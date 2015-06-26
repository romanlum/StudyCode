package at.lumetsnet.caas.dal;

import at.lumetsnet.caas.model.MenuCategory;

/***
 * MenuCategory dao jdbc impl
 * 
 * @author romanlum
 *
 */
public class MenuCategoryDaoJdbc extends GenericJdbcDao<MenuCategory> implements
		MenuCategoryDao {

	public MenuCategoryDaoJdbc(String conString, String userName,
			String password) {
		super(MenuCategory.class, "MenuCategory", conString, userName, password);
	}

}
