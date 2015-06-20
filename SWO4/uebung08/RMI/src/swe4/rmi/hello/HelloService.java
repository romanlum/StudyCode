package swe4.rmi.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface HelloService extends Remote {

	LocalDateTime getServerDate() throws RemoteException;
	int nextId() throws RemoteException;
}
