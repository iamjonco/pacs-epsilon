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
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTrace;
import org.eclipse.epsilon.eol.incremental.trace.IPropertyTrace;
import org.eclipse.epsilon.eol.incremental.trace.IModelElementTraceHasProperties;
import org.eclipse.epsilon.eol.incremental.trace.impl.Feature;


/**
 * Implementation of IModelElementTraceHasProperties reference. 
 */
public class ModelElementTraceHasProperties extends Feature implements IModelElementTraceHasProperties {
    
    /** The source(s) of the reference */
    protected IModelElementTrace source;
    
    /** The target(s) of the reference */
    protected Queue<IPropertyTrace> target =  new ConcurrentLinkedQueue<IPropertyTrace>();
    
    /**
     * Instantiates a new IModelElementTraceHasProperties.
     *
     * @param source the source of the reference
     */
    public ModelElementTraceHasProperties (IModelElementTrace source) {
        super(true);
        this.source = source;
    }
    
    // PUBLIC API
        
    @Override
    public Queue<IPropertyTrace> get() {
        return target;
    }
    
    @Override
    public boolean create(IPropertyTrace target) {
        if (conflict(target)) {
            return false;
        }
        target.element().set(source);
        if (related(target)) {
            return false;
        }
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IPropertyTrace target) {
        if (!related(target)) {
            return false;
        }
        target.element().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IPropertyTrace target) {
        boolean result = false;
        if (isUnique) {
            result |= get().contains(target);
        }
        result |= target.element().get() != null;
        return result;
    }
    
    @Override
    public boolean related(IPropertyTrace target) {
  
        return get().contains(target) & source.equals(target.element().get());
    }
    
    // PRIVATE API
    
    @Override
    public void set(IPropertyTrace target) {
        this.target.add(target);
    }
    
    @Override
    public void remove(IPropertyTrace target) {
        this.target.remove(target);
    }

}