/*
 * This file is part of the OpenSCADA project
 * 
 * Copyright (C) 2013 Jens Reimann (ctron@dentrassi.de)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openscada.da.server.opc;

import org.eclipse.scada.utils.init.Initializer;
import org.openscada.da.opc.configuration.ConfigurationPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelInitializerImpl implements Initializer
{

    private final static Logger logger = LoggerFactory.getLogger ( ModelInitializerImpl.class );

    @Override
    public void initialize ( final Object type )
    {
        if ( type.equals ( "emf" ) )
        {
            logger.info ( "Initializing model" );
            ConfigurationPackage.eINSTANCE.eClass ();
            logger.info ( "Initialized model: {}", ConfigurationPackage.eNS_URI );
        }
    }

}
