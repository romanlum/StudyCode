package at.lumetsnet.caas.viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import at.lumetsnet.caas.model.MenuCategory;

/***
 * ViewModel wrapper for MenuCategory entity
 * Uses javafx properties for databinding
 * @author romanlum
 *
 */
public class MenuCategoryViewModel implements Validatable {

	private LongProperty idProperty;
	private StringProperty nameProperty;

	public MenuCategoryViewModel(MenuCategory input) {
		if (input == null) {
			input = new MenuCategory();
			input.setId(-1);
		}
		idProperty = new SimpleLongProperty(input.getId());
		nameProperty = new SimpleStringProperty(input.getName());

	}

	public MenuCategory toCategoryModel() {
		MenuCategory result = new MenuCategory();
		result.setId(idProperty.get());
		result.setName(nameProperty.get());
		return result;

	}

	public boolean validate() {
		return nameProperty.get() != null && !nameProperty.get().isEmpty();
	}

	@Override
	public String toString() {
		return getNameProperty().get();
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
	public StringProperty getNameProperty() {
		return nameProperty;
	}

}
