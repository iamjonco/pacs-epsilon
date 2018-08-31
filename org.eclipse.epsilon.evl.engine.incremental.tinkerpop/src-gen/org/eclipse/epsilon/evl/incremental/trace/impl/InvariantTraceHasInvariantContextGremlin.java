 /*******************************************************************************
 * This file was automatically generated on: 2018-08-31.
 * Only modify protected regions indicated by "/** **&#47;"
 *
 * Copyright (c) 2017 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 ******************************************************************************/
package org.eclipse.epsilon.evl.incremental.trace.impl;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.*;
import org.eclipse.epsilon.base.incremental.trace.gremlin.impl.GremlinWrapper;
import org.eclipse.epsilon.base.incremental.exceptions.TraceModelConflictRelation;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTrace;
import org.eclipse.epsilon.evl.incremental.trace.IContextTrace;
import org.eclipse.epsilon.evl.incremental.trace.IInvariantTraceHasInvariantContext;
import org.eclipse.epsilon.base.incremental.trace.impl.Feature;
import org.eclipse.epsilon.evl.incremental.util.TraceFactory;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Implementation of IInvariantTraceHasInvariantContext reference. 
 */
public class InvariantTraceHasInvariantContextGremlin extends Feature
        implements IInvariantTraceHasInvariantContext, GremlinWrapper<Edge> {
    
    /** The graph traversal source for all navigations */
    private GraphTraversalSource gts;
    
    /** The source(s) of the reference */
    protected IInvariantTrace source;
    
    /** Fast access for single-valued references */
    private Edge delegate;
 
    
    /**
     * Instantiates a new IInvariantTraceHasInvariantContext.
     *
     * @param source the source of the reference
     */
    public InvariantTraceHasInvariantContextGremlin (IInvariantTrace source, GraphTraversalSource gts) {
        super(true);
        this.source = source;
        this.gts = gts;
    }
    
    // PUBLIC API
        
    @Override
    public IContextTrace get() {
        if (delegate == null) {
            return null;
        }
        GraphTraversalSource g = startTraversal();
        IContextTrace result = null;
        try {
            Vertex to = g.E(delegate).outV().next();
            result = (IContextTrace) TraceFactory.createModuleElementTrace(to, gts);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    

    @Override
    public boolean create(IContextTrace target) throws TraceModelConflictRelation {
        if (conflict(target)) {
            throw new TraceModelConflictRelation("Relation to previous IContextTrace exists");
        }
        if (related(target)) {
            return false;
        }
        target.constraints().set(source);
        set(target);
        return true;
    }

    @Override
    public boolean destroy(IContextTrace target) {
        if (!related(target)) {
            return false;
        }
        target.constraints().remove(source);
        remove(target);
        return true;
    }
    
    @Override
    public boolean conflict(IContextTrace target) {
        boolean result = false;
        GraphTraversalSource g = startTraversal();
        try {
            result |= delegate == null ? g.V(source.getId()).out("invariantContext").hasNext() : g.E(delegate).outV().hasId(target.getId()).hasNext();
            Iterable<IInvariantTrace> iterable = () -> target.constraints().get();
            Stream<IInvariantTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
            result |= delegate == null ? false : target.constraints().isUnique() &&
        	    targetStream.anyMatch(source::equals);
        }
        finally {
            finishTraversal(g);
        }
        return result;
    }
    
    @Override
    public boolean related(IContextTrace target) {
    	if (target == null) {
			return false;
		}
        if (delegate == null) {
            return false;
        }
		Iterable<IInvariantTrace> iterable = () -> target.constraints().get();
		Stream<IInvariantTrace> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        GraphTraversalSource g = startTraversal();
        boolean result = false;
        try {
		  result = g.E(delegate).outV().hasId(target.getId()).hasNext() && targetStream.anyMatch(source::equals);
		}
		finally {
            finishTraversal(g);
        }
        return result;
	}
	
	@Override
    public Edge delegate() {
        return delegate;
    }

    @Override
    public void delegate(Edge delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public void graphTraversalSource(GraphTraversalSource gts) {
        this.gts = gts;
    }
        
    
    // PRIVATE API
    
    @Override
    public void set(IContextTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            delegate = g.V(source.getId()).addE("invariantContext").to(g.V(target.getId())).next();
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
        
    }
    
    @Override
    public void remove(IContextTrace target) {
        GraphTraversalSource g = startTraversal();
        try {
            g.E(delegate).drop();
            delegate = null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            finishTraversal(g);
        }
    }
    
    private GraphTraversalSource startTraversal() {
        return this.gts.clone();
    }
    
    private void finishTraversal(GraphTraversalSource g) {
        try {
            g.close();
        } catch (Exception e) {
            // Fail silently?
        }
    }

}