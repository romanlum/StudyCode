package at.lumetsnet.swe4.collections;

import java.util.Comparator;

public class Util {
	/***
	 * compares the elements using the comparator if not null otherwise it is
	 * assumed that T implements comparable
	 * 
	 * @param left
	 * @param right
	 * @param comparator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> int compareElements(T left, T right,
			Comparator<T> comparator) {

		if (comparator != null) {
			return comparator.compare(left, right);
		}

		if (!(left instanceof Comparable<?>)) {
			throw new IllegalArgumentException("Elements are not comparable");
		}
		return ((Comparable<T>) left).compareTo(right);
	}
}
