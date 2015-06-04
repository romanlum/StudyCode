package at.lumetsnet.caas.gui.dialogs;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import at.lumetsnet.caas.viewmodel.Validatable;

public abstract class ManageEntityDialog<T> extends Dialog {

	protected boolean canceled = false;
	protected T viewModel;
	protected BooleanProperty validationErrorProperty = new SimpleBooleanProperty();

	public boolean show() {
		dialogStage.showAndWait();
		return canceled;
	}

	protected abstract void saveCommand();

	protected abstract void cancelCommand();

	protected boolean validate() {
		if (!(viewModel instanceof Validatable)) {
			return true;
		}

		boolean result = ((Validatable) viewModel).validate();
		if (!result) {
			ErrorDialog.show(dialogStage);
		}
		return result;
	}

	@Override
	protected Pane createBottomPane() {
		Pane pane = super.createBottomPane();
		Button button = new Button();
		button.setText("Speichern");
		button.getStyleClass().add("command-button");
		button.setOnAction(x -> saveCommand());
		pane.getChildren().add(button);

		button = new Button();
		button.setText("Abbrechen");
		button.getStyleClass().add("command-button");
		button.setOnAction(x -> cancelCommand());
		pane.getChildren().add(button);

		return pane;
	}

}
