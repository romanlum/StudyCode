package at.lumetsnet.swe4.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TwoThreeFourTreeSet<T> extends AbstractSortedTreeSet<T> {
	
	private TTFNode<T> root;
	
	/***
	 * Constructor with natural sorting
	 */
	public TwoThreeFourTreeSet() {
		this(null);
	}
	
	/***
	 * Constructor with special sorting
	 * @param comparator
	 */
	public TwoThreeFourTreeSet(Comparator<T> comparator) {
		super(comparator);
		root = null;
	}
	
	/***
	 * Adds an element to the set
	 * 
	 * @param elem
	 * @return true if the item was added, otherwise false
	 */
	@Override
	public boolean add(T elem) {
		if(root == null) {
			root = new TTFNode<>(comparator, null,elem);
			size++;
			level = 0;
			return true;
		}
		
		int currentLevel = 0;
		TTFNode<T> tmp = root;
		while(tmp != null) {
			
			//split if full
			if(tmp.isFull()) {
				TTFNode<T> result = tmp.split();
				if(result.getParent() == null) { 
					root = result; //set new root
					currentLevel = 0;
				} else {
					currentLevel--;
				}
				tmp = result;
				//we start again one level above
				
			} else {
				//element already added
				if(tmp.contains(elem))
					return false;
				
				if(tmp.hasChildren()) {
					tmp = tmp.getChild(elem);
					currentLevel++;
				} else {
					tmp.addValue(elem);
					size++;
					if(currentLevel > level)
						level = currentLevel;
					return true;
				}
			}
		}
		return false;
	}
	
	/***
	 * Searches for an element in the set
	 * 
	 * @param elem
	 * @return found element or null if the element was not found
	 */
	@Override
	public T get(T elem) {
		TTFNode<T> tmp = root;
		while(tmp != null) {
			if(tmp.contains(elem)) {
				return tmp.get(elem);
			}
			tmp = tmp.getChild(elem);
		}
		return null;
	}
	
	/***
	 * Gets the smallest element of the set
	 * 
	 * @return
	 */
	@Override
	public T first() {
		if(root == null) {
			throw new NoSuchElementException("Set is empty");
		}
		TTFNode<T> tmp = root;
		while(tmp != null && tmp.getFirstChild() != null) {
			tmp = tmp.getFirstChild();
		}
		return tmp.getFirstValue();
	}
	
	/***
	 * Gets the biggest element of the set
	 * 
	 * @return
	 */
	@Override
	public T last() {
		if(root == null) {
			throw new NoSuchElementException("Set is empty");
		}
		TTFNode<T> tmp = root;
		while(tmp != null && tmp.getLastChild() != null) {
			tmp = tmp.getLastChild();
		}
		return tmp.getLastValue();
	}
	
	/***
	 * Gets the iterator
	 */
	@Override
	public Iterator<T> iterator() {
		List<T> iteratorList = new ArrayList<T>();
		if(root != null)
			root.getValues(iteratorList); 
		return iteratorList.iterator();
	}
}
