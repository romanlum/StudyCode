package at.lumetsnet.caas.gui;

import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import at.lumetsnet.caas.gui.pages.ManageMenusPage;
import at.lumetsnet.caas.gui.pages.ManageUsersPage;
import at.lumetsnet.caas.gui.pages.OrdersPage;
import at.lumetsnet.caas.gui.pages.Showable;
import at.lumetsnet.caas.model.User;

/***
 * Main windows class Creates all pages and manages page switching
 * 
 * @author romanlum
 *
 */
public class MainWindow {

	private enum VIEW_TYPE {
		ADMIN_ORDERS, ADMIN_USERS, ADMIN_MENUS
	}

	private User user;
	private Stage stage = new Stage();
	private HashMap<VIEW_TYPE, Pane> pages;
	private BorderPane container;

	private Button adminOrdersViewButton;
	private Button adminUsersViewButton;
	private Button adminMenusViewButton;

	public MainWindow(Window owner, User user) {
		this.user = user;
		container = new BorderPane();
		container.getStyleClass().add("window-container");
		container.setTop(createTopMenu());

		pages = createPages();

		Scene scene = new Scene(container);
		scene.getStylesheets().add(
				getClass().getResource("css/main-window.css").toExternalForm());

		stage.initOwner(owner);
		stage.setScene(scene);
		stage.setTitle("CaaS");
		// start with 1024/768
		stage.setWidth(1024);
		stage.setHeight(768);
		switchView(VIEW_TYPE.ADMIN_ORDERS);

	}

	private HashMap<VIEW_TYPE, Pane> createPages() {
		HashMap<VIEW_TYPE, Pane> pages = new HashMap<>();
		pages.put(VIEW_TYPE.ADMIN_ORDERS, new OrdersPage());
		pages.put(VIEW_TYPE.ADMIN_USERS, new ManageUsersPage());
		pages.put(VIEW_TYPE.ADMIN_MENUS, new ManageMenusPage());

		return pages;
	}

	public void show() {

		stage.show();
	}

	private Node createTopMenu() {
		HBox topContainer = new HBox();
		topContainer.getStyleClass().add("nav-container");

		Label iconLabel = new Label();
		iconLabel.setId("nav-top-icon");
		VBox descBox = new VBox();

		Label userNameLabel = new Label();
		userNameLabel.getStyleClass().add("nav-username");
		userNameLabel.setText(user.getFirstName() + " " + user.getLastName());

		Label descLabel = new Label();
		descLabel.getStyleClass().add("nav-role");
		descLabel.setText("Administrator");

		descBox.getChildren().add(userNameLabel);
		descBox.getChildren().add(descLabel);

		HBox commandBox = new HBox();
		commandBox.getStyleClass().add("nav-command-container");

		adminOrdersViewButton = new Button();
		adminOrdersViewButton.getStyleClass().add("nav-command");
		adminOrdersViewButton.setText("Bestellungen");
		adminOrdersViewButton
				.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_ORDERS));
		commandBox.getChildren().add(adminOrdersViewButton);

		adminUsersViewButton = new Button();
		adminUsersViewButton.getStyleClass().add("nav-command");
		adminUsersViewButton.setText("Benutzerverwaltung");
		adminUsersViewButton
				.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_USERS));
		commandBox.getChildren().add(adminUsersViewButton);

		adminMenusViewButton = new Button();
		adminMenusViewButton.getStyleClass().add("nav-command");
		adminMenusViewButton.setText("Speisekarte verwalten");
		adminMenusViewButton
				.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_MENUS));
		commandBox.getChildren().add(adminMenusViewButton);

		HBox.setHgrow(commandBox, Priority.ALWAYS);

		topContainer.getChildren().add(iconLabel);
		topContainer.getChildren().add(descBox);
		topContainer.getChildren().add(commandBox);
		return topContainer;
	}

	/***
	 * Switches the view
	 * 
	 * @param type
	 */
	private void switchView(VIEW_TYPE type) {
		adminOrdersViewButton.getStyleClass().remove("nav-command-selected");
		adminUsersViewButton.getStyleClass().remove("nav-command-selected");
		adminMenusViewButton.getStyleClass().remove("nav-command-selected");

		Button selected = null;
		switch (type) {
		case ADMIN_USERS:
			selected = adminUsersViewButton;

			break;
		case ADMIN_ORDERS:
			selected = adminOrdersViewButton;
			break;
		case ADMIN_MENUS:
			selected = adminMenusViewButton;
			break;
		}
		if (selected != null)
			selected.getStyleClass().add("nav-command-selected");

		Pane page = pages.get(type);
		if (page instanceof Showable)
			((Showable) page).show();
		container.setCenter(page);

	}

}
