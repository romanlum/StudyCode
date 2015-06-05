package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/***
 * Own dialog class used because 
 * javafx does not have dialogs before update 40
 * :(
 * @author romanlum
 *
 */
public class ErrorDialog extends Dialog {

	public ErrorDialog(Window owner) {

		createGui(owner, "Fehler", "Bitte korrigieren Sie Ihre Eingaben.");

		dialogStage
				.getScene()
				.getStylesheets()
				.add(getClass().getResource("../css/error-dialog.css")
						.toExternalForm());
	}

	@Override
	protected Pane createBottomPane() {
		Pane pane = super.createBottomPane();
		Button button = new Button();
		button.setText("OK");
		button.getStyleClass().add("command-button");
		button.setOnAction(x -> okCommand());
		pane.getChildren().add(button);
		return pane;
	}

	private void okCommand() {
		dialogStage.close();
	}

	@Override
	protected Node createContentPane() {
		return null;
	}

	public boolean show() {
		dialogStage.showAndWait();
		return true;
	}

	public static boolean show(Window owner) {
		ErrorDialog dialog = new ErrorDialog(owner);
		return dialog.show();

	}
}
