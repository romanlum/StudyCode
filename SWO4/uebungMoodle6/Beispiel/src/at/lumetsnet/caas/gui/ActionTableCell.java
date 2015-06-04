package at.lumetsnet.caas.gui;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionTableCell<S, T> extends TableCell<S, T> {
	protected HBox box = new HBox();
	protected Button editButton = new Button("Bearbeiten");
	protected Button deleteButton = new Button("Löschen");

	protected Consumer<T> editAction;
	protected Consumer<T> deleteAction;

	public ActionTableCell(Consumer<T> editAction, Consumer<T> deleteAction) {
		this.editAction = editAction;
		this.deleteAction = deleteAction;
		box.getStyleClass().add("cell-command-container");
		editButton.getStyleClass().addAll("cell-command", "edit-cell-command");
		deleteButton.getStyleClass().addAll("cell-command",
				"delete-cell-command");
		box.getChildren().addAll(editButton, deleteButton);
	}

	@Override
	protected void updateItem(T entity, boolean empty) {

		if (entity != null) {
			editButton.setOnAction(x -> {
				if (editAction != null)
					editAction.accept(entity);

			});
			deleteButton.setOnAction(x -> {
				if (deleteAction != null)
					deleteAction.accept(entity);
			});
			setGraphic(box);
		} else {
			setGraphic(null);
		}
	}
}