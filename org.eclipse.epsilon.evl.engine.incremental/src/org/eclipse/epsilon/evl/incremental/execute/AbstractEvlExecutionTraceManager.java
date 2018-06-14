package org.eclipse.epsilon.evl.incremental.execute;


import org.eclipse.epsilon.base.incremental.execute.AbstractEolExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.base.incremental.trace.util.ExecutionContextRepositoryImpl;
import org.eclipse.epsilon.base.incremental.trace.util.IExecutionContextRepository;
import org.eclipse.epsilon.evl.execute.context.IEvlContext;
import org.eclipse.epsilon.evl.incremental.execute.introspection.recording.SatisfiesInvocationExecutionListener;


/**
 * A base implementation of the {@link IEvlExecutionTraceManager}.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
@Deprecated
public abstract class AbstractEvlExecutionTraceManager
		extends AbstractEolExecutionTraceManager<IEvlExecutionTraceRepository>
    	implements IEvlExecutionTraceManager {
	
	/** Repository of execution traces executions */
	protected IEvlExecutionTraceRepository executionTraceRepository;
	
	protected IExecutionContextRepository executionContextTraceRepository;
	
	private PropertyAccessExecutionListener propertyAccessListener = new PropertyAccessExecutionListener();
	private AllInstancesInvocationExecutionListener allAccessListener = new AllInstancesInvocationExecutionListener();
	private SatisfiesInvocationExecutionListener satisfiesListener = new SatisfiesInvocationExecutionListener();
	

	@Override
	public IEvlExecutionTraceRepository getExecutionTraceRepository() {
		if (executionTraceRepository == null) {
			executionTraceRepository = new EvlModuleExecutionRepository(inParallel);
		}
		return executionTraceRepository;
	}
	
	@Override
	public IExecutionContextRepository getExecutionContextRepository() {
		if (executionContextTraceRepository == null) {
			executionContextTraceRepository = new ExecutionContextRepositoryImpl();
		}
		return executionContextTraceRepository;
	}


	@Override
	public PropertyAccessExecutionListener getPropertyAccessListener() {
		return propertyAccessListener;
	}

	@Override
	public AllInstancesInvocationExecutionListener getAllInstancesAccessListener() {
		return allAccessListener;
	}

	@Override
	public SatisfiesInvocationExecutionListener getSatisfiesListener() {
		return satisfiesListener;
	}

}
