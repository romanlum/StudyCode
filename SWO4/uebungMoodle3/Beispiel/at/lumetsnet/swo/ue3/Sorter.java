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
	
	protected abstract void doSort(int[] data);
	
	/***
	 * Gets the instrumentation data for the current sort operation
	 * @return
	 */
	InstrumentationData getInstrumentationData() {
		return instrumentationData;
	}
	
}
