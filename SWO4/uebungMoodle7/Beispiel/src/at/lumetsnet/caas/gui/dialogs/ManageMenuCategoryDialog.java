package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import at.lumetsnet.caas.business.MenuService;
import at.lumetsnet.caas.gui.Util;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.viewmodel.MenuCategoryViewModel;

/***
 * Edit/Add dialog for MenuCategories
 * 
 * @author romanlum
 *
 */
public class ManageMenuCategoryDialog extends
		ManageEntityDialog<MenuCategoryViewModel> {

	public ManageMenuCategoryDialog(Window owner, MenuCategory data) {
		viewModel = new MenuCategoryViewModel(data);
		createGui(owner, "Bereich verwalten",
				"Bitte geben Sie die Daten des Bereichs ein.");

		dialogStage
				.getScene()
				.getStylesheets()
				.add(getClass().getResource(
						"../css/manage-menu-category-dialog.css")
						.toExternalForm());
	}

	@Override
	protected Node createContentPane() {

		VBox box = new VBox();
		box.getStyleClass().add("form-container");
		box.getChildren().addAll(
				Util.getTextFieldForm("Name", viewModel.getNameProperty()));

		return box;
	}

	@Override
	protected void saveCommand() {
		if (validate()) {
			// Saves the entity
			MenuService.getInstance().saveOrUpdateCategory(
					viewModel.toCategoryModel());
			dialogStage.close();
		}

	}

	/***
	 * Static method for showing the dialog
	 * 
	 * @param owner
	 * @param model
	 * @return
	 */
	public static boolean show(Window owner, MenuCategory model) {
		ManageMenuCategoryDialog dialog = new ManageMenuCategoryDialog(owner,
				model);
		return dialog.show();

	}
}
