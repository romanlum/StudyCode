package at.lumetsnet.swo.ue3.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import at.lumetsnet.swo.ue3.sort.HeapSort;
import at.lumetsnet.swo.ue3.sort.InstrumentationData;
import at.lumetsnet.swo.ue3.sort.QuickSort;
import at.lumetsnet.swo.ue3.sort.Sorter;

/***
 * Testclass for Heapsort and Quicksort
 * @author romanlum
 *
 */
public class SortTest {

  public static void runTests() {
    sortTest(new HeapSort());
    sortTest(new QuickSort());
    sortComparison();
    
  }  
  
  private static void sortTest(Sorter sorter) {
    String testCaseSuffix = sorter.getClass().getSimpleName();
    System.out.println("Testcase "+testCaseSuffix +" I: Empty array/null");
    int[] data = new int[0];
    sorter.sort(data);
    sorter.sort(null);
    System.out.println("No error.");
    System.out.println();
        
    System.out.println("Testcase "+testCaseSuffix+" II: Small data");
    data = new int[]{9,8,3,5,6,2,1,100};
    sorter.sort(data);
    sorter.sort(null);
    Arrays.stream(data).forEach((x)->System.out.print(x + ","));
    System.out.println();
    System.out.println();
    
    System.out.println("Testcase "+testCaseSuffix+" III: Same data test");
    data = new int[]{9,9,9,9,6,2,6,100,2,2};
    sorter.sort(data);
    sorter.sort(null);
    Arrays.stream(data).forEach((x)->System.out.print(x + ","));
    System.out.println();
    System.out.println();
    
    System.out.println("Testcase "+testCaseSuffix+" IV: Instrumentation data");
    data = new int[]{9,9,9,9,6,2,6,100,2,2};
    sorter.sort(data);
    System.out.println(sorter.getInstrumentationData());
       
  }
  
  /***
   * Compares heapsort and quicksort by using array lengths
   * from 2 to 70000.
   * For every size the sort is done 100 times and the average
   * is used.
   */
  private static void sortComparison() {
    System.out.println("Testcase: Sort comparison");
    int currentPow = 1;
    int currentSize = 2;
    HeapSort heapSort = new HeapSort();
    QuickSort quickSort = new QuickSort();
    
    while(currentSize < 70000) {
      ArrayList<InstrumentationData> hData = new ArrayList<InstrumentationData>(100);
      ArrayList<InstrumentationData> qData = new ArrayList<InstrumentationData>(100);
      
      //do the sort 100 times
      for(int i = 0; i < 100; i++) {
        
        int[] data = getData(currentSize);
        //Copy the array to have equal test data for both sorts
        heapSort.sort(Arrays.copyOf(data,data.length));
        quickSort.sort(data);
        hData.add(heapSort.getInstrumentationData());
        qData.add(quickSort.getInstrumentationData());
      }
      
      
      System.out.printf("Size %1d\n", currentSize);
      System.out.printf("  HeapSort:\t%4d micro sec\t%5d comps\t%5d swaps\n",
          getTimeAverage(hData),
          getComparisonAverage(hData),
          getSwapAverage(hData));
      System.out.printf("  QuickSort:\t%4d micro sec\t%5d comps\t%5d swaps\n",
          getTimeAverage(qData),
          getComparisonAverage(qData),
          getSwapAverage(qData));
      currentPow++;
      currentSize = (int) Math.pow(2, currentPow); 
    }
  }
  
  /***
   * Gets the average comparison count of the given data
   * @param data
   * @return
   */
  private static int getComparisonAverage(List<InstrumentationData> data) {
    return (int)data.stream().mapToDouble(c ->c.getComparisonCount()).average().getAsDouble();
  }
  /***
   * Gets the average swap count of the given data
   * @param data
   * @return
   */
  private static int getSwapAverage(List<InstrumentationData> data) {
    return (int)data.stream().mapToDouble(c ->c.getSwapCount()).average().getAsDouble();
  }
  /***
   * Gets the average time of the given data
   * @param data
   * @return
   */
  private static long getTimeAverage(List<InstrumentationData> data) {
    long nsec = (long) data.stream().mapToDouble(c ->c.getSwapCount())
                                    .average().getAsDouble();
    return TimeUnit.MICROSECONDS.convert(nsec, TimeUnit.NANOSECONDS);
  }
  
  /***
   * Gets random test data
   * @param size
   * @return
   */
  private static int[] getData(int size) {
    Random rnd=new Random();
    rnd.setSeed(System.nanoTime());
    int[] data = new int[size];
    for(int i = 0; i< size; i++){
      data[i] = rnd.nextInt();
    }
    return data;
  }
}
