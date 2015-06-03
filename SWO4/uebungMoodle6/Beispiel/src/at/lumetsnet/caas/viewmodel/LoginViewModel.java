package at.lumetsnet.caas.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {

	private StringProperty userName;
	private StringProperty password;
	private boolean loginResult;
	
	public LoginViewModel() {
		this.userName = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}

	public StringProperty userNameProperty() {
		return userName;
	}
	
	public StringProperty passwordProperty() {
		return password;
	}
	
	public boolean getLoginResult() {
		return loginResult;
	}
	
	public void login() {
		System.out.println("user: "+userName.get() + " pass: "+password.get());
		loginResult = true;
	}
	
	
	

	
	
	
	
}
