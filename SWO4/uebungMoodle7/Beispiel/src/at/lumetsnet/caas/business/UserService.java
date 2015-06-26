package at.lumetsnet.caas.business;

import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.User;
import at.lumetsnet.caas.rmi.interfaces.RemoteUserService;

/***
 * Mock user business logic class Used as singleton, should be replaced with
 * real logic class
 * 
 * @author romanlum
 *
 */
public class UserService extends RmiService<RemoteUserService> {

	private static UserService instance = null;

	private UserService() {
		super("CaasUserService");
	}

	/**
	 * Singleton instance class
	 */
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	/***
	 * Fetches all users
	 * 
	 * @return
	 */
	public Collection<User> getAllUsers() {
		try {
			return service.getAllUsers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * delete the user
	 * 
	 * @param id
	 */
	public void deleteUser(long id) {
		try {
			service.deleteUser(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Toggles the locked state of a user
	 * 
	 * @param id
	 */
	public void toggleLockState(long id) {
		try {
			service.toggleLockState(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Saves or adds the user
	 * 
	 * @param userModel
	 */
	public void saveOrUpdate(User userModel) {
		try {
			service.saveOrUpdate(userModel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
