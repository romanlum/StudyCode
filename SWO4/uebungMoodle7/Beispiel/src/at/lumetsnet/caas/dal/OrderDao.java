package at.lumetsnet.caas.dal;

import java.time.LocalDate;
import java.util.Collection;

import at.lumetsnet.caas.model.Order;

public interface OrderDao extends GenericDao<Order> {

	Collection<Order> getOrdersByDate(LocalDate date);
}
