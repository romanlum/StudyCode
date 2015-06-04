package at.lumetsnet.caas.gui.pages;

import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import at.lumetsnet.caas.gui.ManageUserActionCell;
import at.lumetsnet.caas.gui.dialogs.ManageUserDialog;
import at.lumetsnet.caas.viewmodel.ManageUsersViewModel;
import at.lumetsnet.caas.viewmodel.UserViewModel;

public class ManageUsersPage extends VBox implements Showable {

	TableView<UserViewModel> table;
	ManageUsersViewModel viewModel;

	public ManageUsersPage() {

		viewModel = new ManageUsersViewModel();
		getStyleClass().add("page-content-container");

		HBox topPane = new HBox();
		topPane.getChildren().add(createTitle("Benutzerliste"));
		HBox commandContainer = new HBox();
		commandContainer.getStyleClass().add("page-command-container");
		commandContainer.getChildren().add(createAddButton());
		HBox.setHgrow(commandContainer, Priority.ALWAYS);

		topPane.getChildren().add(commandContainer);
		table = createTableView();
		table.setItems(viewModel.getUserList());
		getChildren().addAll(topPane, table);

	}

	private Node createAddButton() {
		Button button = new Button("User anlegen");
		button.getStyleClass().add("page-command");
		button.setOnAction(x -> addUserCommand());
		return button;
	}

	private Label createTitle(String title) {
		Label titleLabel = new Label(title);
		titleLabel.getStyleClass().add("page-title");
		return titleLabel;
	}

	private TableView<UserViewModel> createTableView() {
		TableView<UserViewModel> table = new TableView<>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<UserViewModel, String> column = new TableColumn<>(
				"Benutzername");
		column.setCellValueFactory(x -> x.getValue().getUserNameProperty());
		table.getColumns().add(column);

		column = new TableColumn<>("Vorname");
		column.setCellValueFactory(x -> x.getValue().getFirstNameProperty());
		table.getColumns().add(column);

		column = new TableColumn<>("Nachname");
		column.setCellValueFactory(x -> x.getValue().getLastNameProperty());
		table.getColumns().add(column);

		column = new TableColumn<>("Status");
		column.setCellValueFactory(x -> x.getValue().getLockedStringProperty());
		table.getColumns().add(column);

		TableColumn<UserViewModel, Number> actionColumn = new TableColumn<>(
				"Aktion");
		actionColumn.setCellValueFactory(x -> x.getValue().getIdProperty());
		actionColumn.setCellFactory(p -> new ManageUserActionCell<>(
				x -> editCommand(x), x -> deleteCommand(x),
				x -> toggleLockStateCommand(x)));

		table.getColumns().add(actionColumn);
		return table;
	}

	private void toggleLockStateCommand(Number x) {
		viewModel.toggleLockState(x);
	}

	private void deleteCommand(Number x) {
		viewModel.deleteCommand(x);

	}

	private void editCommand(Number id) {
		Optional<UserViewModel> model = table.getItems().stream()
				.filter(x -> x.getIdProperty().get() == (long) id).findFirst();

		if (model.isPresent()) {
			boolean result = ManageUserDialog.show(getScene().getWindow(),
					model.get().toUserModel());
			if (!result) {
				viewModel.update();
			}
		}
	}

	private void addUserCommand() {
		boolean result = ManageUserDialog.show(getScene().getWindow(), null);
		if (!result) {
			viewModel.update();
		}
	}

	public void show() {
		viewModel.update();
	}
}
