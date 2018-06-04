 /*******************************************************************************
 * This file was automatically generated on: 2018-06-04.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.base.incremental.trace.impl;

import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTrace;
import org.eclipse.epsilon.base.incremental.trace.IExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.IContextModuleElementTraceHasExecutionContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;


/**
 * Implementation of IContextModuleElementTraceHasExecutionContext reference. 
 */
public class ContextModuleElementTraceHasExecutionContext extends Feature implements IContextModuleElementTraceHasExecutionContext {
    
    /** The source(s) of the reference */
    protected IContextModuleElementTrace source;
    
    /** The target(s) of the reference */
    protected IExecutionContext target;
    
    /**
     * Instantiates a new IContextModuleElementTraceHasExecutionContext.
     *
     * @param source the source of the reference
     */
    public ContextModuleElementTraceHasExecutionContext (IContextModuleElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public IExecutionContext get() {
        return target;
    }
    
    @Override
    public boolean create(IExecutionContext target) {
        if (conflict(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IExecutionContext target) {
        if (!related(target)) {
            return false;
        }
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IExecutionContext target) {
        boolean result = false;
        result |= get() != null;
        return result;
    }
    
    @Override
    public boolean related(IExecutionContext target) {
  
        return target.equals(this.target) ;
    }
    
    // PRIVATE API
    
    @Override
    public void set(IExecutionContext target) {
        this.target = target;
    }
    
    @Override
    public void remove(IExecutionContext target) {
        this.target = null;
    }

}