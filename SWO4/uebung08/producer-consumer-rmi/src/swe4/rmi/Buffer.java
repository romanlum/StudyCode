package swe4.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Buffer extends Remote {

	void put(char ch) throws RemoteException;
	char get() throws RemoteException;
}
