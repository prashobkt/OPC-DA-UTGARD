/**j-Interop (Pure Java implementation of DCOM protocol) 
 * Copyright (C) 2006  Vikram Roopchand
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * Though a sincere effort has been made to deliver a professional, 
 * quality product,the library itself is distributed WITHOUT ANY WARRANTY; 
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.jinterop.dcom.transport;

import java.util.Properties;

import rpc.ProviderException;
import rpc.Transport;

/**
 * @exclude
 * @since 1.0
 *
 */
public final class JIComTransportFactory extends rpc.TransportFactory {
	
	private static JIComTransportFactory factory = null; 
	private JIComTransportFactory() {}
	
	public Transport createTransport(String address, Properties properties)
    	throws ProviderException {
			return new JIComTransport(address, properties);
	}
	
	public static JIComTransportFactory getSingleTon()
	{
		if (factory == null)
		{
			synchronized (JIComTransportFactory.class) {
				if (factory == null)
				{
					factory = new JIComTransportFactory();
				}
			}
		}
		
		return factory;
	}
}
