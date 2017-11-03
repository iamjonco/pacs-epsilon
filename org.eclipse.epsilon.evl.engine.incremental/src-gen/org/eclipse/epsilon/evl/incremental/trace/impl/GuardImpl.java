 /*******************************************************************************
 * This file was automatically generated on: 2017-11-03.
 * Only modify protected regions indicated by "<!-- -->"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.eclipse.epsilon.evl.incremental.trace.Guard;
import org.eclipse.epsilon.evl.incremental.trace.GuardedElement;
import org.eclipse.epsilon.evl.incremental.trace.GuardHasLimits;

import org.eclipse.epsilon.eol.incremental.trace.Access;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionHasAccesses;
import org.eclipse.epsilon.eol.incremental.trace.impl.ExecutionHasAccessesImpl;

/**
 * Implementation of Guard. 
 */
public class GuardImpl implements Guard {

    /** The id */
    private Object id;

    /** The result */
    private boolean result;

    /** The accesses relation */
    private final ExecutionHasAccesses accesses;

    /** The limits relation */
    private final GuardHasLimits limits;

    /**
     * Instantiates a new Guard. The Guard is uniquely identified by its
     * container and any attributes identified as indexes.
     */    
    public GuardImpl(GuardedElement container) {
        accesses = new ExecutionHasAccessesImpl(this);
        limits = new GuardHasLimitsImpl(this);
    }
    
    @Override
    public Object getId() {
        return id;
    }
    
    
    @Override
    public void setId(Object value) {
        this.id = value;
    }   
     
    @Override
    public boolean getResult() {
        return result;
    }
    
    
    @Override
    public void setResult(boolean value) {
        this.result = value;
    }   
     
    @Override
    public ExecutionHasAccesses accesses() {
        return accesses;
    }
    @Override
    public GuardHasLimits limits() {
        return limits;
    }
 

}