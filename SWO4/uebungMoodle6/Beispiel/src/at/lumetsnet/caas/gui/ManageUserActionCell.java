package at.lumetsnet.caas.gui;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import at.lumetsnet.caas.viewmodel.UserViewModel;

public class ManageUserActionCell<S, T> extends ActionTableCell<S, T> {

	protected Button lockButton = new Button("Sperren");
	protected Consumer<T> lockAction;

	public ManageUserActionCell(Consumer<T> editAction,
			Consumer<T> deleteAction, Consumer<T> lockAction) {
		super(editAction, deleteAction);
		this.lockAction = lockAction;
		lockButton.getStyleClass().addAll("cell-command", "lock-cell-command");
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
					if (((UserViewModel) model).getLockedProperty().get()) {
						lockButton.setText("Entsperren");
					} else {
						lockButton.setText("Sperren");
					}
				}
			}

		}
		super.updateItem(entity, empty);
	}

}
