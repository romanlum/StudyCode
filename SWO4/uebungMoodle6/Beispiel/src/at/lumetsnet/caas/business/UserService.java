package at.lumetsnet.caas.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import at.lumetsnet.caas.model.User;

public class UserService {

	ArrayList<User> users;

	private static UserService instance = null;

	private UserService() {
		users = new ArrayList<>();
		users.add(new User(0, "admin", "admin", "Roman", "Lumetsberger", false,
				true));
		users.add(new User(1, "romanlum", "password", "Roman", "Lumetsberger",
				false, true));
		users.add(new User(2, "christophlum", "password", "Christoph",
				"Lumetsberger", true, false));
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

	public Collection<User> getAllUsers() {
		return users;
	}

	public void deleteUser(long id) {
		users.removeIf(x -> x.getId() == id);
	}

	public void toggleLockState(long id) {
		Optional<User> result = users.stream().filter(x -> x.getId() == id)
				.findFirst();
		if (result.isPresent()) {
			result.get().setLocked(!result.get().isLocked());
		}
	}

	public void saveOrUpdate(User userModel) {
		Util.saveOrUpdate(userModel, users);
	}
}
