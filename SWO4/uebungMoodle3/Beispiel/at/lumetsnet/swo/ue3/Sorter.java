package at.lumetsnet.swo.ue3;

/***
 * Interface used for abstracting sorting and instrumentation
 * @author romanlum
 *
 * @param <T>
 */
public abstract class Sorter {
	
	protected InstrumentationData instrumentationData;
	
	public Sorter() {
		instrumentationData = new InstrumentationData();
	}
	
	/***
	 * Sorts the data
	 * @param data
	 */
	public void sort(int[] data) {
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
