package at.lumetsnet.swo.ue3;

public class HeapSort extends Sorter {
	
	private int heapSize;
	
	protected void doSort(int[] arr)
    {
        buildHeap(arr);
        for (int i = arr.length-1; i >= 0; i--)
        {
            Swap(arr, 0, i);
            heapSize--;
            heapify(arr, 0);
        }
    }
	
	private void buildHeap(int[] arr)
	{
	    heapSize = arr.length-1;
	    for (int i = heapSize/2; i >= 0; i--)
	    {
	        heapify(arr, i);
	    }
	}
	
	private void heapify(int[] arr, int index)
	{
	    int left = 2 * index + 1;
	    int right = 2 * index + 2;
	    int largest = index;
	    if (left <= heapSize && arr[left] > arr[index])
	    {
	        largest = left;
	        instrumentationData.addComparison();
	    }
	    if (right <= heapSize && arr[right] > arr[largest])
	    {
	        largest = right;
	        instrumentationData.addComparison();
	    }
	    
	    if (largest != index)
	    {
	       Swap(arr, index, largest);
	       heapify(arr, largest);
	    }
	}
	
	private void Swap(int[] arr, int x, int y)
    {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
        instrumentationData.addSwap();
    }
	
    
}
