package at.lumetsnet.swo.ue3;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HammingTest {

	public static void main(String[] args) {
	
		long time = System.nanoTime();
		List<BigInteger> result = Hamming.calculate(BigInteger.valueOf( 51931278044839L).multiply(BigInteger.valueOf(10).pow(70)));
		System.out.println("Time "+TimeUnit.MILLISECONDS.convert(System.nanoTime()-time,TimeUnit.NANOSECONDS));
		System.out.println(result.get(result.size()-1));
		System.out.println(result.size());
		
		
	}
}
