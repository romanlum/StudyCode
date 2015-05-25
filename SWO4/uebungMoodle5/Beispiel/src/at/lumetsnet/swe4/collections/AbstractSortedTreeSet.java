package at.lumetsnet.swe4.collections;

import java.util.Comparator;

public abstract class AbstractSortedTreeSet<T> implements SortedTreeSet<T> {
	
	protected Comparator<T> comparator;
	protected int size;
	protected int level;
	
	public AbstractSortedTreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
		this.size = 0;
		this.level = -1;
	}

	protected int compareElements(T left, T right) {
		return Util.compareElements(left, right, comparator);
	}
	
	@Override
	public Comparator<T> comparator() {
		return comparator;
	}

	@Override
	public int height() {
		return level;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean contains(T elem) {
		return get(elem) != null;
	}
	
	
}
