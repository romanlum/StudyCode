package at.lumetsnet.caas.viewmodel;

import java.util.Collection;

import at.lumetsnet.caas.business.OrderService;
import at.lumetsnet.caas.business.UserService;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageUsersViewModel {
	
	private ObservableList<UserViewModel> users;
	
	public ManageUsersViewModel() {
		users = FXCollections.observableArrayList();
	}

	public void update() {
		Collection<User> data = UserService.getInstance().getAllUsers();
		users.clear();
		if(data != null) {
			data.forEach(x -> users.add(new UserViewModel(x)));
		}
	}
	
	public ObservableList<UserViewModel> getUserList() {
		return users;
	}

	public void deleteCommand(Number x) {
		UserService.getInstance().deleteUser((long) x);
		update();
		
	}
}
