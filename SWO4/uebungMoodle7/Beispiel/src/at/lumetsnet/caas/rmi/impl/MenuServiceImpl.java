package at.lumetsnet.caas.rmi.impl;

import java.util.Collection;

import at.lumetsnet.caas.dal.MenuCategoryDao;
import at.lumetsnet.caas.dal.MenuCategoryDaoJdbc;
import at.lumetsnet.caas.dal.MenuDao;
import at.lumetsnet.caas.dal.MenuDaoJdbc;
import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.rmi.interfaces.RemoteMenuService;

/***
 * MenuService impl using jdbc
 * @author romanlum
 *
 */
public class MenuServiceImpl extends ServiceImpl implements RemoteMenuService {
	
	private MenuDao menuDao;
	private MenuCategoryDao categoryDao;
	
	public MenuServiceImpl() {
		menuDao = new MenuDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		categoryDao = new MenuCategoryDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
	}
	
	/***
	 * Fetches all categories
	 * @return
	 */
	public Collection<MenuCategory> getAllCategories() {
		return categoryDao.getAll();
	}

	/***
	 * Deletes the category with the given id
	 * @param id
	 */
	public void deleteCategory(long id) {
		categoryDao.delete(id);
	}
	
	

	/***
	 * Deletes the menu with the given id
	 * @param id
	 */
	public void deleteMenu(long id) {
		menuDao.delete(id);
	}

	/***
	 * Saves or adds the category
	 * @param data
	 */
	public void saveOrUpdateCategory(MenuCategory data) {
		categoryDao.saveOrUpdate(data);
	}

	/***
	 * Saves or adds the menu
	 * @param data
	 */
	public void saveOrUpdateMenu(Menu data) {
		menuDao.saveOrUpdate(data);
	}

	/***
	 * Fetches all menus
	 * @return
	 */
	public Collection<Menu> getAllMenus() {
		Collection<Menu> data = menuDao.getAll();
		data.stream().forEach(x -> {
			x.setCategory(categoryDao.get(x.getCategoryId()));
		});
		return data;
		
	}
}
