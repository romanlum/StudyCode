package at.lumetsnet.caas.business;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.model.User;
import at.lumetsnet.caas.rmi.interfaces.RemoteOrderService;

/***
 * Mock order business logic class
 * Used as singleton, should be replaced with 
 * real logic class
 * @author romanlum
 *
 */

public class OrderService extends Service<RemoteOrderService> {

	private static OrderService instance = null;

	private OrderService() {
		super("localhost:1931","CaasOrderService");
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
	 * @return
	 */
	public Collection<Order> getTodaysOrders() {
		try {
			return service.getTodaysOrders();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
