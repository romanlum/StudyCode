package at.lumetsnet.caas.business;

import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.rmi.interfaces.RemoteMenuService;

/***
 * Mock menu business logic class Used as singleton, should be replaced with
 * real logic class
 * 
 * @author romanlum
 *
 */
public class MenuService extends RmiService<RemoteMenuService> {

	private static MenuService instance = null;

	private MenuService() {
		super("CaasMenuService");
	}

	/***
	 * Singleton instance class
	 * 
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
	 * 
	 * @return
	 */
	public Collection<MenuCategory> getAllCategories() {
		try {
			return service.getAllCategories();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Deletes the category with the given id
	 * 
	 * @param id
	 */
	public void deleteCategory(long id) {
		try {
			service.deleteCategory(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		;
	}

	/***
	 * Deletes the menu with the given id
	 * 
	 * @param id
	 */
	public void deleteMenu(long id) {
		try {
			service.deleteMenu(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Saves or adds the category
	 * 
	 * @param data
	 */
	public void saveOrUpdateCategory(MenuCategory data) {
		try {
			service.saveOrUpdateCategory(data);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Saves or adds the menu
	 * 
	 * @param data
	 */
	public void saveOrUpdateMenu(Menu data) {
		try {
			service.saveOrUpdateMenu(data);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Fetches all menus
	 * 
	 * @return
	 */
	public Collection<Menu> getAllMenus() {
		try {
			return service.getAllMenus();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
