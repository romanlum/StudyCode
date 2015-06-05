package at.lumetsnet.caas.viewmodel;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.lumetsnet.caas.business.UserService;
import at.lumetsnet.caas.model.User;

/***
 * ViewModel (logic) class for ManageUsersPage
 * @author romanlum
 *
 */

public class ManageUsersPageViewModel {

	private ObservableList<UserViewModel> users;

	public ManageUsersPageViewModel() {
		users = FXCollections.observableArrayList();
	}

	public void update() {
		Collection<User> data = UserService.getInstance().getAllUsers();
		users.clear();
		if (data != null) {
			data.forEach(x -> users.add(new UserViewModel(x)));
		}
	}

	public ObservableList<UserViewModel> getUserList() {
		return users;
	}

	public void deleteCommand(Number userId) {
		UserService.getInstance().deleteUser((long) userId);
		update();

	}

	public void toggleLockStateCommand(Number userId) {
		UserService.getInstance().toggleLockState((long) userId);
		update();
	}
}
