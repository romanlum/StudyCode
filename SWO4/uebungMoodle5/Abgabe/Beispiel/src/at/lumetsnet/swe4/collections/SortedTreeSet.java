package at.lumetsnet.swe4.collections;

public interface SortedTreeSet<T> extends SortedSet<T> {
	/***
	 * Gets the height of the tree 
	 * height starts with 0, this means that a tree
	 * with only one item has height 0
	 * 
	 * @return
	 */
	int height();

}
