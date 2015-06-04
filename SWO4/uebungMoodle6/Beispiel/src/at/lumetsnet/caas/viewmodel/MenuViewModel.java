package at.lumetsnet.caas.viewmodel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import at.lumetsnet.caas.model.Menu;

public class MenuViewModel implements Validatable {

	private LongProperty idProperty;
	private StringProperty descriptionProperty;
	private LongProperty priceProperty;
	private ObjectProperty<LocalDate> beginProperty;
	private ObjectProperty<LocalDate> endProperty;
	private ObjectProperty<MenuCategoryViewModel> categoryProperty;
	private StringProperty usageRangeProperty;
	private StringProperty priceAsStringProperty;

	public MenuViewModel(Menu data) {
		if (data == null) {
			data = new Menu();
			data.setId(-1);
		}
		idProperty = new SimpleLongProperty(data.getId());
		descriptionProperty = new SimpleStringProperty(data.getDescription());
		priceProperty = new SimpleLongProperty(data.getPrice());
		beginProperty = new SimpleObjectProperty<LocalDate>(data.getBegin());
		endProperty = new SimpleObjectProperty<LocalDate>(data.getEnd());
		categoryProperty = new SimpleObjectProperty<>(
				new MenuCategoryViewModel(data.getCategory()));
		if (data.getBegin() != null && data.getEnd() != null) {
			usageRangeProperty = new SimpleStringProperty(data.getBegin()
					.format(DateTimeFormatter.ISO_LOCAL_DATE)
					+ " - "
					+ data.getEnd().format(DateTimeFormatter.ISO_LOCAL_DATE));
		} else {
			usageRangeProperty = new SimpleStringProperty("");
		}
		priceAsStringProperty = new SimpleStringProperty(""
				+ (data.getPrice() / (double) 100));
	}

	public Menu toMenuModel() {
		Menu menu = new Menu();
		menu.setId(idProperty.get());
		menu.setBegin(beginProperty.get());
		menu.setEnd(endProperty.get());
		menu.setDescription(descriptionProperty.get());

		long amount = (long) (Double.parseDouble(priceAsStringProperty.get()) * 100);
		menu.setPrice(amount);
		menu.setCategory(categoryProperty.get().toCategoryModel());
		return menu;

	}

	/**
	 * @return the idProperty
	 */
	public LongProperty getIdProperty() {
		return idProperty;
	}

	/**
	 * @return the descriptionNameProperty
	 */
	public StringProperty getDescriptionProperty() {
		return descriptionProperty;
	}

	/**
	 * @return the priceProperty
	 */
	public LongProperty getPriceProperty() {
		return priceProperty;
	}

	/**
	 * @return the beginProperty
	 */
	public ObjectProperty<LocalDate> getBeginProperty() {
		return beginProperty;
	}

	/**
	 * @return the endProperty
	 */
	public ObjectProperty<LocalDate> getEndProperty() {
		return endProperty;
	}

	/**
	 * @return the categoryProperty
	 */
	public ObjectProperty<MenuCategoryViewModel> getCategoryProperty() {
		return categoryProperty;
	}

	/**
	 * @return the usageRange
	 */
	public StringProperty getUsageRangeProperty() {
		return usageRangeProperty;
	}

	/**
	 * @return the priceAsStringProperty
	 */
	public StringProperty getPriceAsStringProperty() {
		return priceAsStringProperty;
	}

	@Override
	public boolean validate() {
		boolean val = descriptionProperty.get() != null
				&& !descriptionProperty.get().isEmpty()
				&& beginProperty.get() != null && endProperty.get() != null
				&& categoryProperty.get() != null
				&& categoryProperty.get().getIdProperty().get() != -1;

		if (!val)
			return val;
		// extended validation of price
		try {
			Double.parseDouble(priceAsStringProperty.get());

		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

}
