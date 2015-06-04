package at.lumetsnet.caas.gui.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import at.lumetsnet.caas.business.MenuService;
import at.lumetsnet.caas.gui.Util;
import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.viewmodel.MenuCategoryViewModel;
import at.lumetsnet.caas.viewmodel.MenuViewModel;

public class ManageMenuDialog extends ManageEntityDialog<MenuViewModel> {

	public ManageMenuDialog(Window owner, Menu menu) {
		viewModel = new MenuViewModel(menu);
		createGui(owner, "Menü verwalten",
				"Bitte geben Sie die Daten des Menüs ein.");

		dialogStage
				.getScene()
				.getStylesheets()
				.add(getClass().getResource("../css/manage-menu-dialog.css")
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
				Util.getTextFieldForm("Beschreibung", viewModel
						.getDescriptionProperty()));
		box.getChildren().addAll(Util.getComboboxForm("Bereich", viewModel
						.getCategoryProperty(),
						getMenuCategories()));
		
		box.getChildren().addAll(Util.getTextFieldForm("Preis",viewModel.getPriceAsStringProperty()));
		DatePicker picker = new DatePicker();
		box.getChildren().add(Util.getFormLabel("Begin"));
		picker.valueProperty().bindBidirectional(viewModel.getBeginProperty());
		box.getChildren().add(picker);
		box.getChildren().add(Util.getFormLabel("End"));
		picker = new DatePicker();
		picker.valueProperty().bindBidirectional(viewModel.getEndProperty());
		box.getChildren().add(picker);
		return box;
	}

	
	private ObservableList<MenuCategoryViewModel> getMenuCategories() {
		ObservableList<MenuCategoryViewModel> data= FXCollections.observableArrayList();
		MenuService.getInstance().getAllCategories().forEach(x -> data.add(new MenuCategoryViewModel(x)));
		return data;
	}

	@Override
	protected void saveCommand() {
		MenuService.getInstance().saveOrUpdateMenu(viewModel.toMenuModel());
		dialogStage.close();
	}

	
	@Override
	protected  void cancelCommand() {
		canceled = true;
		dialogStage.close();
	}

	public static boolean show(Window owner, Menu model) {
		ManageMenuDialog dialog = new ManageMenuDialog(owner, model);
		return dialog.show();

	}
}
