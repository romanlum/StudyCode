package at.lumetsnet.caas.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.Order;

public interface RemoteOrderService extends Remote{
	
	/***
	 * Fetches the orders of today
	 * @return
	 */
	public Collection<Order> getTodaysOrders() throws RemoteException;
		
}
