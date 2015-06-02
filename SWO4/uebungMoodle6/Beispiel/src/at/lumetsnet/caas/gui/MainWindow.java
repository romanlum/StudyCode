package at.lumetsnet.caas.gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import at.lumetsnet.caas.model.User;

public class MainWindow {
	
	private enum VIEW_TYPE {
		ADMIN_ORDERS,
		ADMIN_USERS,
		ADMIN_MENUS,
		USER_ORDER
	}
	
	private User user;
	private Stage stage = new Stage();
	
	private Button adminOrdersViewButton;
	private Button adminUsersViewButton;
	private Button adminMenusViewButton;
	private Button userOrderViewButton;
	
	public MainWindow(Window owner, User user) {
		this.user = user;
		BorderPane container=new BorderPane();
		container.getStyleClass().add("window-container");
		container.setTop(createTopMenu());
		
		Scene scene = new Scene(container);
		scene.getStylesheets().add(getClass().getResource("../gui/css/main-window.css")
                .toExternalForm());
		
		stage.initOwner(owner);
		stage.setScene(scene);
		stage.setTitle("CaaS");
		
	}
	
	public void show() {
		stage.show();
	}

	private Node createTopMenu() {
		HBox topContainer=new HBox();
		topContainer.getStyleClass().add("nav-container");
		
		Label iconLabel = new Label();
		iconLabel.setId("nav-top-icon");
		VBox descBox = new VBox();
		
		Label userNameLabel = new Label();
		userNameLabel.getStyleClass().add("nav-username");
		userNameLabel.setText(user.getFirstName() + " "+user.getLastName());
				
		Label descLabel = new Label();
		descLabel.getStyleClass().add("nav-role");
		descLabel.setText(user.isAdmin() ? "Administrator" :"User");
		
		descBox.getChildren().add(userNameLabel);
		descBox.getChildren().add(descLabel);
		
		HBox commandBox = new HBox();
		commandBox.getStyleClass().add("nav-command-container");
		
		if(user.isAdmin()) {
			adminOrdersViewButton = new Button();
			adminOrdersViewButton.getStyleClass().add("nav-command");
			adminOrdersViewButton.setText("Bestellungen");
			adminOrdersViewButton.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_ORDERS));
			commandBox.getChildren().add(adminOrdersViewButton);
			
			adminUsersViewButton = new Button();
			adminUsersViewButton.getStyleClass().add("nav-command");
			adminUsersViewButton.setText("Benutzerverwaltung");
			adminUsersViewButton.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_USERS));
			commandBox.getChildren().add(adminUsersViewButton);
			
			adminMenusViewButton = new Button();
			adminMenusViewButton.getStyleClass().add("nav-command");
			adminMenusViewButton.setText("Speisekarte verwalten");
			adminMenusViewButton.setOnAction(x -> switchView(VIEW_TYPE.ADMIN_MENUS));
			commandBox.getChildren().add(adminMenusViewButton);
		} else {
			userOrderViewButton = new Button();
			userOrderViewButton.getStyleClass().add("nav-command");
			userOrderViewButton.setText("Bestellung aufgeben");
			userOrderViewButton.setOnAction(x -> switchView(VIEW_TYPE.USER_ORDER));
			commandBox.getChildren().add(userOrderViewButton);
		}

		HBox.setHgrow(commandBox, Priority.ALWAYS);
		
		topContainer.getChildren().add(iconLabel);
		topContainer.getChildren().add(descBox);
		topContainer.getChildren().add(commandBox);
		return topContainer;
	}

	private void switchView(VIEW_TYPE type) {
		if(user.isAdmin()) {
			adminOrdersViewButton.getStyleClass().remove("nav-command-selected");
			adminUsersViewButton.getStyleClass().remove("nav-command-selected");
			adminMenusViewButton.getStyleClass().remove("nav-command-selected");
		} else  {
			userOrderViewButton.getStyleClass().remove("nav-command-selected");
		}
		
		Button selected = null;
		switch(type) {
			case ADMIN_USERS:
				selected = adminUsersViewButton;
				break;
			case ADMIN_ORDERS:
				selected = adminOrdersViewButton;
				break;
			case ADMIN_MENUS:
				selected = adminMenusViewButton;
				break;
			case USER_ORDER:
				selected = userOrderViewButton;
				break;
		}
		if(selected != null)
			selected.getStyleClass().add("nav-command-selected");
	}

}
