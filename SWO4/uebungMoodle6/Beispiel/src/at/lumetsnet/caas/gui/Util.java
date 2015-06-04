package at.lumetsnet.caas.gui;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Util {

	public static Node getFormLabel(String text) {
		Label lab = new Label();
		lab.setText(text);
		lab.getStyleClass().add("form-label");
		return lab;
	}

	public static Collection<Node> getTextFieldForm(String text,
			StringProperty property) {

		Collection<Node> nodes = new ArrayList<Node>();
		nodes.add(getFormLabel(text));

		TextField field = new TextField();
		field.getStyleClass().add("form-textfield");
		field.textProperty().bindBidirectional(property);
		nodes.add(field);
		return nodes;
	}

	public static Collection<Node> getPasswordFieldForm(String text,
			StringProperty property) {

		Collection<Node> nodes = new ArrayList<Node>();
		nodes.add(getFormLabel(text));

		PasswordField field = new PasswordField();
		field.getStyleClass().add("form-textfield");
		field.textProperty().bindBidirectional(property);
		nodes.add(field);
		return nodes;
	}

	public static <T> Collection<Node> getComboboxForm(String text,
			ObjectProperty<T> property, ObservableList<T> data) {

		Collection<Node> nodes = new ArrayList<Node>();
		nodes.add(getFormLabel(text));

		ComboBox<T> field = new ComboBox<>();
		field.getStyleClass().add("form-combobox");
		field.setItems(data);
		field.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<T>() {
					@Override
					public void changed(ObservableValue<? extends T> item,
							T arg1, T arg2) {
						property.setValue(item.getValue());

					}
				});
		field.selectionModelProperty().get().select(property.get());
		nodes.add(field);
		return nodes;
	}

}
