package at.lumetsnet.caas.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;

/***
 * Mock menu business logic class
 * Used as singleton, should be replaced with 
 * real logic class
 * @author romanlum
 *
 */
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
				LocalDate.now(), categories.get(0)));
		menus.add(new Menu(1, "Gericht 2", 20000, LocalDate.now(), LocalDate
				.now(), categories.get(1)));
	}

	/***
	 * Singleton instance class
	 * @return
	 */
	public static MenuService getInstance() {
		if (instance == null) {
			instance = new MenuService();
		}
		return instance;
	}

	/***
	 * Fetches all categories
	 * @return
	 */
	public Collection<MenuCategory> getAllCategories() {
		return categories;
	}

	/***
	 * Deletes the category with the given id
	 * @param id
	 */
	public void deleteCategory(long id) {
		categories.removeIf(x -> x.getId() == id);
	}

	/***
	 * Deletes the menu with the given id
	 * @param id
	 */
	public void deleteMenu(long id) {
		menus.removeIf(x -> x.getId() == id);
	}

	/***
	 * Saves or adds the category
	 * @param data
	 */
	public void saveOrUpdateCategory(MenuCategory data) {
		Util.saveOrUpdate(data, categories);
	}

	/***
	 * Saves or adds teh menu
	 * @param data
	 */
	public void saveOrUpdateMenu(Menu data) {
		Util.saveOrUpdate(data, menus);
	}

	/***
	 * Fetches all menus
	 * @return
	 */
	public Collection<Menu> getAllMenus() {
		return menus;
	}

}
