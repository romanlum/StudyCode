package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class ManageEntityDialog<T> extends Dialog {

	protected boolean canceled = false;
	protected T viewModel;
	
	public boolean show() {
		dialogStage.showAndWait();
		return canceled;
	}
	
	protected abstract void saveCommand();
	protected abstract void cancelCommand();
	
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
