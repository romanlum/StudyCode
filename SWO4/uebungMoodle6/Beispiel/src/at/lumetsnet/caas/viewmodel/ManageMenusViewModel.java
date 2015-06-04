package at.lumetsnet.caas.viewmodel;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.lumetsnet.caas.business.MenuService;
import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;

public class ManageMenusViewModel {

	private ObservableList<MenuCategoryViewModel> categories;
	private ObservableList<MenuViewModel> menus;

	public ManageMenusViewModel() {
		categories = FXCollections.observableArrayList();
		menus = FXCollections.observableArrayList();
	}

	public void updateCategories() {
		Collection<MenuCategory> data = MenuService.getInstance()
				.getAllCategories();
		categories.clear();
		if (data != null) {
			data.forEach(x -> categories.add(new MenuCategoryViewModel(x)));
		}
	}

	public void updateMenus() {
		Collection<Menu> data = MenuService.getInstance().getAllMenus();
		menus.clear();
		if (data != null) {
			data.forEach(x -> menus.add(new MenuViewModel(x)));
		}
	}

	public ObservableList<MenuCategoryViewModel> getCategoryList() {
		return categories;
	}

	public ObservableList<MenuViewModel> getMenuList() {
		return menus;
	}

	public void deleteCategoryCommand(Number userId) {
		MenuService.getInstance().deleteCategory((long) userId);
		updateCategories();

	}

	public void deleteMenuCommand(Number id) {
		MenuService.getInstance().deleteMenu((long) id);
		updateMenus();

	}

}
