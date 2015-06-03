package at.lumetsnet.caas.viewmodel;

import at.lumetsnet.caas.model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserViewModel {

	private LongProperty idProperty;
	private StringProperty userNameProperty;
	private StringProperty passwordProperty;
	private StringProperty firstNameProperty;
	private StringProperty lastNameProperty;
	private BooleanProperty lockedProperty;
	private BooleanProperty isAdminProperty;
	
	public UserViewModel(User user) {
		idProperty = new SimpleLongProperty(user.getId());
		userNameProperty = new SimpleStringProperty(user.getUserName());
		passwordProperty = new SimpleStringProperty(user.getPassword());
		firstNameProperty = new SimpleStringProperty(user.getFirstName());
		lastNameProperty = new SimpleStringProperty(user.getLastName());
		lockedProperty = new SimpleBooleanProperty(user.isLocked());
		isAdminProperty = new SimpleBooleanProperty(user.isAdmin());
		
	}
	/**
	 * @return the id
	 */
	public LongProperty getIdProperty() {
		return idProperty;
	}
	/**
	 * @return the userNameProperty
	 */
	public StringProperty getUserNameProperty() {
		return userNameProperty;
	}
	/**
	 * @return the passwordProperty
	 */
	public StringProperty getPasswordProperty() {
		return passwordProperty;
	}
	/**
	 * @return the firstNameProperty
	 */
	public StringProperty getFirstNameProperty() {
		return firstNameProperty;
	}
	/**
	 * @return the lastNameProperty
	 */
	public StringProperty getLastNameProperty() {
		return lastNameProperty;
	}
	/**
	 * @return the lockedProperty
	 */
	public BooleanProperty getLockedProperty() {
		return lockedProperty;
	}
	
	/**
	 * @return the lockedProperty as string
	 */
	public StringProperty getLockedStringProperty() {
		return new SimpleStringProperty(lockedProperty.get() ? "Locked" : "OK");
	}
	/**
	 * @return the isAdminProperty
	 */
	public BooleanProperty getIsAdminProperty() {
		return isAdminProperty;
	}
	
	
	
	
	
}
