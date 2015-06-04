package at.lumetsnet.caas.viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import at.lumetsnet.caas.model.MenuCategory;

public class MenuCategoryViewModel {

	private LongProperty idProperty;
	private StringProperty nameProperty;
	

	public MenuCategoryViewModel(MenuCategory input) {
		if(input == null) {
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
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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