package at.lumetsnet.caas.gui.pages;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import at.lumetsnet.caas.gui.ActionCellFactory;
import at.lumetsnet.caas.viewmodel.ManageUsersViewModel;
import at.lumetsnet.caas.viewmodel.UserViewModel;

public class ManageUsersPage extends VBox implements Showable {

	TableView<UserViewModel> table;
	ManageUsersViewModel viewModel;

	public ManageUsersPage() {

		viewModel = new ManageUsersViewModel();
		getStyleClass().add("page-content-container");

		Label title = createTitle("Benutzerliste");
		getChildren().add(title);

		table = createTableView();
		table.setItems(viewModel.getUserList());
		getChildren().add(table);

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

		TableColumn<UserViewModel, Number> actionColumn = new TableColumn<>("Aktion");
		actionColumn.setCellValueFactory(x->x.getValue().getIdProperty());
		actionColumn.setCellFactory(new ActionCellFactory<>(x -> editCommand(x), x -> deleteCommand(x)));
		table.getColumns().add(actionColumn);
		return table;
	}

	private Void deleteCommand(Number x) {
		viewModel.deleteCommand(x);
		return null;
	}

	private Void editCommand(Number x) {
		System.out.println(x);
		return null;
	}

	public void show() {
		viewModel.update();
	}
}
