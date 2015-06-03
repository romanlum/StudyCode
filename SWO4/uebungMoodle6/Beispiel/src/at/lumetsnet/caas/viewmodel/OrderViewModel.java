package at.lumetsnet.caas.viewmodel;

import java.time.format.DateTimeFormatter;

import at.lumetsnet.caas.model.Order;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderViewModel {
	
	private StringProperty userNameProperty;
	private StringProperty menuProperty;
	private StringProperty timeProperty;
	private StringProperty commentProperty;
	
	public OrderViewModel(Order order) {
		userNameProperty = new SimpleStringProperty(order.getUser().getFirstName() + " " + order.getUser().getLastName());
		menuProperty = new SimpleStringProperty(order.getMenu().getDescription());
		timeProperty = new SimpleStringProperty(order.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
		commentProperty = new SimpleStringProperty(order.getComment());
	}
	/**
	 * @return the userNameProperty
	 */
	public StringProperty getUserNameProperty() {
		return userNameProperty;
	}
	/**
	 * @return the menuProperty
	 */
	public StringProperty getMenuProperty() {
		return menuProperty;
	}
	/**
	 * @return the timeProperty
	 */
	public StringProperty getTimeProperty() {
		return timeProperty;
	}
	/**
	 * @return the commentProperty
	 */
	public StringProperty getCommentProperty() {
		return commentProperty;
	}
	
	
}
