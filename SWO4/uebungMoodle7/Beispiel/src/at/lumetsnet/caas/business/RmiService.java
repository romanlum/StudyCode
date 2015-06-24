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
	
	public RmiService(String serviceHost, String name) {
		try {
			service = (T) Naming.lookup("rmi://" + serviceHost + "/"+name);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
