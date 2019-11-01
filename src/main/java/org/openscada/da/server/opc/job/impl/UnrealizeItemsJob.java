/*
 * This file is part of the OpenSCADA project
 * Copyright (C) 2006-2011 TH4 SYSTEMS GmbH (http://th4-systems.com)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.openscada.da.server.opc.job.impl;

import org.openscada.da.server.opc.job.JobResult;
import org.openscada.da.server.opc.job.ThreadJob;
import org.openscada.opc.dcom.common.ResultSet;
import org.openscada.opc.dcom.da.impl.OPCItemMgt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This job removes items from an opc group
 * @author Jens Reimann &lt;jens.reimann@th4-systems.com&gt;
 *
 */
public class UnrealizeItemsJob extends ThreadJob implements JobResult<ResultSet<Integer>>
{
    public static final long DEFAULT_TIMEOUT = 5000L;

    private final static Logger logger = LoggerFactory.getLogger ( UnrealizeItemsJob.class );

    private final OPCItemMgt itemMgt;

    private final Integer[] serverHandles;

    private ResultSet<Integer> result;

    public UnrealizeItemsJob ( final long timeout, final OPCItemMgt itemMgt, final Integer[] serverHandles )
    {
        super ( timeout );
        this.itemMgt = itemMgt;
        this.serverHandles = serverHandles;
    }

    @Override
    protected void perform () throws Exception
    {
        logger.info ( "UnRealizing items: {}", new Object[] { this.serverHandles } );
        this.result = this.itemMgt.remove ( this.serverHandles );
    }

    @Override
    public ResultSet<Integer> getResult ()
    {
        return this.result;
    }
}
