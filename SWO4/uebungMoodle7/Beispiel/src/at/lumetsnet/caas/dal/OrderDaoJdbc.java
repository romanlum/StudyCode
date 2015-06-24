package at.lumetsnet.caas.dal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import at.lumetsnet.caas.model.Order;

public class OrderDaoJdbc extends GenericJdbcDao<Order> implements OrderDao {

	public OrderDaoJdbc(String conString, String userName, String password) {
		super(Order.class, "Order", conString, userName, password);
	}
	
	public Collection<Order> getOrdersByDate(LocalDate date) {
		return getFromWhere("WHERE DATE(time) = ?",  Date.valueOf(date));
	}

	@Override
	public Collection<String> getPropertyFilter() {
		Collection<String> result = super.getPropertyFilter();
		result.add("user");
		result.add("menu");
		return result;
	}

}
