package at.lumetsnet.swe4.collections;

import java.util.Comparator;

public class Util {
	@SuppressWarnings("unchecked")
	public static <T> int compareElements(T left, T right, Comparator<T> comparator) {
		if ((left == null) && (right == null)) {
			return 0;
		} else if (left == null) {
			return -1;
		} else if (right == null) {
			return 1;
		}
		if(comparator != null) {
			return comparator.compare(left, right); 
		}
		
		if(!(left instanceof Comparable<?>)) {
			throw new IllegalArgumentException("Elements are not comparable"); //TODO: check
		}
		return ((Comparable<T>)left).compareTo(right);
	}
}
