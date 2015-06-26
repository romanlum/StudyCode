package at.lumetsnet.caas.rmi.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.dal.UserDao;
import at.lumetsnet.caas.dal.UserDaoJdbc;
import at.lumetsnet.caas.model.User;
import at.lumetsnet.caas.rmi.interfaces.RemoteUserService;

/***
 * User service impl using jdbc
 * @author romanlum
 *
 */
public class UserServiceImpl extends ServiceImpl implements RemoteUserService {
	
	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDaoJdbc(CONNECTION_STRING,USER_NAME,PASSWORD);
	}

	/***
	 * Fetches all users
	 * @return
	 */
	public Collection<User> getAllUsers() throws RemoteException {
		return dao.getAll();
	}

	/***
	 * delete the user
	 * @param id
	 */
	public void deleteUser(long id) throws RemoteException{
		dao.delete(id);
	}

	/***
	 * Toggles the locked state of a user
	 * @param id
	 */
	public void toggleLockState(long id) throws RemoteException {
		User user = dao.get(id);
		if(user != null) {
			user.setLocked(!user.isLocked());
		}
		saveOrUpdate(user);
	}

	/***
	 * Saves or adds the user
	 * @param userModel
	 */
	public void saveOrUpdate(User userModel)throws RemoteException {
		dao.saveOrUpdate(userModel);
	}

}
