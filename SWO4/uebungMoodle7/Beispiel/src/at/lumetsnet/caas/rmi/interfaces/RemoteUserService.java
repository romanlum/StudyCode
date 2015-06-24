package at.lumetsnet.caas.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.lumetsnet.caas.model.User;

public interface RemoteUserService extends Remote {

	/***
	 * Fetches all users
	 * @return
	 */
	public Collection<User> getAllUsers() throws RemoteException;

	/***
	 * delete the user
	 * @param id
	 */
	public void deleteUser(long id) throws RemoteException;

	/***
	 * Toggles the locked state of a user
	 * @param id
	 */
	public void toggleLockState(long id) throws RemoteException;

	/***
	 * Saves or adds the user
	 * @param userModel
	 */
	public void saveOrUpdate(User userModel) throws RemoteException;
}
