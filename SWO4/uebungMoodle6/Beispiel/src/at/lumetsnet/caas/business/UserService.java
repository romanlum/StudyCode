package at.lumetsnet.caas.business;

public class UserService {
	
	public boolean login(String user, String pass) {
		if(user.equals("admin") && pass.equals("admin"))
			return true;
		
		return false;
	}
}
