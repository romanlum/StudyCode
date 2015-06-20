package at.lumetsnet.swe4.collections;

import java.util.Comparator;
import java.util.Iterator;

public interface SortedSet<T> extends Iterable<T> {

	/***
	 * Adds an element to the set
	 * 
	 * @param elem
	 * @return true if the item was added, otherwise false
	 */
	boolean add(T elem);

	/***
	 * Searches for an element in the set
	 * 
	 * @param elem
	 * @return found element or null if the element was not found
	 */
	T get(T elem);

	/***
	 * checks if the set contains the element
	 * 
	 * @param elem
	 * @return true if element found, false if not
	 */
	boolean contains(T elem);

	/***
	 * Gets the size of the set
	 * 
	 * @return
	 */
	int size();

	/***
	 * Gets the smallest element of the set
	 * 
	 * @return
	 */
	T first();

	/***
	 * Gets the biggest element of the set
	 * 
	 * @return
	 */
	T last();

	/***
	 * Gets the comparator
	 * 
	 * @return comparator or null
	 */
	Comparator<T> comparator();

	/***
	 * Gets the iterator
	 */
	Iterator<T> iterator();
}
