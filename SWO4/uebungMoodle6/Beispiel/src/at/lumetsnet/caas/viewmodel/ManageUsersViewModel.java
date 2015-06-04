package at.lumetsnet.caas.viewmodel;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import at.lumetsnet.caas.business.UserService;
import at.lumetsnet.caas.model.User;

public class ManageUsersViewModel {

	private ObservableList<UserViewModel> users;

	public ManageUsersViewModel() {
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

	public void toggleLockState(Number userId) {
		UserService.getInstance().toggleLockState((long) userId);
		update();
	}
}
