package at.lumetsnet.caas.business;

import java.rmi.Naming;

import at.lumetsnet.caas.rmi.interfaces.RemoteUserService;

/***
 * Base class for all services using RMI
 * @author romanlum
 *
 */
public class Service<T> {

	protected T service;
	
	public Service(String serviceHost, String name) {
		try {
			service = (T) Naming.lookup("rmi://" + serviceHost + "/"+name);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
