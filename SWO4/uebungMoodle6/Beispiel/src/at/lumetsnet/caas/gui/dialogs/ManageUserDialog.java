package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import at.lumetsnet.caas.business.UserService;
import at.lumetsnet.caas.gui.Util;
import at.lumetsnet.caas.model.User;
import at.lumetsnet.caas.viewmodel.UserViewModel;

public class ManageUserDialog extends ManageEntityDialog<UserViewModel> {

	public ManageUserDialog(Window owner, User user) {
		viewModel = new UserViewModel(user);
		createGui(owner, "User verwalten",
				"Bitte geben Sie die Daten des Benutzers ein.");

		dialogStage
				.getScene()
				.getStylesheets()
				.add(getClass().getResource("../css/manage-user-dialog.css")
						.toExternalForm());
	}

	public boolean show() {
		dialogStage.showAndWait();
		return canceled;
	}

	@Override
	protected Node createContentPane() {

		VBox box = new VBox();
		box.getStyleClass().add("form-container");
		box.getChildren().addAll(
				Util.getTextFieldForm("Benutzername", viewModel
						.getUserNameProperty()));
		box.getChildren().addAll(
				Util.getTextFieldForm("Passwort", viewModel
						.getPasswordProperty()));
		box.getChildren().addAll(
				Util.getTextFieldForm("Vorname", viewModel
						.getFirstNameProperty()));
		box.getChildren().addAll(
				Util.getTextFieldForm("Nachname", viewModel
						.getLastNameProperty()));

		return box;
	}

	
	@Override
	protected void saveCommand() {
		UserService.getInstance().saveOrUpdate(viewModel.toUserModel());
		dialogStage.close();
	}

	
	@Override
	protected  void cancelCommand() {
		canceled = true;
		dialogStage.close();
	}

	public static boolean show(Window owner, User model) {
		ManageUserDialog dialog = new ManageUserDialog(owner, model);
		return dialog.show();

	}
}
