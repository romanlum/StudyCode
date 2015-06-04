package at.lumetsnet.caas.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;

public class MenuService {

	ArrayList<MenuCategory> categories;
	ArrayList<Menu> menus;

	private static MenuService instance = null;

	private MenuService() {
		categories = new ArrayList<>();
		categories.add(new MenuCategory(0, "Vegetarisch"));
		categories.add(new MenuCategory(1, "Fleisch und Fisch"));

		menus = new ArrayList<>();
		menus.add(new Menu(0, "Wienerschnitzel", 1000, LocalDate.now(),
				LocalDate.now() , categories.get(0)));
		menus.add(new Menu(1, "Gericht 2", 20000, LocalDate.now(),
				LocalDate.now(), categories.get(1)));
	}

	public static MenuService getInstance() {
		if (instance == null) {
			instance = new MenuService();
		}
		return instance;
	}

	public Collection<MenuCategory> getAllCategories() {
		return categories;
	}

	public void deleteCategory(long id) {
		categories.removeIf(x -> x.getId() == id);
	}
	
	public void deleteMenu(long id) {
		menus.removeIf(x -> x.getId() == id);
	}

	public void saveOrUpdateCategory(MenuCategory data) {
		Util.saveOrUpdate(data, categories);
	}
	
	public void saveOrUpdateMenu(Menu data) {
		Util.saveOrUpdate(data, menus);
	}

	public Collection<Menu> getAllMenus() {
		return menus;
	}

}
