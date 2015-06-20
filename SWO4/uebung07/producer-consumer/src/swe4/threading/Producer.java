package swe4.threading;

import java.util.Random;
import swe4.util.Util;


public class Producer extends Thread {
  private Buffer b; // to store products to

  public Producer(String name, Buffer b) {
    this.b = b;
  } // Producer

  public void run() {
    Random randGen = new Random();
    for (int i = 0; i < 26; i++) {
      char newChar = (char) ('A' + (i % 26));
      System.out.printf("Producer(%.3f): ++> %c%n", Util.currTime(), 
                        newChar);
     // Util.sleep(randGen.nextInt(1000));
      System.out.printf("Producer(%.3f): <++ %c%n", Util.currTime(), 
                        newChar);
      b.put(newChar);
    }
  } // run
} // Producer
