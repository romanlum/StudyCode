package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import at.lumetsnet.caas.viewmodel.LoginViewModel;

public class LoginDialog extends Dialog {

	private LoginViewModel viewModel;

	public LoginDialog(Window owner) {
		viewModel = new LoginViewModel();
		createGui(owner, "CaaS-Login",
				"Bitte geben Sie Ihren Benutzernamen und das Passwort ein.");

		dialogStage
				.getScene()
				.getStylesheets()
				.add(getClass().getResource("../css/login-dialog.css")
						.toExternalForm());
	}

	public boolean show() {
		dialogStage.showAndWait();
		return viewModel.getLoginResult();
	}

	@Override
	protected Node createContentPane() {

		VBox box = new VBox();
		box.getStyleClass().add("form-container");
		Label lab = new Label();
		lab.setText("Benutzername");
		lab.getStyleClass().add("form-label");
		box.getChildren().add(lab);

		TextField field = new TextField();
		field.getStyleClass().add("form-textfield");
		field.textProperty().bindBidirectional(viewModel.userNameProperty());
		box.getChildren().add(field);

		lab = new Label();
		lab.setText("Passwort");
		lab.getStyleClass().add("form-label");
		box.getChildren().add(lab);

		field = new PasswordField();
		field.textProperty().bindBidirectional(viewModel.passwordProperty());
		field.getStyleClass().add("form-textfield");
		box.getChildren().add(field);
		return box;
	}

	@Override
	protected Pane createBottomPane() {
		Pane pane = super.createBottomPane();
		Button loginButton = new Button();
		loginButton.setId("login-button");
		loginButton.setText("Login");
		loginButton.getStyleClass().add("command-button");
		loginButton.setOnAction(x -> loginCommand());
		pane.getChildren().add(loginButton);
		return pane;
	}

	/***
	 * Wrapper used because close cannot be initiated from the viewmodel
	 */
	private void loginCommand() {
		viewModel.login();
		dialogStage.close();
	}
}
