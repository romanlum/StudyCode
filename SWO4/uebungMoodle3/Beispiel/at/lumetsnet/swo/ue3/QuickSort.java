package at.lumetsnet.swo.ue3;

public class QuickSort extends Sorter{
	
	private int sortDataSize;  
	private int[] data;
	/***
	 * Sorts the data
	 * @param values
	 */
	  protected void doSort(int[] values) {
		instrumentationData.clear();
		
	    // check for empty or null array
	    if (values ==null || values.length==0){
	      return;
	    }
	    this.data = values;
	    sortDataSize = values.length;
	    quicksort(0, sortDataSize - 1);
	  }
	  
	  private void quicksort(int low, int high) {
	    int i = low, j = high;
	    
	    int pivot = data[low + (high-low)/2];

	    // Divide into two lists
	    while (i <= j) {
	   
	      while (data[i] < pivot) {
	        i++;
	        instrumentationData.addComparison();
	      }
	      
	      while (data[j] > pivot) {
	    	instrumentationData.addComparison();
	        j--;
	      }
	      
	      if (i <= j) {
	        exchange(i, j);
	        i++;
	        j--;
	      }
	    }
	    
	    if (low < j)
	      quicksort(low, j);
	    if (i < high)
	      quicksort(i, high);
	  }

	  private void exchange(int i, int j) {
	    int temp = data[i];
	    data[i] = data[j];
	    data[j] = temp;
	    instrumentationData.addSwap();
	    
	  }
	} 
