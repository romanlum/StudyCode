package at.lumetsnet.swe4.collections;

import java.util.Comparator;

public class AbstractSortedTreeSet<T> {
	
	protected Comparator<T> comparator;
	
	public AbstractSortedTreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	protected int compareElements(T left, T right) {
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
