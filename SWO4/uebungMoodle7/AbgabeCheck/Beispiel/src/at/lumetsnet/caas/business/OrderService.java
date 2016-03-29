package at.lumetsnet.caas.business;

import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.rmi.interfaces.RemoteOrderService;

/***
 * Mock order business logic class Used as singleton, should be replaced with
 * real logic class
 * 
 * @author romanlum
 *
 */

public class OrderService extends RmiService<RemoteOrderService> {

	private static OrderService instance = null;

	private OrderService() {
		super("CaasOrderService");
	}

	/**
	 * singleton instance
	 */
	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}

	/***
	 * Fetches the orders of today
	 * 
	 * @return
	 */
	public Collection<Order> getTodaysOrders() {
		try {
			return service.getTodaysOrders();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
