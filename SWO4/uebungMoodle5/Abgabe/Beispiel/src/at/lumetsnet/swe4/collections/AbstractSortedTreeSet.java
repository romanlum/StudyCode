package at.lumetsnet.swe4.collections;

import java.util.Comparator;

/***
 * Class used as base class for binary-search-tree and thwo-three-four tree
 * 
 * @author romanlum
 *
 * @param <T>
 */
public abstract class AbstractSortedTreeSet<T> implements SortedTreeSet<T> {

	protected Comparator<T> comparator;
	protected int size;
	protected int level;

	public AbstractSortedTreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
		this.size = 0;
		this.level = -1;
	}

	/***
	 * Compares two elements using either the comparator or comparable
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	protected int compareElements(T left, T right) {
		return Util.compareElements(left, right, comparator);
	}

	/***
	 * Gets the comparator
	 * 
	 * @return comparator or null
	 */
	@Override
	public Comparator<T> comparator() {
		return comparator;
	}

	/***
	 * Gets the size of the set
	 * 
	 * @return
	 */
	@Override
	public int size() {
		return size;
	}

	/***
	 * checks if the set contains the element
	 * 
	 * @param elem
	 * @return true if element found, false if not
	 */
	@Override
	public boolean contains(T elem) {
		return get(elem) != null;
	}

	/***
	 * Gets the height of the tree height starts with 0, this means that a tree
	 * with only one item has height 0
	 * 
	 * @return
	 */
	@Override
	public int height() {
		return level;
	}
}
