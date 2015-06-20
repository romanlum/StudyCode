package swe4.rmi.hello;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

public class HelloClient {
	private static final int NO_ITERATIONS = 1000;
	
	private static void doTimings(HelloService service) throws RemoteException {
		long nanoStart = System.nanoTime();
		
		for(int i = 0;i < NO_ITERATIONS; i++) {
			service.getServerDate();
		}
		long nanoEnd = System.nanoTime();
		System.out.printf("Time/Call: %.6f\n" ,(double) (nanoEnd - nanoStart)/NO_ITERATIONS /1e9 );
	}
	
	public static void main(String[] args) {
		String host_port = args[0];
		try{
			System.out.println("Doing lookup for helloService");
			HelloService proxy = (HelloService) Naming.lookup("rmi://"+host_port+"/HelloService");
			System.out.println("typeof(proxy): "+proxy.getClass().getName());
			System.out.println("Client-Date: "+LocalDateTime.now());
			System.out.println("Server-Date: "+proxy.getServerDate());
			
			for(int i = 0; i< NO_ITERATIONS;i++) {
				System.out.println("NextId="+proxy.nextId());
			}
			
			
			System.out.println("--------------Timings--------------------");
			doTimings(proxy);
			System.out.println("--------------Timings End----------------");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
