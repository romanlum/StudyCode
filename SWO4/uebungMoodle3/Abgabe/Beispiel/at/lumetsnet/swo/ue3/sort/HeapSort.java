package at.lumetsnet.swo.ue3.sort;

/***
 * Heap sort implementation
 * 
 * @author romanlum
 *
 */
public class HeapSort extends Sorter {

  private int heapSize;

  protected void doSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      // nothing todo
      return;
    }
    buildHeap(arr);
    for (int i = arr.length - 1; i >= 0; i--) {
      swap(arr, 0, i);
      heapSize--;
      heapify(arr, 0);
    }
  }

  private void buildHeap(int[] arr) {
    heapSize = arr.length - 1;
    for (int i = heapSize / 2; i >= 0; i--) {
      heapify(arr, i);
    }
  }

  private void heapify(int[] arr, int index) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;
    int largest = index;
    if (left <= heapSize && arr[left] > arr[index]) {
      largest = left;
      instrumentationData.addComparison();
    }
    if (right <= heapSize && arr[right] > arr[largest]) {
      largest = right;
      instrumentationData.addComparison();
    }

    if (largest != index) {
      swap(arr, index, largest);
      heapify(arr, largest);
    }
  }
}
