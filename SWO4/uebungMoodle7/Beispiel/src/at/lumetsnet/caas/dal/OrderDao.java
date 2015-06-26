package at.lumetsnet.caas.dal;

import java.time.LocalDate;
import java.util.Collection;

import at.lumetsnet.caas.model.Order;

/***
 * Order dao 
 * @author romanlum
 *
 */

public interface OrderDao extends GenericDao<Order> {

	/***
	 * Gets the orders by date
	 * @param date
	 * @return
	 */
	Collection<Order> getOrdersByDate(LocalDate date);
}
