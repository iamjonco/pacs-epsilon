package org.eclipse.epsilon.evl.incremental.dom;

import org.eclipse.epsilon.base.incremental.dom.TracedModuleElement;
import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelDuplicateRelation;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.IElementAccess;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IModelTrace;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.evl.dom.Constraint;
import org.eclipse.epsilon.evl.dom.ConstraintContext;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.execute.IEvlExecutionTraceManager;
import org.eclipse.epsilon.evl.incremental.execute.context.IncrementalEvlContext;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTrace;
import org.eclipse.epsilon.evl.incremental.trace.IEvlModuleTraceRepository;
import org.eclipse.epsilon.evl.incremental.trace.IGuardTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ConstraintContext that holds a reference to its tracing reference so it can create ElementAccessTraces and that
 * starts/stops the recording of the guardBlock accesses.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class TracedConstraintContext extends ConstraintContext 
		implements TracedModuleElement<IContextTrace> {
	
	private static final Logger logger = LoggerFactory.getLogger(TracedConstraintContext.class);
	
	private IContextTrace currentTrace;
	private int index;
	
	@Override
	public void setCurrentTrace(IContextTrace trace) {
		this.currentTrace = trace;
	}

	@Override
	public IContextTrace getCurrentTrace() {
		return currentTrace;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		this.index = cst.childIndex;
	}

	@Override
	public boolean appliesTo(Object object, IEvlContext context, final boolean checkType) throws EolRuntimeException {
		final IModel owningModel = context.getModelRepository().getOwningModel(object);
		if (checkType && !owningModel.isOfType(object, getTypeName())) {
			return false;
		}
		@SuppressWarnings("unchecked")
		IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>> tracedEvlContext =
				(IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>>) context;
		return appliesTo(object, (IIncrementalModel)owningModel, tracedEvlContext);
		
	}
	
	private boolean appliesTo(Object object, IIncrementalModel owningModel,
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>> tracedEvlContext)
					throws EolRuntimeException {
		try {
			createContextTrace(object, tracedEvlContext, owningModel);
		} catch (EolIncrementalExecutionException e) {
			throw new EolRuntimeException("Error creating trace information. " + e.getMessage(), this);
		}
		if (guardBlock != null) {
			tracedEvlContext.getPropertyAccessExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().aboutToExecute(guardBlock, tracedEvlContext);
			Boolean result = guardBlock.execute(tracedEvlContext, Variable.createReadOnlyVariable("self", object));
			tracedEvlContext.getPropertyAccessExecutionListener().finishedExecuting(guardBlock, result, tracedEvlContext);
			tracedEvlContext.getAllInstancesInvocationExecutionListener().finishedExecuting(guardBlock, result, tracedEvlContext);
			return result;
		} else {
			return true;
		}
		
	}
	
	/**
	 * Create an  IContextTrace and add an ElementAccessTrace.
	 * 
	 * This method is called once for each execution of the Context and therefore we create a new 
	 * IContextTrace for each. Each new IContextTrace will have a companion IExecutionContext that stores
	 * the context information of the execution. 
	 * 
	 * @param modelElement	The model element for which the Context is being executed
	 * @param context	The IEolContext of the execution
	 * @param owningModel	The model that owns the element
	 * @throws TraceModelDuplicateRelation 
	 */
	private void createContextTrace(Object modelElement,
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>> context,
			final IIncrementalModel model)
		throws EolIncrementalExecutionException {
		
		logger.info("Create ContextTrace: element: {}, context: {}", modelElement, getTypeName());
		if (!(model instanceof IIncrementalModel)) {
			logger.error("Model {} does not implement IIncrementalModel",  model);
			throw new EolIncrementalExecutionException("Cannot trace non-incremental models");
		}
		String moduleUri = context.getModule().getUri().toString();
		IEvlModuleTrace moduleExecutionTrace = getModuleExecutionTrace(context, moduleUri);
		IModelElementTrace elementTrace = getOrCreateModelElementTrace(modelElement, context, model, moduleUri,
				moduleExecutionTrace);
		createContextExecutionTrace(moduleExecutionTrace, elementTrace);
		IElementAccess access = moduleExecutionTrace.createElementAccess(currentTrace, elementTrace);
		if (!currentTrace.accesses().create(access)) {
			logger.info("ElementAcces was not added to ContextTrace");
		}
		for (Constraint c : constraints) {
			TracedConstraint tc = (TracedConstraint) c;
			IInvariantTrace tcTrace = currentTrace.createInvariantTrace(tc.getName());
			tc.setCurrentTrace(tcTrace);
			if (!tc.createGuardTrace()) {
				throw new EolIncrementalExecutionException("Error creating Guard Execution Trace.");
			}
			if (!tc.createCheckTrace()) {
				throw new EolIncrementalExecutionException("Error creating Guard Execution Trace.");
			}
			if (!tc.createMessageTrace()) {
				throw new EolIncrementalExecutionException("Error creating Guard Execution Trace.");
			}
		}
	}

	/**
	 * @param context
	 * @param moduleUri
	 * @return
	 * @throws EolIncrementalExecutionException
	 */
	private IEvlModuleTrace getModuleExecutionTrace(
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>> context,
			String moduleUri) throws EolIncrementalExecutionException {
		IEvlModuleTraceRepository repo = context.getTraceManager().getExecutionTraceRepository();
		IEvlModuleTrace moduleExecutionTrace = repo.getEvlModuleTraceByIdentity(moduleUri);
		if (moduleExecutionTrace == null) {
			throw new EolIncrementalExecutionException("A moduleExecutionTrace was not found for the module under execution. "
					+ "The module execution trace must be created at the begining of the execution of the module.");
		}
		return moduleExecutionTrace;
	}

	/**
	 * @param moduleExecutionTrace
	 * @param elementTrace
	 * @throws EolIncrementalExecutionException
	 */
	private void createContextExecutionTrace(IEvlModuleTrace moduleExecutionTrace, IModelElementTrace elementTrace)
			throws EolIncrementalExecutionException {
		logger.debug("Creating a new context trace");
		currentTrace = moduleExecutionTrace.createContextTrace(getTypeName(), index);
		if (guardBlock != null) {
			try {
				IGuardTrace guard = currentTrace.createGuardTrace();
				((TracedGuardBlock) guardBlock).setCurrentTrace(guard);
			} catch (EolIncrementalExecutionException e2) {
				throw new IllegalStateException("Can't create GuardTrace for Context " + getTypeName() + ".", e2);	
			}	
		}
		IExecutionContext exContext = currentTrace.createExecutionContext();
		exContext.createModelElementVariable("self", elementTrace);
	}

	/**
	 * @param modelElement
	 * @param context
	 * @param model
	 * @param moduleUri
	 * @param moduleExecutionTrace
	 * @return
	 * @throws EolIncrementalExecutionException
	 */
	private IModelElementTrace getOrCreateModelElementTrace(Object modelElement,
			IncrementalEvlContext<IEvlModuleTraceRepository, IEvlExecutionTraceManager<IEvlModuleTraceRepository>> context,
			final IIncrementalModel model, String moduleUri, IEvlModuleTrace moduleExecutionTrace)
			throws EolIncrementalExecutionException {
		IModelElementTrace elementTrace = context.getTraceManager().getExecutionTraceRepository()
				.getModelElementTraceFor(moduleUri, model.getName(), model.getModelUri(), model.getElementId(modelElement));
		if (elementTrace == null) {
			IModelTrace modelTrace = context.getTraceManager().getExecutionTraceRepository()
					.getModelTraceByIdentity(null, model.getName(), ((IIncrementalModel)model).getModelUri());
			if (modelTrace == null) {
				modelTrace = moduleExecutionTrace.createModelTrace(model.getName(), model.getModelUri());
			}
			elementTrace = modelTrace.createModelElementTrace(model.getElementId(modelElement));
		}
		return elementTrace;
	}

	public void goOnline() {
		for (Constraint c : constraints) {
			((TracedConstraint)c).setListeningToChagnes(true);
		}
	}
	
	public void goOffline() {
		for (Constraint c : constraints) {
			((TracedConstraint)c).setListeningToChagnes(false);
		}
	}
	
}
