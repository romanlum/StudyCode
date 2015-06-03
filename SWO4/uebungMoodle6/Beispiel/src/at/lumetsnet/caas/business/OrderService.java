package at.lumetsnet.caas.business;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.model.User;

public class OrderService {

	private static OrderService instance = null;
	
	private OrderService() {
		
	}
	
	/**
     * 
     */
    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }
	
	public Collection<Order> getTodaysOrders() {
		List<Order> orders  =new ArrayList<Order>();
		
		User user = new User(1,"romanlum","","Roman", "Lumetsberger",false,true);
		Menu menu = new Menu(1,"Wienerschnitzel mit Pommes",0,null,null,new MenuCategory());
		
		orders.add(new Order(1,menu,user,LocalDateTime.now(),"ohne Preiselbeeren"));
		orders.add(new Order(1,menu,user,LocalDateTime.now(),"mit Preiselbeeren"));
		
		return orders;
	}
}
