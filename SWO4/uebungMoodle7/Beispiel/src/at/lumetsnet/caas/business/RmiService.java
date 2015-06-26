package at.lumetsnet.caas.business;

import java.rmi.Naming;
import java.rmi.Remote;

/***
 * Base class for all services using RMI
 * @author romanlum
 *
 */
public class RmiService<T extends Remote> {

	protected T service;
	protected String serviceHost;
	protected String name;
	/**
	 * Create a service with the given name
	 * @param name
	 */
	public RmiService(String name) {
		this.name = name;
	}
	
	/**
	 * Initializes the service
	 * @param serviceHost
	 * @return
	 */
	public boolean initialize(String serviceHost) {
		try {
			service = (T) Naming.lookup("rmi://" + serviceHost + "/"+name);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
