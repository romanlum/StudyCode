package at.lumetsnet.caas.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import at.lumetsnet.caas.rmi.impl.MenuServiceImpl;
import at.lumetsnet.caas.rmi.impl.OrderServiceImpl;
import at.lumetsnet.caas.rmi.impl.UserServiceImpl;
import at.lumetsnet.caas.rmi.interfaces.RemoteMenuService;
import at.lumetsnet.caas.rmi.interfaces.RemoteOrderService;
import at.lumetsnet.caas.rmi.interfaces.RemoteUserService;

public class RemoteService {

	public static void main(String[] args) {
		// version2:
		try {
			LocateRegistry.createRegistry(1931);
			RemoteUserService userService = new UserServiceImpl();
			RemoteMenuService menuService = new MenuServiceImpl();
			RemoteOrderService orderService = new OrderServiceImpl();
			Remote serviceStub = UnicastRemoteObject.exportObject(userService,
					0); // 0 nimm registry port
			
			Naming.rebind("rmi://" + "localhost:1931" + "/CaasUserService",
					serviceStub);
			
			serviceStub = UnicastRemoteObject.exportObject(menuService,
					0); // 0 nimm registry port
			
			Naming.rebind("rmi://" + "localhost:1931" + "/CaasMenuService",
					serviceStub);
			
			serviceStub = UnicastRemoteObject.exportObject(orderService,
					0); // 0 nimm registry port
			
			Naming.rebind("rmi://" + "localhost:1931" + "/CaasOrderService",
					serviceStub);


			System.out.println("Service available on port " + "rmi://"
					+ "loacalhost:1931" + "/CaasUserService");
			System.out.println("Service available on port " + "rmi://"
					+ "loacalhost:1931" + "/CaasMenuService");
			System.out.println("Service available on port " + "rmi://"
					+ "loacalhost:1931" + "/CaasOrderService");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
