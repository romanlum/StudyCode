package at.lumetsnet.swe4.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BSTSet<T> extends AbstractSortedTreeSet<T>  {

	/***
	 * Node helper class
	 * @author romanlum
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		private T value;
		private Node<T> left, right;

		Node(T val, Node<T> left, Node<T> right) {
			this.left = left;
			this.right = right;
			this.value = val;
		}
	}

	/***
	 * Iterator class
	 * @author romanlum
	 *
	 * @param <T>
	 */
	private static class BSTIterator<T> implements Iterator<T> {

		private Stack<Node<T>> unvisitedParents = new Stack<>();

		public BSTIterator(Node<T> root) {
			Node<T> next = root;
			while (next != null) {
				unvisitedParents.push(next);
				next = next.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !unvisitedParents.isEmpty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Stack is empty");
			}

			Node<T> cur = unvisitedParents.pop();
			Node<T> next = cur.right;
			while (next != null) {
				unvisitedParents.add(next);
				next = next.left;
			}
			return cur.value;
		}

	}

	private Node<T> root;
	

	public BSTSet() {
		this(null);
	}

	public BSTSet(Comparator<T> comparator) {
		super(comparator);
		root = null;
		level = -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new BSTIterator<>(root);
	}

	/***
	 * Adds an element to the set
	 * 
	 * @param elem
	 * @return true if the item was added, otherwise false
	 */
	@Override
	public boolean add(T elem) {
		int curLevel = -1;
		
		Node<T> newNode = new Node<>(elem, null, null);

		if (root == null) {
			root = newNode;
		} else {
			Node<T> current = root;
			curLevel++;
			while (current != null) {
				int cmpResult = compareElements(current.value, elem);
				if (cmpResult == 0) return false; //duplicate element
				
				if( cmpResult > 0) {
					if (current.left == null) {
						current.left = newNode;
						break;
					} else {
						current = current.left;
					}
				} else {
					if (current.right == null) {

						current.right = newNode;
						break;
					} else {
						current = current.right;
					}
				}
				curLevel++;
			}
		}
		size++;
		curLevel++;
		
		if(curLevel > level)
			level = curLevel; //update current level
		return true;
	}

	/***
	 * Searches for an element in the set
	 * 
	 * @param elem
	 * @return found element or null if the element was not found
	 */
	@Override
	public T get(T elem) {
		Node<T> t = root;
		while (t != null) {
			int cmpRes = compareElements(t.value, elem);
			if (cmpRes == 0) {
				return t.value;
			} else if (cmpRes > 0) {
				t = t.left;
			} else {
				t = t.right;
			}
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
		if (root == null) {
			throw new NoSuchElementException("Set is empty");
		}
		Node<T> tmp = root;
		while (tmp.left != null) {
			tmp = tmp.left;
		}
		return tmp.value;
	}

	/***
	 * Gets the biggest element of the set
	 * 
	 * @return
	 */
	@Override
	public T last() {
		if (root == null) {
			throw new NoSuchElementException("Set is empty");
		}
		Node<T> tmp = root;
		while (tmp.right != null) {
			tmp = tmp.right;
		}
		return tmp.value;
	}


}