package at.lumetsnet.swo.ue3.test;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

import at.lumetsnet.swo.ue3.hamming.Hamming;

/**
 * Testclass for Hamming class
 * @author romanlum
 *
 */
public class HammingTest {

  public static void runTests() {
    
    System.out.println("Testcase I: Hamming number 1 - 10");
    long time = System.nanoTime();
    List<BigInteger> result = Hamming.calculate(BigInteger.valueOf(10));
    System.out.println("Time "+TimeUnit.MILLISECONDS.convert(System.nanoTime()
                               -time,TimeUnit.NANOSECONDS) + "ms");
    
    result.forEach((x) -> System.out.print(x + ","));
    System.out.println();
    System.out.println();
    
    System.out.println("Testcae III: 10 000 Hamming number");
    calculateAndPrintHamming(BigInteger.valueOf(288325195312500001L));
    
    System.out.println("Testcae III: 1 000 000 Hamming number");
    calculateAndPrintHamming(BigInteger.valueOf( 51931278044839L)
                        .multiply(BigInteger.valueOf(10).pow(70)));
    
  }
  
  private static void calculateAndPrintHamming(BigInteger upperBoundary) {
    long time = System.nanoTime();
    List<BigInteger> result = Hamming.calculate(upperBoundary);
    System.out.println("Time "+TimeUnit.MILLISECONDS.convert(System.nanoTime()
                                -time,TimeUnit.NANOSECONDS) + "ms");
    System.out.println("Count: "+ result.size());
    System.out.println("Last entry: " + result.get(result.size()-1));
    System.out.println();
      
  }
}
