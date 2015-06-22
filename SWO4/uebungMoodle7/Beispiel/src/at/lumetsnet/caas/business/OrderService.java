package at.lumetsnet.caas.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.model.User;

/***
 * Mock order business logic class
 * Used as singleton, should be replaced with 
 * real logic class
 * @author romanlum
 *
 */

public class OrderService {

	private static OrderService instance = null;

	private OrderService() {

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
		List<Order> orders = new ArrayList<Order>();

		User user = new User(1, "romanlum", "", "Roman", "Lumetsberger", false);

		Menu menu = new Menu(1, "Wienerschnitzel mit Pommes", 0, null, null,
				new MenuCategory());

		LocalDateTime time = LocalDateTime.now();
		
		orders.add(new Order(1, menu, user,time ,
				"ohne Preiselbeeren"));
		time = time.minusHours(1);
		user = new User(1, "christophlum", "", "Christoph", "Lumetsberger", false);
		orders.add(new Order(1, menu, user,time,
				"mit Preiselbeeren"));
		time = time.minusMinutes(1);
		user = new User(1, "romanlum", "", "Moe", "Sislec", false);
		orders.add(new Order(1, menu, user,time,
				"normal"));
		return orders;
	}
}
