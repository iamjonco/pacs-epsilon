 /*******************************************************************************
 * This file was automatically generated on: 2017-11-23.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.eol.incremental.trace.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.epsilon.eol.incremental.trace.IModelTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTypeTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelTraceHasTypes;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModelTraceHasTypes reference. 
 */
public class ModelTraceHasTypes extends Feature implements IModelTraceHasTypes {
    
    /** The source(s) of the reference */
    protected IModelTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IModelTypeTrace> target =  new ConcurrentLinkedQueue<IModelTypeTrace>();
    
    /**
     * Instantiates a new IModelTraceHasTypes.
     *
     * @param source the source of the reference
     */
    public ModelTraceHasTypes (IModelTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<IModelTypeTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IModelTypeTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.model().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IModelTypeTrace target) {
        if (!related(target)) {
            return false;
        }
        target.model().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IModelTypeTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.model().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IModelTypeTrace target) {
  
        return get().contains(target) & source.equals(target.model().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IModelTypeTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IModelTypeTrace target) {
        this.target.remove(target);
    }

}