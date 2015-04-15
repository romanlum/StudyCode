package at.lumetsnet.swo.ue3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/***
 * Hamming number generator class
 * @author romanlum
 *
 */
public class Hamming {
	
	/**
	 * Constant 2 in BigInteger representation
	 */
	private static final BigInteger TWO = BigInteger.valueOf(2);
	/**
	 * Constant 3 in BigInteger representation
	 */
	private static final BigInteger THREE = BigInteger.valueOf(3);
	/**
	 * Constant 5 in BigInteger representation
	 */
	private static final BigInteger FIVE = BigInteger.valueOf(5);
	
	/***
	 * Calculates the hamming numbers till the given upper barrier
	 * @param upper
	 * @return
	 */
	public static List<BigInteger> calculate(BigInteger upper) {
		List<BigInteger> result = new ArrayList<BigInteger>();
		//add first hamming number
		result.add(BigInteger.ONE);
		
		BigInteger value2 = TWO;
		BigInteger value3 = THREE;
		BigInteger value5 = FIVE;
		int index2 , index3, index5;
		index2 = index3 = index5 = 0;
		
		BigInteger currentMin;
		while(true) {
			//add the next hamming number to list
			currentMin = (value3.min(value5)).min(value2);
			
			//stop if we have reached out upper limit
			if(currentMin.compareTo(upper) == 1) {
				break;
			}
			
			result.add(currentMin);
			
			//check all values against the current min and increase the indexes if needed
			if(currentMin.compareTo(value2) == 0) {
				index2++;
				value2 = TWO.multiply(result.get(index2));
			} 
			if(currentMin.compareTo(value3) == 0) {
				index3++;
				value3 = THREE.multiply(result.get(index3));
			} 
			if(currentMin.compareTo(value5) == 0) {
				index5++;
				value5 = FIVE.multiply(result.get(index5));
			}
		}
		
		return result;		
	}
}
