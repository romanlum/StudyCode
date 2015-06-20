package swe4.rmi.hello;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class HelloServiceImpl implements HelloService{

	private int counter = 0;
	
	private static int getPort(String hostPort) {
		int idx = hostPort.lastIndexOf(':');
		if(idx == -1) {
			return 1099;
		} else {
			return Integer.parseInt(hostPort.substring(idx+1));
		}
	}
	@Override
	public LocalDateTime getServerDate() throws RemoteException {
		return LocalDateTime.now();
	}

	@Override
	public synchronized int nextId() throws RemoteException {
		int value = counter;
		try {
			Thread.sleep(100);
		} catch(InterruptedException ex) {
			//nothing
		}
		counter = value+1;
		System.out.printf("nextId=%d, (thread: %d)%n", counter,Thread.currentThread().getId());
		return counter;
	}
	
	public static void main(String[] args) {
		//version 1: start rmi registry via commandline that these
		//files are in the classpath of rmiregistry process.
		
		//version2: 
		try
		{
			LocateRegistry.createRegistry(getPort(args[0]));
			HelloService service = new HelloServiceImpl();
			Remote helloServiceStub = UnicastRemoteObject.exportObject(service,0); //0 nimm registry port
			Naming.rebind("rmi://"+ args[0] + "/HelloService", helloServiceStub);

			System.out.println("Service available on port "+"rmi://"+ args[0] + "/HelloService");
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
