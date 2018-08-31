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
package org.eclipse.epsilon.evl.incremental.util;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.eclipse.epsilon.base.incremental.trace.impl.*;
import org.eclipse.epsilon.evl.incremental.trace.impl.*;

/**
 * A Factory to wrap Vertices around concrete classes based on the Vertex label.
 * It is useful for instantiating domain elements back from Gremlin elements
 * 
 * @author Horacio Hoyos
 *
 */
public class TraceFactory {

    public static Object createModuleElementTrace(Element delegate, GraphTraversalSource gts) {
        String label = delegate.label();
        switch (label) {
  
        case "EvlModuleTrace":
            EvlModuleTraceGremlin evlModuleTrace = new EvlModuleTraceGremlin();
            evlModuleTrace.delegate((Vertex) delegate);
            evlModuleTrace.graphTraversalSource(gts);
            return evlModuleTrace;
  
        case "ContextTrace":
            ContextTraceGremlin contextTrace = new ContextTraceGremlin();
            contextTrace.delegate((Vertex) delegate);
            contextTrace.graphTraversalSource(gts);
            return contextTrace;
  
        case "InvariantTrace":
            InvariantTraceGremlin invariantTrace = new InvariantTraceGremlin();
            invariantTrace.delegate((Vertex) delegate);
            invariantTrace.graphTraversalSource(gts);
            return invariantTrace;
  
        case "GuardTrace":
            GuardTraceGremlin guardTrace = new GuardTraceGremlin();
            guardTrace.delegate((Vertex) delegate);
            guardTrace.graphTraversalSource(gts);
            return guardTrace;
  
        case "CheckTrace":
            CheckTraceGremlin checkTrace = new CheckTraceGremlin();
            checkTrace.delegate((Vertex) delegate);
            checkTrace.graphTraversalSource(gts);
            return checkTrace;
  
        case "MessageTrace":
            MessageTraceGremlin messageTrace = new MessageTraceGremlin();
            messageTrace.delegate((Vertex) delegate);
            messageTrace.graphTraversalSource(gts);
            return messageTrace;
  
        case "SatisfiesTrace":
            SatisfiesTraceGremlin satisfiesTrace = new SatisfiesTraceGremlin();
            satisfiesTrace.delegate((Vertex) delegate);
            satisfiesTrace.graphTraversalSource(gts);
            return satisfiesTrace;
  
        case "ModelAccess":
            ModelAccessGremlin modelAccess = new ModelAccessGremlin();
            modelAccess.delegate((Vertex) delegate);
            modelAccess.graphTraversalSource(gts);
            return modelAccess;
  
        case "ExecutionContext":
            ExecutionContextGremlin executionContext = new ExecutionContextGremlin();
            executionContext.delegate((Vertex) delegate);
            executionContext.graphTraversalSource(gts);
            return executionContext;
  
        case "ModelElementVariable":
            ModelElementVariableGremlin modelElementVariable = new ModelElementVariableGremlin();
            modelElementVariable.delegate((Vertex) delegate);
            modelElementVariable.graphTraversalSource(gts);
            return modelElementVariable;
  
        case "ElementAccess":
            ElementAccessGremlin elementAccess = new ElementAccessGremlin();
            elementAccess.delegate((Vertex) delegate);
            elementAccess.graphTraversalSource(gts);
            return elementAccess;
  
        case "AllInstancesAccess":
            AllInstancesAccessGremlin allInstancesAccess = new AllInstancesAccessGremlin();
            allInstancesAccess.delegate((Vertex) delegate);
            allInstancesAccess.graphTraversalSource(gts);
            return allInstancesAccess;
  
        case "PropertyAccess":
            PropertyAccessGremlin propertyAccess = new PropertyAccessGremlin();
            propertyAccess.delegate((Vertex) delegate);
            propertyAccess.graphTraversalSource(gts);
            return propertyAccess;
  
        case "ModelTrace":
            ModelTraceGremlin modelTrace = new ModelTraceGremlin();
            modelTrace.delegate((Vertex) delegate);
            modelTrace.graphTraversalSource(gts);
            return modelTrace;
  
        case "ModelTypeTrace":
            ModelTypeTraceGremlin modelTypeTrace = new ModelTypeTraceGremlin();
            modelTypeTrace.delegate((Vertex) delegate);
            modelTypeTrace.graphTraversalSource(gts);
            return modelTypeTrace;
  
        case "ModelElementTrace":
            ModelElementTraceGremlin modelElementTrace = new ModelElementTraceGremlin();
            modelElementTrace.delegate((Vertex) delegate);
            modelElementTrace.graphTraversalSource(gts);
            return modelElementTrace;
  
        case "PropertyTrace":
            PropertyTraceGremlin propertyTrace = new PropertyTraceGremlin();
            propertyTrace.delegate((Vertex) delegate);
            propertyTrace.graphTraversalSource(gts);
            return propertyTrace;
        
        }
        return null;
    }
}
