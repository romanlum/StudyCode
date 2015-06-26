package at.lumetsnet.caas.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import at.lumetsnet.caas.model.User;

/***
 * ViewModel wrapper for User entity Uses javafx properties for databinding
 * 
 * @author romanlum
 *
 */

public class UserViewModel implements Validatable {

	private LongProperty idProperty;
	private StringProperty userNameProperty;
	private StringProperty passwordProperty;
	private StringProperty firstNameProperty;
	private StringProperty lastNameProperty;
	private BooleanProperty lockedProperty;

	public UserViewModel(User user) {
		if (user == null) {
			user = new User();
			user.setId(-1);
		}
		idProperty = new SimpleLongProperty(user.getId());
		userNameProperty = new SimpleStringProperty(user.getUserName());
		passwordProperty = new SimpleStringProperty(user.getPassword());
		firstNameProperty = new SimpleStringProperty(user.getFirstName());
		lastNameProperty = new SimpleStringProperty(user.getLastName());
		lockedProperty = new SimpleBooleanProperty(user.isLocked());

	}

	public User toUserModel() {
		User result = new User();
		result.setId(idProperty.get());
		result.setUserName(userNameProperty.get());
		result.setPassword(passwordProperty.get());
		result.setFirstName(firstNameProperty.get());
		result.setLastName(lastNameProperty.get());
		result.setLocked(lockedProperty.get());
		return result;

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
		return new SimpleStringProperty(lockedProperty.get() ? "Gesperrt"
				: "Ok");
	}

	@Override
	public boolean validate() {
		return userNameProperty.get() != null
				&& !userNameProperty.get().isEmpty()
				&& passwordProperty.get() != null
				&& !passwordProperty.get().isEmpty()
				&& firstNameProperty.get() != null
				&& !firstNameProperty.get().isEmpty()
				&& lastNameProperty.get() != null
				&& !lastNameProperty.get().isEmpty();

	}

}
