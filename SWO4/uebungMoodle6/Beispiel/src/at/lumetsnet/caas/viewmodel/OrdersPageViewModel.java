package at.lumetsnet.caas.viewmodel;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.lumetsnet.caas.business.OrderService;
import at.lumetsnet.caas.model.Order;

public class OrdersPageViewModel {

	private ObservableList<OrderViewModel> orders;

	public OrdersPageViewModel() {
		orders = FXCollections.observableArrayList();
	}

	public void update() {
		Collection<Order> orderData = OrderService.getInstance()
				.getTodaysOrders();
		orders.clear();
		if (orderData != null) {
			orderData.forEach(x -> orders.add(new OrderViewModel(x)));
		}
	}

	public ObservableList<OrderViewModel> getOrders() {
		return orders;
	}
}
