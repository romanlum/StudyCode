package at.lumetsnet.caas.gui.pages;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import at.lumetsnet.caas.viewmodel.OrderViewModel;
import at.lumetsnet.caas.viewmodel.OrdersPageViewModel;

/***
 * View page used for showing the orders
 * Uses a OrdersPageViewModel for business logic operations
 * @author romanlum
 *
 */
public class OrdersPage extends VBox implements Showable {

	TableView<OrderViewModel> table;
	OrdersPageViewModel viewModel;

	public OrdersPage() {

		viewModel = new OrdersPageViewModel();
		getStyleClass().add("page-content-container");

		Label title = createTitle("Heutige Bestellungen");
		getChildren().add(title);

		table = createTableView();
		table.setItems(viewModel.getOrders());
		getChildren().add(table);

	}

	private Label createTitle(String title) {
		Label titleLabel = new Label(title);
		titleLabel.getStyleClass().add("page-title");
		return titleLabel;
	}

	private TableView<OrderViewModel> createTableView() {
		TableView<OrderViewModel> table = new TableView<>();
		table.getStyleClass().add("table");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<OrderViewModel, String> column = new TableColumn<>(
				"Benutzer");
		column.setCellValueFactory(x -> x.getValue().getUserNameProperty());
		table.getColumns().add(column);

		column = new TableColumn<>("Gericht");
		column.setCellValueFactory(x -> x.getValue().getMenuProperty());
		table.getColumns().add(column);

		column = new TableColumn<>("Zeit");
		column.setCellValueFactory(x -> x.getValue().getTimeProperty());
		column.setSortType(SortType.ASCENDING);
		table.getColumns().add(column);
		table.getSortOrder().clear();
		table.getSortOrder().add(column);

		column = new TableColumn<>("Kommentar");
		column.setCellValueFactory(x -> x.getValue().getCommentProperty());

		table.getColumns().add(column);
		
		return table;
	}

	public void show() {
		viewModel.update();
		table.sort();
	}
}
