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
import at.lumetsnet.caas.gui.ActionTableCell;
import at.lumetsnet.caas.gui.AmountTableCell;
import at.lumetsnet.caas.gui.dialogs.ManageMenuCategoryDialog;
import at.lumetsnet.caas.gui.dialogs.ManageMenuDialog;
import at.lumetsnet.caas.viewmodel.ManageMenusViewModel;
import at.lumetsnet.caas.viewmodel.MenuCategoryViewModel;
import at.lumetsnet.caas.viewmodel.MenuViewModel;

public class ManageMenusPage extends VBox implements Showable {

	TableView<MenuCategoryViewModel> categoryTable;
	TableView<MenuViewModel> menuTable;
	ManageMenusViewModel viewModel;

	public ManageMenusPage() {

		viewModel = new ManageMenusViewModel();
		getStyleClass().add("page-content-container");

		HBox topPane = new HBox();
		topPane.getChildren().add(createTitle("Bereiche"));
		HBox commandContainer = new HBox();
		commandContainer.getStyleClass().add("page-command-container");
		commandContainer.getChildren().add(createAddCategoryButton());
		HBox.setHgrow(commandContainer, Priority.ALWAYS);

		topPane.getChildren().add(commandContainer);
		categoryTable = createCategoryTableView();
		categoryTable.setItems(viewModel.getCategoryList());
		
		HBox menuTopPane = new HBox();
		menuTopPane.getChildren().add(createTitle("Menüs"));
		HBox menuCommandContainer = new HBox();
		menuCommandContainer.getStyleClass().add("page-command-container");
		menuCommandContainer.getChildren().add(createAddMenuButton());
		HBox.setHgrow(menuCommandContainer, Priority.ALWAYS);

		menuTopPane.getChildren().add(menuCommandContainer);
		menuTable = createMenuTableView();
		menuTable.setItems(viewModel.getMenuList());
		
		
		getChildren().addAll(topPane, categoryTable,menuTopPane, menuTable);

	}

	private Node createAddCategoryButton() {
		Button button = new Button("Bereich anlegen");
		button.getStyleClass().add("page-command");
		button.setOnAction(x -> addCategoryCommand());
		return button;
	}
	
	private Node createAddMenuButton() {
		Button button = new Button("Menü anlegen");
		button.getStyleClass().add("page-command");
		button.setOnAction(x -> addMenuCommand());
		return button;
	}

	private Label createTitle(String title) {
		Label titleLabel = new Label(title);
		titleLabel.getStyleClass().add("page-title");
		return titleLabel;
	}

	private TableView<MenuCategoryViewModel> createCategoryTableView() {
		TableView<MenuCategoryViewModel> table = new TableView<>();
		table.getStyleClass().add("table");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<MenuCategoryViewModel, String> column = new TableColumn<>(
				"Name");
		column.setCellValueFactory(x -> x.getValue().getNameProperty());
		table.getColumns().add(column);
		

		TableColumn<MenuCategoryViewModel, Number> actionColumn = new TableColumn<>(
				"Aktion");
		actionColumn.setCellValueFactory(x -> x.getValue().getIdProperty());
		actionColumn.setCellFactory(p -> new ActionTableCell<>(
				x -> editCategoryCommand(x), x -> deleteCategoryCommand(x)));

		table.getColumns().add(actionColumn);
		return table;
	}

	private TableView<MenuViewModel> createMenuTableView() {
		TableView<MenuViewModel> table = new TableView<>();
		table.getStyleClass().add("table");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<MenuViewModel, String> column = new TableColumn<>("Name");
		column.setCellValueFactory(x -> x.getValue().getDescriptionProperty());
		table.getColumns().add(column);
		
		TableColumn<MenuViewModel, Number> priceColumn = new TableColumn<>("Preis");
		priceColumn.setCellValueFactory(x -> x.getValue().getPriceProperty());
		priceColumn.setCellFactory(x -> new AmountTableCell<MenuViewModel>());
		table.getColumns().add(priceColumn);
		
		column = new TableColumn<>("Zeitraum");
		column.setCellValueFactory(x -> x.getValue().getUsageRangeProperty());
		table.getColumns().add(column);
		

		TableColumn<MenuViewModel, Number> actionColumn = new TableColumn<>(
				"Aktion");
		actionColumn.setCellValueFactory(x -> x.getValue().getIdProperty());
		actionColumn.setCellFactory(p -> new ActionTableCell<>(
				x -> editMenuCommand(x), x -> deleteMenuCommand(x)));

		table.getColumns().add(actionColumn);
		return table;
	}
	
	private void deleteCategoryCommand(Number x) {
		viewModel.deleteCategoryCommand(x);

	}

	private void editCategoryCommand(Number id) {
		Optional<MenuCategoryViewModel> model = categoryTable.getItems().stream()
				.filter(x -> x.getIdProperty().get() == (long) id).findFirst();

		if (model.isPresent()) {
			boolean result = ManageMenuCategoryDialog.show(getScene().getWindow(),
					model.get().toCategoryModel());
			if (!result) {
				viewModel.updateCategories();
			}
		}
	}
	
	private void deleteMenuCommand(Number x) {
		viewModel.deleteMenuCommand(x);

	}

	private void editMenuCommand(Number id) {
		Optional<MenuViewModel> model = menuTable.getItems().stream()
				.filter(x -> x.getIdProperty().get() == (long) id).findFirst();

		if (model.isPresent()) {
			boolean result = ManageMenuDialog.show(getScene().getWindow(),
					model.get().toMenuModel());
			if (!result) {
				viewModel.updateMenus();
			}
		}
	}

	private void addCategoryCommand() {
		boolean result = ManageMenuCategoryDialog.show(getScene().getWindow(), null);
		if (!result) {
			viewModel.updateCategories();
		}
	}
	
	private void addMenuCommand() {
		boolean result = ManageMenuDialog.show(getScene().getWindow(), null);
		if (!result) {
			viewModel.updateMenus();
		}
	}

	public void show() {
		viewModel.updateCategories();
		viewModel.updateMenus();
	}
}
