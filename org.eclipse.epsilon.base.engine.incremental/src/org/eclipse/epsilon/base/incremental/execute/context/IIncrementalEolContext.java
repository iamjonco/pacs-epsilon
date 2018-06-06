/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.execute.context;

import org.eclipse.epsilon.base.incremental.execute.IEolExecutionTraceManager;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.AllInstancesInvocationExecutionListener;
import org.eclipse.epsilon.base.incremental.execute.introspection.recording.PropertyAccessExecutionListener;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTrace;
import org.eclipse.epsilon.base.incremental.trace.IModuleExecutionTraceRepository;
import org.eclipse.epsilon.eol.execute.context.IEolContext;

/**
 * The Interface IIncrementalEolContext.
 *
 * @param <T> the type of ExecutionTraceManager (see {@link IEolExecutionTraceManager}
 */
public interface IIncrementalEolContext<R extends IModuleExecutionTraceRepository, 
		M extends IEolExecutionTraceManager<R>> extends IEolContext {
	
	/**
	 * Gets the trace manager.
	 *
	 * @return the trace manager
	 */
	M getTraceManager();
	
	/**
	 * Sets the trace manager.
	 *
	 * @param traceManager the new trace manager
	 */
	void setTraceManager(M traceManager);
	
	/**
	 * Gets the property access execution listener.
	 *
	 * @return the property access execution listener
	 */
	PropertyAccessExecutionListener getPropertyAccessExecutionListener();
	
	/**
	 * Gets the all instances invocation execution listener.
	 *
	 * @return the all instances invocation execution listener
	 */
	AllInstancesInvocationExecutionListener getAllInstancesInvocationExecutionListener();

}
