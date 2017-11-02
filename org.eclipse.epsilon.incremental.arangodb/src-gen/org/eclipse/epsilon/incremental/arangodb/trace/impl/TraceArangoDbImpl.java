/*******************************************************************************
 * Copyright (c) 2016 University of York
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos - Initial API and implementation
 *******************************************************************************/

/*******************************************************************************
 ** Trace OrientDB Trace Model Implementation automatically
 ** generated on Sat Sep 09 20:01:33 BST 2017.
 ** Do not modify this file.
 *******************************************************************************/
package org.eclipse.epsilon.incremental.arangodb.trace.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.epsilon.eol.incremental.generation.*;
import org.eclipse.epsilon.eol.incremental.trace.ExecutionContext;
import org.eclipse.epsilon.eol.incremental.trace.ModelElement;
import org.eclipse.epsilon.eol.incremental.trace.ModuleElement;
import org.eclipse.epsilon.eol.incremental.trace.Property;
import org.eclipse.epsilon.eol.incremental.trace.Trace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * An implementation of the Trace .
 */
public class TraceArangoDbImpl extends JSONObject implements Trace {
    
    
    /**
     * Empty constructor to instantiate elements from the DB.
     *
     * @param delegate the delegate
     */
    public TraceArangoDbImpl() { }
    
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- protected region id-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region id-getter-doc end --> 
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     */
    public java.lang.Object getId() {
        return (java.lang.Object)get("_id");
    }
    
 
    /**
     * Returns the value of the '<em><b>Execution Context</b></em>' attribute.
     * <!-- protected region executionContext-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Execution Context</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region executionContext-getter-doc end --> 
     * @return the value of the '<em>Execution Context</em>' attribute.
     * @see #setExecutionContext(String)
     */
    public ExecutionContext getExecutionContext() {
        return null;
    }
    
    
    /**
     * Sets the value of the '{@link Trace#ExecutionContext <em>Execution Context</em>}' attribute.
     * <!-- protected region executionContext-setter-doc on begin -->
     * <!-- protected region executionContext-setter-doc end --> 
     * @param value the new value of the '<em>Execution Context/em>' attribute.
     * @see #getExecutionContext()
     */
    public void setExecutionContext(ExecutionContext value) {
        
    }
    
    /**
     * Returns the value of the '<em><b>Traces</b></em>' attribute.
     * <!-- protected region traces-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Traces</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region traces-getter-doc end --> 
     * @return the value of the '<em>Traces</em>' attribute.
     * @see #setTraces(String)
     */
    public ModuleElement getTraces() {
        return null;
    }
    
    
    /**
     * Sets the value of the '{@link Trace#Traces <em>Traces</em>}' attribute.
     * <!-- protected region traces-setter-doc on begin -->
     * <!-- protected region traces-setter-doc end --> 
     * @param value the new value of the '<em>Traces/em>' attribute.
     * @see #getTraces()
     */
    public void setTraces(ModuleElement value) {
        
    }
    
    /**
     * Returns the value of the '<em><b>Reaches</b></em>' attribute.
     * <!-- protected region reaches-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Reaches</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region reaches-getter-doc end --> 
     * @return the value of the '<em>Reaches</em>' attribute.
     * @see #setReaches(String)
     */
    public List<ModelElement> getReaches() {
        return null;
    }

    /**
     * Returns the value of the '<em><b>Accesses</b></em>' attribute.
     * <!-- protected region accesses-getter-doc on begin -->
     * <p>
     * If the meaning of the '<em>Accesses</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- protected region accesses-getter-doc end --> 
     * @return the value of the '<em>Accesses</em>' attribute.
     * @see #setAccesses(String)
     */
    public List<Property> getAccesses() {
        return null;
    }

 
}
