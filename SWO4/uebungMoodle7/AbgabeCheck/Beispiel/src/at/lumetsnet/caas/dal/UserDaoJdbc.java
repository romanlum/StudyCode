package at.lumetsnet.caas.dal;

import at.lumetsnet.caas.model.User;

/***
 * User dao jdbc impl
 * 
 * @author romanlum
 *
 */
public class UserDaoJdbc extends GenericJdbcDao<User> implements UserDao {

	public UserDaoJdbc(String conString, String userName, String password) {
		super(User.class, "User", conString, userName, password);
	}

}
