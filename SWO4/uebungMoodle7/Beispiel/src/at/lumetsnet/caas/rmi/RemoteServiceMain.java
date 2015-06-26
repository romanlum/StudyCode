package at.lumetsnet.caas.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import at.lumetsnet.caas.rmi.impl.MenuServiceImpl;
import at.lumetsnet.caas.rmi.impl.OrderServiceImpl;
import at.lumetsnet.caas.rmi.impl.UserServiceImpl;

/***
 * Rmi remote service class
 * 
 * @author romanlum
 *
 */
public class RemoteServiceMain {

	public static void main(String[] args) {
		try {
			String host = "localhost:1099";
			if (args.length == 1) {
				host = args[0];
			}

			LocateRegistry.createRegistry(getPort(host));
			registerSerice(new UserServiceImpl(), host, "CaasUserService");
			registerSerice(new MenuServiceImpl(), host, "CaasMenuService");
			registerSerice(new OrderServiceImpl(), host, "CaasOrderService");

		} catch (Exception ex) {
			System.out.println("Could not register services");
			ex.printStackTrace();
		}

	}

	/***
	 * Creates and exports the service
	 * 
	 * @param remote
	 * @param host
	 * @param name
	 */
	public static void registerSerice(Remote remote, String host, String name) {
		Remote serviceStub;
		try {
			serviceStub = UnicastRemoteObject.exportObject(remote, 0);
			Naming.rebind("rmi://" + host + "/" + name, serviceStub);
			System.out.println(name + " available on port " + "rmi://" + host
					+ "/" + name);
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Could not register service " + name);
			e.printStackTrace();
		}

	}

	/***
	 * Gets the port from host:port string Defaults to 1099 (RMI default port)
	 * 
	 * @param hostPort
	 * @return
	 */
	private static int getPort(String hostPort) {
		int idx = hostPort.lastIndexOf(':');
		if (idx == -1) {
			return 1099;
		} else {
			return Integer.parseInt(hostPort.substring(idx + 1));
		}
	}

}
