package at.lumetsnet.swo.ue3;

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
    sortTime = 0;
  }
  
  public String toString() {
    StringBuilder builder=new StringBuilder();
    builder.append("Sort time: ");
    builder.append(sortTime);
    builder.append(" ns \n");
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
  
  public void setComparisonCount(int comparisonCount) {
    this.comparisonCount = comparisonCount;
  }
  
  public int getSwapCount() {
    return swapCount;
  }
  
  public void setSwapCount(int swapCount) {
    this.swapCount = swapCount;
  }

  public long getSortTime() {
    return sortTime;
  }

  public void setSortTime(long sortTime) {
    this.sortTime = sortTime;
  }
  
  
}
