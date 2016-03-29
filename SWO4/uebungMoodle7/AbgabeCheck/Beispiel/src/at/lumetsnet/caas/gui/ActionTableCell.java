package at.lumetsnet.caas.gui;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/***
 * TableCell used for displaying edit / delete buttons inside a TableView
 * 
 * @author romanlum
 *
 * @param <S>
 * @param <T>
 */
public class ActionTableCell<S, T> extends TableCell<S, T> {
	protected HBox box = new HBox();
	protected Button editButton = new Button("Bearbeiten");
	protected Button deleteButton = new Button("Löschen");

	protected Consumer<T> editAction;
	protected Consumer<T> deleteAction;

	public ActionTableCell(Consumer<T> editAction, Consumer<T> deleteAction) {
		this.editAction = editAction;
		this.deleteAction = deleteAction;

		box.getStyleClass().add("table-command-container");
		editButton.getStyleClass()
				.addAll("table-command", "table-command-edit");
		deleteButton.getStyleClass().addAll("table-command",
				"table-command-delete");
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