package at.lumetsnet.swe4.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class TTFNode<T> {
	private ArrayList<T> values;
	private ArrayList<TTFNode<T>> children;
	private Comparator<T> comparator;
	private TTFNode<T> parent;

	
	public TTFNode(Comparator<T> comparator, TTFNode<T> parent) {
		this.values = new ArrayList<>(3);
		this.children = new ArrayList<>(4);
		this.comparator = comparator;
		this.parent = parent;
	}

	public TTFNode(Comparator<T> comparator, TTFNode<T> parent, T value) {
		this(comparator, parent);
		this.values.add(0,value);
	}
	
	public TTFNode(Comparator<T> comparator, TTFNode<T> parent, T value, TTFNode<T> left,TTFNode<T> right) {
		this(comparator, parent);
		this.values.add(0,value);
		//update parent
		if(left != null) {
			left.parent = this;
			children.add(0, left);
		}
		//update parent
		if(right != null) {
			right.parent = this;
			children.add(1, right);
		}
	}

	/***
	 * Returns all the values used for the iterator
	 * @param iteratorList
	 */
	void getValues(List<T> iteratorList) {
		//Check if we have children
		if (children.size() != 0) {
			for (int i = 0; i < values.size(); i++) {
				//add child values
				children.get(i).getValues(iteratorList);
				//add node value
				iteratorList.add(values.get(i));
			}
			children.get(children.size() - 1).getValues(iteratorList);
		} else {
			iteratorList.addAll(values);
		}
	}

	/***
	 * Gets if the node is full (4-Node)
	 * @return
	 */
	public boolean isFull() {
		return values.size() == 3;
	}

	/**
	 * Gets if the node has children
	 * @return
	 */
	public boolean hasChildren() {
		return children.size() != 0;
	}
	
	
	/***
	 * Gets the child in which the element should be in
	 * @param elem
	 * @return
	 */
	public TTFNode<T> getChild(T elem) {
		if (children.size() == 0)
			return null;
		return (children.get(getChildIndex(elem)));
	}
	
	/***
	 * splits the node
	 * @return
	 */
	public TTFNode<T> split() {
		TTFNode<T> tmpParent = parent;
		if (tmpParent == null) {
			//create new parent (used for splitting root)
			tmpParent = new TTFNode<T>(comparator,null);
		}
		//add the value to the node
		tmpParent.addValue(values.get(1));
		
		TTFNode<T> left = new TTFNode<T>(comparator, tmpParent, values.get(0),
				getChildByPosition(0),
				getChildByPosition(1));
		
		TTFNode<T> right = new TTFNode<T>(comparator, tmpParent, values.get(2),
				getChildByPosition(2),
				getChildByPosition(3));
		
		//get the correct child index
		int childIndex = tmpParent.getChildIndex(this.values.get(0));
		if(childIndex < tmpParent.children.size()) 
			tmpParent.children.remove(childIndex); //remove old child
		//insert childs
		tmpParent.children.add(childIndex, right);
		tmpParent.children.add(childIndex, left);
		
		return tmpParent;
	}

	/***
	 * Gets the element 
	 * @param elem
	 * @return
	 */
	public T get(T elem) {
		int idx = values.indexOf(elem);
		if (idx == -1)
			throw new NoSuchElementException("Element "+elem+" not found");
		
		return values.get(idx);
	}

	/***
	 * Checks if the node contains the value
	 * @param elem
	 * @return
	 */
	public boolean contains(T elem) {
		return values.contains(elem);
	}

	/***
	 * Adds a value to the node
	 * @param value
	 */
	public void addValue(T value) {
		values.add(value);
		//sort the values
		values.sort(comparator);
	}

	/***
	 * Gets the parent
	 * @return
	 */
	public TTFNode<T> getParent() {
		return parent;
	}
	
	/***
	 * Gets the first value
	 * @return
	 */
	public T getFirstValue() {
		if(values.isEmpty()) {
			throw new NoSuchElementException("Node is empty");
		}
		return values.get(0);
	}
	
	/***
	 * Gets the last value according to node size
	 * @return
	 */
	public T getLastValue() {
		if(values.isEmpty()) {
			throw new NoSuchElementException("Node is empty");
		}
		return values.get(values.size()-1);
	}
	
	/***
	 * Gets the first child
	 * @return
	 */
	public TTFNode<T> getFirstChild() {
		if(children.isEmpty()) return null;
		return children.get(0);
	}
	
	/***
	 * Gets the last child
	 * @return
	 */
	public TTFNode<T> getLastChild() {
		if(children.isEmpty()) return null;
		return children.get(children.size()-1);
	}
	
	/***
	 * gets the child if available
	 * @param index
	 * @return 
	 */
	private TTFNode<T> getChildByPosition(int index) {
		if(index < children.size())
			return children.get(index);
		return null;
	}
	
	/***
	 * calculates the correct child index for the given element
	 * @param elem
	 * @return
	 */
	private int getChildIndex(T elem) {
		if (Util.compareElements(values.get(0), elem, comparator) > 0)
			return 0;
		else if (values.size() == 1
				|| Util.compareElements(values.get(1), elem, comparator) > 0)
			return 1;
		else if (values.size() == 2
				|| Util.compareElements(values.get(2), elem, comparator) > 0)
			return 2;
		else
			return 3;
	}
}
