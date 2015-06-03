package at.lumetsnet.caas.business;

import java.util.ArrayList;
import java.util.Collection;

import at.lumetsnet.caas.model.User;

public class UserService {

	Collection<User> users;

	private static UserService instance = null;

	private UserService() {
		users = new ArrayList<>();
		users.add(new User(0, "admin", "admin", "Roman", "Lumetsberger", false,
				true));
		users.add(new User(1, "romanlum", "password", "Roman", "Lumetsberger",
				false, true));
		users.add(new User(2, "christophlum", "password", "Christoph",
				"Lumetsberger", false, false));
		users.add(new User(3, "moe", "password", "Moe", "Sislec", false, true));

	}

	/**
     * 
     */
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public boolean login(String user, String pass) {
		return users.stream().anyMatch(
				x -> x.getUserName().equals(user)
						&& x.getPassword().equals(pass));
	}

	public Collection<User> getAllUsers() {
		return users;
	}
	
	public void deleteUser(long id) {
		users.removeIf(x -> x.getId() == id);
	}
}
