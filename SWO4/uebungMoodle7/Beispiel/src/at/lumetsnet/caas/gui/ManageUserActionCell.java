package at.lumetsnet.caas.gui;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import at.lumetsnet.caas.viewmodel.UserViewModel;

/***
 * TableCell used for displaying additional lock/unlock
 * button inside the TableView
 * @author romanlum
 *
 * @param <S>
 * @param <T>
 */
public class ManageUserActionCell<S, T> extends ActionTableCell<S, T> {

	protected Button lockButton = new Button("Sperren");
	protected Consumer<T> lockAction;

	public ManageUserActionCell(Consumer<T> editAction,
			Consumer<T> deleteAction, Consumer<T> lockAction) {
		super(editAction, deleteAction);
		this.lockAction = lockAction;
		box.getChildren().addAll(lockButton);
	}

	@Override
	protected void updateItem(T entity, boolean empty) {

		if (entity != null) {
			lockButton.setOnAction(x -> {
				if (lockAction != null)
					lockAction.accept(entity);

			});
			if (getTableRow() != null && getTableRow().getItem() != null) {
				Object model = getTableRow().getItem();
				if (model instanceof UserViewModel) {
					lockButton.getStyleClass().removeIf(
							x -> x.startsWith("table-command"));
					if (((UserViewModel) model).getLockedProperty().get()) {
						lockButton.setText("Entsperren");
						lockButton.getStyleClass().addAll("table-command",
								"table-command-unlock");
					} else {
						lockButton.setText("Sperren");
						lockButton.getStyleClass().addAll("table-command",
								"table-command-lock");
					}
				}
			}

		}
		super.updateItem(entity, empty);
	}

}
