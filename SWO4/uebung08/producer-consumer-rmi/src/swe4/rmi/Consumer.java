package swe4.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

import swe4.util.Util;

public class Consumer {
	private Buffer b; // get get products from

	public Consumer(String host_port) {
		try {
			b = (Buffer) Naming.lookup("rmi://" + host_port + "/Buffer");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	} // Consumer

	public void consume() throws RemoteException {
		Random randGen = new Random();
		for (int i = 0; i < 26; i++) {
			char ch = b.get();
			System.out.printf("Consumer(%.3f): --> %c%n", Util.currTime(), ch);
			Util.sleep(randGen.nextInt(1500));
			System.out.printf("Consumer(%.3f): <-- %c%n", Util.currTime(), ch);
		}
	} // run

	public static void main(String[] args) {
		Consumer c = new Consumer(args[0]);
		System.out.println("Consuming: ");
		try {
			c.consume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} // Consumer

