package at.lumetsnet.swo.ue3;

import java.util.Arrays;
import java.util.Random;

public class SortTest {

	public static void main(String[] args) {
		sortTest(new HeapSort());
		sortTest(new QuickSort());
	}	
	
	private static void sortTest(Sorter sorter) {
		System.out.println("Testcase I: Empty array/null");
		int[] data = new int[0];
		sorter.sort(data);
		sorter.sort(null);
		System.out.println("No error.");
		System.out.println();
				
		System.out.println("Testcase II: Small data");
		data = new int[]{9,8,3,5,6,2,1,100};
		sorter.sort(data);
		sorter.sort(null);
		Arrays.stream(data).forEach((x)->System.out.print(x + ","));
		System.out.println();
		System.out.println();
		
		System.out.println("Testcase III: Same data test");
		data = new int[]{9,9,9,9,6,2,6,100,2,2};
		sorter.sort(data);
		sorter.sort(null);
		Arrays.stream(data).forEach((x)->System.out.print(x + ","));
		System.out.println();
		System.out.println();
		
		System.out.println("Testcase IV: Instrumentation data");
		data = new int[]{9,9,9,9,6,2,6,100,2,2};
		sorter.sort(data);
		System.out.println(sorter.getInstrumentationData());
		
		sortComparison();
		
	}
	
	private static void sortComparison() {
		System.out.println("Sort comparison");
		int currentPow = 1;
		int currentSize = 2;
		HeapSort heapSort = new HeapSort();
		QuickSort quickSort = new QuickSort();
		while(currentSize < 50000) {
			int[] data = getData(currentSize);
			heapSort.sort(data.clone());
			quickSort.sort(data);
			System.out.print("HeapSort: " + heapSort.getInstrumentationData().getSortTime() + " ns, "+ heapSort.getInstrumentationData().getComparisonCount() + ", " + heapSort.getInstrumentationData().getSwapCount());
			System.out.println();
			System.out.print("QuickSort: " + quickSort.getInstrumentationData().getSortTime() + " ns, "+ quickSort.getInstrumentationData().getComparisonCount() + ", " + quickSort.getInstrumentationData().getSwapCount());
			System.out.println();
			currentPow++;
			currentSize = (int) Math.pow(2, currentPow); 
		}
	}
	
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
