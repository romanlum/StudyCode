package at.lumetsnet.swo.ue3;

import java.util.Random;

public class SortTest {

	public static void main(String[] args) {
		Sorter sort = new HeapSort();
		int[] data=new int[50000];
		Random rnd = new Random();
		rnd.setSeed(System.nanoTime());
		for(int i = 0; i< 50000;i++)  {
			data[i] = rnd.nextInt();
		}
		
		sort.sort(data);
		System.out.println("Performance data:");
		System.out.println(sort.getInstrumentationData());
		
		
		
		
	}	
}
