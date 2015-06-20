package swe4.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

import swe4.util.Util;


public class Producer {
  private Buffer b; // to store products to

  public Producer(String host_port) {
	  try {
			b = (Buffer) Naming.lookup("rmi://" + host_port + "/Buffer");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
  } // Producer

  public void produce() throws RemoteException {
    Random randGen = new Random();
    for (int i = 0; i < 26; i++) {
      char newChar = (char) ('A' + (i % 26));
      System.out.printf("Producer(%.3f): ++> %c%n", Util.currTime(), 
                        newChar);
      Util.sleep(randGen.nextInt(1000));
      System.out.printf("Producer(%.3f): <++ %c%n", Util.currTime(), 
                        newChar);
      b.put(newChar);
    }
  } // run
  
  public static void main(String[] args) {
		Producer c = new Producer(args[0]);
		System.out.println("Consuming: ");
		try {
			c.produce();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
} // Producer
