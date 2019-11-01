/**
 * This file is part of the openSCADA project
 * 
 * Copyright (C) 2013 Jens Reimann (ctron@dentrassi.de)
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openscada.da.opc.configuration.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.openscada.da.opc.configuration.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openscada.da.opc.configuration.ConfigurationPackage
 * @generated
 */
public class ConfigurationAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ConfigurationPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConfigurationAdapterFactory ()
    {
        if ( modelPackage == null )
        {
            modelPackage = ConfigurationPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType ( Object object )
    {
        if ( object == modelPackage )
        {
            return true;
        }
        if ( object instanceof EObject )
        {
            return ( (EObject)object ).eClass ().getEPackage () == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConfigurationSwitch<Adapter> modelSwitch = new ConfigurationSwitch<Adapter> () {
        @Override
        public Adapter caseConfigurationType ( ConfigurationType object )
        {
            return createConfigurationTypeAdapter ();
        }

        @Override
        public Adapter caseConnectionsType ( ConnectionsType object )
        {
            return createConnectionsTypeAdapter ();
        }

        @Override
        public Adapter caseDocumentRoot ( DocumentRoot object )
        {
            return createDocumentRootAdapter ();
        }

        @Override
        public Adapter caseInitialItemsType ( InitialItemsType object )
        {
            return createInitialItemsTypeAdapter ();
        }

        @Override
        public Adapter caseInitialItemType ( InitialItemType object )
        {
            return createInitialItemTypeAdapter ();
        }

        @Override
        public Adapter caseRootType ( RootType object )
        {
            return createRootTypeAdapter ();
        }

        @Override
        public Adapter defaultCase ( EObject object )
        {
            return createEObjectAdapter ();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter ( Notifier target )
    {
        return modelSwitch.doSwitch ( (EObject)target );
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.ConfigurationType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.ConfigurationType
     * @generated
     */
    public Adapter createConfigurationTypeAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.ConnectionsType <em>Connections Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.ConnectionsType
     * @generated
     */
    public Adapter createConnectionsTypeAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.DocumentRoot
     * @generated
     */
    public Adapter createDocumentRootAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.InitialItemsType <em>Initial Items Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.InitialItemsType
     * @generated
     */
    public Adapter createInitialItemsTypeAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.InitialItemType <em>Initial Item Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.InitialItemType
     * @generated
     */
    public Adapter createInitialItemTypeAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.openscada.da.opc.configuration.RootType <em>Root Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.openscada.da.opc.configuration.RootType
     * @generated
     */
    public Adapter createRootTypeAdapter ()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter ()
    {
        return null;
    }

} //ConfigurationAdapterFactory
