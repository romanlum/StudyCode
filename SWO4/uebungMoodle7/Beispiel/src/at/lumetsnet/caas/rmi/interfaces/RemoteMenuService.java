package at.lumetsnet.caas.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;

public interface RemoteMenuService extends Remote {

	/***
	 * Fetches all categories
	 * 
	 * @return
	 */
	public Collection<MenuCategory> getAllCategories() throws RemoteException;

	/***
	 * Deletes the category with the given id
	 * 
	 * @param id
	 */
	public void deleteCategory(long id) throws RemoteException;

	/***
	 * Deletes the menu with the given id
	 * 
	 * @param id
	 */
	public void deleteMenu(long id) throws RemoteException;

	/***
	 * Saves or adds the category
	 * 
	 * @param data
	 */
	public void saveOrUpdateCategory(MenuCategory data) throws RemoteException;

	/***
	 * Saves or adds the menu
	 * 
	 * @param data
	 */
	public void saveOrUpdateMenu(Menu data) throws RemoteException;

	/***
	 * Fetches all menus
	 * 
	 * @return
	 */
	public Collection<Menu> getAllMenus() throws RemoteException;
}
