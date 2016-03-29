package at.lumetsnet.caas.gui.dialogs;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import at.lumetsnet.caas.viewmodel.Validatable;

/***
 * Base dialog used for editing a viewmodel entity
 * 
 * @author romanlum
 *
 * @param <T>
 */
public abstract class ManageEntityDialog<T> extends Dialog {

	protected boolean canceled = false;
	protected T viewModel;
	protected BooleanProperty validationErrorProperty = new SimpleBooleanProperty();

	/***
	 * Shows the dialog and waits for exit returns if the action was canceled
	 * 
	 * @return
	 */
	public boolean show() {
		dialogStage.showAndWait();
		return canceled;
	}

	/***
	 * Save action
	 */
	protected abstract void saveCommand();

	/***
	 * Cancel action Default: closes the dialog and sets canceled flag
	 */
	protected void cancelCommand() {
		canceled = true;
		dialogStage.close();

	}

	/***
	 * Validates the viewmodel if it is Validatable Shows error dialog if
	 * validation fails
	 * 
	 * @return true if the viewmodel is not validatable true on successfull
	 *         validation otherwise false
	 */
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
