//package dev.opcda.test;
//
//import in.intellicar.windmill.scada.ScadaSnapshot;
//import in.intellicar.windmill.scada.callback.ConnStatusCallback;
//import in.intellicar.windmill.scada.callback.DataReceiveCallback;
//import in.intellicar.windmill.scada.config.OPCConfig;
//import in.intellicar.windmill.scada.config.ScadaIngestorConfig;
//import in.intellicar.windmill.scada.config.ScadaTagConfig;
//import in.intellicar.windmill.scada.config.ScadaTagSetConfig;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.concurrent.Executors;
//import java.util.logging.Level;
//import org.jinterop.dcom.common.JIException;
//import org.jinterop.dcom.common.JISystem;
//import org.jinterop.dcom.core.JIVariant;
//import org.openscada.opc.lib.common.ConnectionInformation;
//import org.openscada.opc.lib.common.NotConnectedException;
//import org.openscada.opc.lib.da.AccessBase;
//import org.openscada.opc.lib.da.AccessStateListener;
//import org.openscada.opc.lib.da.AddFailedException;
//import org.openscada.opc.lib.da.AutoReconnectController;
//import org.openscada.opc.lib.da.AutoReconnectListener;
//import org.openscada.opc.lib.da.AutoReconnectState;
//import org.openscada.opc.lib.da.DataCallback;
//import org.openscada.opc.lib.da.DuplicateGroupException;
//import org.openscada.opc.lib.da.Item;
//import org.openscada.opc.lib.da.ItemState;
//import org.openscada.opc.lib.da.Server;
//import org.openscada.opc.lib.da.SyncAccess;
//
//public class OpcDaDataSource extends AbstractDataSource {
//	public Server server;
//	public ConnectionInformation conninfo;
//	public AutoReconnectController controller;
//	public AccessBase accessBase;
//	private boolean isAttached = false;
//	private boolean isConnected = false;
//	private int restartCount = 0;
//	int low = 1;
//	int high = 1000;
//	Random r = new Random();
//	private AutoReconnectListener connectionStateListener;
//	private AccessStateListener accessStateListener;
//	public DataCallback dataCallback;
//
//	private void exponentialBackoff() {
//		try {
//			int seconds = (int) Math.round(Math.pow(2.0D, (this.restartCount / 2))) * 500
//					+ this.r.nextInt(this.high - this.low) + this.low;
//			System.out.println("Backing off " + seconds);
//			Thread.sleep(seconds);
//		} catch (InterruptedException interruptedException) {
//		}
//	}
//
//	private static Object convertToNativeType(JIVariant type) {
//		try {
//			if (type.isArray()) {
//				ArrayList<Object> objs = new ArrayList<Object>();
//				Object[] array = (Object[]) type.getObjectAsArray().getArrayInstance();
//
//				for (Object element : array) {
//					objs.add(convertToNativeType((JIVariant) element));
//				}
//
//				return objs;
//			}
//
//			switch (type.getType()) {
//			case 1:
//				return null;
//			case 8:
//				return type.getObjectAsString().getString();
//			case 16:
//				return Integer.valueOf(type.getObjectAsInt());
//			case 2:
//				return Short.valueOf(type.getObjectAsShort());
//			case 3:
//				return Integer.valueOf(type.getObjectAsInt());
//			case 22:
//				return Integer.valueOf(type.getObjectAsInt());
//			case 17:
//				return type.getObjectAsUnsigned().getValue();
//			case 18:
//				return type.getObjectAsUnsigned().getValue();
//			case 19:
//				return type.getObjectAsUnsigned().getValue();
//
//			case 23:
//				return type.getObjectAsUnsigned().getValue();
//
//			case 4:
//				return Float.valueOf(type.getObjectAsFloat());
//			case 11:
//				return Boolean.valueOf(type.getObjectAsBoolean());
//			case 14:
//				return Float.valueOf(type.getObjectAsFloat());
//
//			case 7:
//				return type.getObjectAsDate();
//			case 16407:
//				return type.getObjectAsUnsigned().getValue();
//			}
//
//			System.out.println(type.getType());
//			return type.getObjectAsUnsigned().getValue().toString();
//		} catch (JIException e) {
//			return null;
//		}
//	}
//
//	private void AttachTags() {
//		try {
//			this.accessBase = new SyncAccess(this.server, this.tagConfig.pollInterval);
//			for (ScadaTagConfig eachTag : this.tagConfig.tags) {
//				try {
//					this.accessBase.addItem(eachTag.tag, this.dataCallback);
//				} catch (AddFailedException af) {
//					println("Add failed for tag");
//				}
//			}
//			this.accessBase.addStateListener(this.accessStateListener);
//			this.accessBase.bind();
//
//			println("AccessBase Attached");
//		} catch (StackOverflowError ex) {
//			println("Exception in attaching tags");
//			println(ex.getMessage());
//		} catch (JIException ex) {
//			println("JI_Exception in attaching tags");
//			println(ex.getMessage());
//		} catch (UnknownHostException ex) {
//			println(" Unknown Host Exception");
//		} catch (NotConnectedException nc) {
//			println("Not Connected. Initiating Reconnect");
//			Configure();
//		} catch (DuplicateGroupException dupgrp) {
//			println("j-Interon duplicate group excetpion");
//		}
//	}
//
//	public OpcDaDataSource(ScadaIngestorConfig scadaConfig, ScadaTagSetConfig tagConfig,
//			ConnStatusCallback accessStateCallback, DataReceiveCallback dataReceivedCallback) {
//		super(scadaConfig, tagConfig, accessStateCallback, dataReceivedCallback);
//		this.connectionStateListener = (state -> {
//			switch (state) {
//			case WAITING:
//				this.isConnected = false;
//				return;
//			case DISABLED:
//				this.isConnected = false;
//				Configure();
//			case CONNECTED:
//				return;
//			case CONNECTING:
//				this.isConnected = true;
//				return;
//			case DISCONNECTED:
//				this.isConnected = false;
//				this.restartCount++;
//				if (this.restartCount > 10)
//					exponentialBackoff();
//				break;
//			}
//		});
//		this.accessStateListener = new AccessStateListener() {
//			public void stateChanged(boolean state) {
//				OpcDaDataSource.this.isAttached = state;
//			}
//
//			public void errorOccured(Throwable t) {
//				OpcDaDataSource.this.logger.log(Level.SEVERE, "Exception has occured thrown by access base");
//				OpcDaDataSource.this.DettachTags();
//				OpcDaDataSource.this.AttachTags();
//			}
//		};
//		this.dataCallback = ((item, itemState) -> {
//			try {
//				long currTimeStamp = itemState.getTimestamp().getTimeInMillis();
//				ScadaSnapshot scadaSnapshot = new ScadaSnapshot();
//				String readTag = item.getId();
//				Integer index = GetTagIndex(readTag);
//				JIVariant itemValue = itemState.getValue();
//				Object v = convertToNativeType(itemValue);
//				String value = v.toString().replace("[[", "").replace("]]", "");
//				if (index != null && index.intValue() != -1) {
//					String prefix = ((ScadaTagConfig) this.tagConfig.tags.get(index.intValue())).friendlyTag;
//					if (!prefix.equals(readTag)) {
//						scadaSnapshot.tagName = prefix + readTag;
//					} else {
//						scadaSnapshot.tagName = readTag;
//					}
//				} else {
//					scadaSnapshot.tagName = readTag;
//				}
//				scadaSnapshot.timestamp = currTimeStamp;
//				scadaSnapshot.quality = itemState.getQuality().shortValue();
//				scadaSnapshot.typeid = itemValue.getType();
//				scadaSnapshot.value = value;
//				scadaSnapshot.index = index.intValue();
//				ArrayList<ScadaSnapshot> values = new ArrayList<ScadaSnapshot>();
//				values.add(scadaSnapshot);
//				asyncSend(values);
//			} catch (Exception e) {
//				this.logger.log(Level.SEVERE, "Exception while processing the received data", e);
//			}
//		});
//	}
//
//	public void Configure() {
//		try {
//			OPCConfig opcConfig = this.scadaConfig.opcconfig;
//
//			this.conninfo = new ConnectionInformation();
//			this.conninfo.setHost(opcConfig.hostname);
//			this.conninfo.setDomain(opcConfig.domainname);
//			this.conninfo.setUser(opcConfig.username);
//			this.conninfo.setPassword(opcConfig.password);
//			this.conninfo.setProgId(opcConfig.progid);
//			this.conninfo.setClsid(opcConfig.clsid);
//			JISystem.setInBuiltLogHandler(false);
//
//			this.server = new Server(this.conninfo, Executors.newSingleThreadScheduledExecutor());
//			this.controller = new AutoReconnectController(this.server, 60000);
//			this.controller.connect();
//			AttachTags();
//			this.controller.addListener(this.connectionStateListener);
//			this.isConfigured = Boolean.valueOf(true);
//			println("Configured OPC DA Source");
//		}
//
//		catch (Exception ex)
//
//		{
//			println("Exception in configuring Data Source Connection");
//			this.isConfigured = Boolean.valueOf(false);
//		}
//	}
//
//	public void Connect() {
//		if (!this.isAttached) {
//			if (this.isConfigured.booleanValue() == true && !this.isConnected)
//				this.controller.connect();
//		}
//	}
//
//	public void Disconnect() {
//		DettachTags();
//		try {
//			if (this.server != null)
//				this.controller.disconnect();
//			this.isAttached = false;
//			this.isConnected = false;
//		} catch (Exception e) {
//			this.logger.log(Level.SEVERE, "Exception while closing server connection");
//		}
//	}
//
//	private void DettachTags() {
//		try {
//			if (this.accessBase != null) {
//
//				for (ScadaTagConfig eachTag : this.tagConfig.tags) {
//					this.accessBase.removeItem(eachTag.tag);
//				}
//				this.accessBase.removeStateListener(this.accessStateListener);
//				this.accessBase.unbind();
//				this.isAttached = false;
//			}
//		} catch (Exception e) {
//			this.logger.log(Level.SEVERE, "Exception while binding", e);
//		}
//		this.accessBase = null;
//	}
//}
