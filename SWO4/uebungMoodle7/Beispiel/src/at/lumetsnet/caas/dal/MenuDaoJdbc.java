package at.lumetsnet.caas.dal;

import java.util.Collection;

import at.lumetsnet.caas.model.Menu;

/***
 * Menu dao jdbc impl 
 * @author romanlum
 *
 */

public class MenuDaoJdbc extends GenericJdbcDao<Menu> implements MenuDao {

	public MenuDaoJdbc(String conString, String userName, String password) {
		super(Menu.class, "Menu", conString, userName, password);
	}

	@Override
	public Collection<String> getPropertyFilter() {
		Collection<String> result = super.getPropertyFilter();
		result.add("category");
		return result;
	}

}
