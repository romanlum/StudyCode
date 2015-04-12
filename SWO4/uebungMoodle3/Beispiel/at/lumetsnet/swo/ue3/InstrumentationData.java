package at.lumetsnet.swo.ue3;

import java.util.concurrent.TimeUnit;

/***
 * Encapsulates the istrumentation data for sorting
 * @author romanlum
 *
 */
public class InstrumentationData {
	private int comparisonCount;
	private int swapCount;
	private long sortTime;
		
	public void addSwap() {
		swapCount++;
	}
	
	public void addComparison() {
		comparisonCount++;
	}
	
	
	public void clear() {
		comparisonCount = 0;
		swapCount = 0;
	}
	
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("Sort time: ");
		builder.append(TimeUnit.MILLISECONDS.convert(sortTime, TimeUnit.NANOSECONDS));
		builder.append("ms \n");
		builder.append("Comparison count: ");
		builder.append(comparisonCount);
		builder.append("\n");
		builder.append("Swap count: ");
		builder.append(swapCount);
		builder.append("\n");
		
		return builder.toString();
	}
	
	public int getComparisonCount() {
		return comparisonCount;
	}
	
	public int getSwapCount() {
		return swapCount;
	}

	public long getSortTime() {
		return sortTime;
	}

	public void setSortTime(long sortTime) {
		this.sortTime = sortTime;
	}
	
	
}
