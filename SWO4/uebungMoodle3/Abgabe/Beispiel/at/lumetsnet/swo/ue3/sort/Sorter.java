package at.lumetsnet.swo.ue3.sort;

/***
 * Base class used for abstracting sorting and instrumentation
 * @author romanlum
 */
public abstract class Sorter {
  
  protected InstrumentationData instrumentationData;
  
  /***
   * Sorts the data
   * @param data
   */
  public void sort(int[] data) {
    instrumentationData = new InstrumentationData();
    long begin = System.nanoTime();
    doSort(data);
    long end = System.nanoTime();
    instrumentationData.setSortTime(end-begin);
  }
  
  /**
   * Sort method which needs to be implemented
   * @param data
   */
  protected abstract void doSort(int[] data);
  
  /***
   * Swaps the two field elements
   * refreshes instrumentation data
   * @param arr
   * @param x
   * @param y
   */
  protected void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
        instrumentationData.addSwap();
    }
  
  /***
   * Gets the instrumentation data for the current sort operation
   * @return
   */
  public InstrumentationData getInstrumentationData() {
    return instrumentationData;
  }
  
}
