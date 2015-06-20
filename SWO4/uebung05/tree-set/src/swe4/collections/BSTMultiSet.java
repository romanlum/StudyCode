package swe4.collections;

import java.util.*;

public class BSTMultiSet<T extends Comparable<T>> implements SortedMultiSet<T> {

	public static class Node<T> {
		private T value;
		private Node<T> left, right;
		
		Node(T val, Node<T> left, Node<T> right) {
			this.left = left;
			this.right = right;
			this.value = val;
		}
	}
	
	private static class BSTIterator<T> implements Iterator {

		private Stack<Node<T>> unvisitedParents = new Stack<>();
		
		public BSTIterator(Node<T> root) {
			Node<T> next = root;
			while(next != null) {
				unvisitedParents.push(next);
				next = next.left;
			}
		}
		
		@Override
		public boolean hasNext() {
			return !unvisitedParents.isEmpty();
		}

		@Override
		public Object next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Stack is empty");
			}
			
			Node<T> cur = unvisitedParents.pop();
			Node<T> next = cur.right;
			while(next != null) {
				unvisitedParents.add(next);
				next = next.left;
			}
			return cur.value;
		}
		
	}
	
	private Node<T> root;
	private int size;
	
	
	public BSTMultiSet() {
		this.root = null;
	}
	
	private Node<T> addRecursive(Node<T> parent, T element) {
		if(parent == null) {
			size++;
			return new Node<T>(element, null, null);
		}
		if(element.compareTo(parent.value) < 0) {
			parent.left = addRecursive(parent.left, element);
		} else {
			parent.right = addRecursive(parent.right, element);
		}
		return parent;
	}

	@Override
	public Iterator<T> iterator() {
		return new BSTIterator<>(root);
	}

	@Override
	public void add(T elem) {
		root = addRecursive(root, elem);

	}

	@Override
	public boolean contains(T elem) {
		return get(elem) != null;
	}

	@Override
	public T get(T elem) {
		Node<T> t = root;
		while( t != null) {
			int cmpRes = t.value.compareTo(elem);
			if(cmpRes == 0 ) {
				return t.value;
			} else if(cmpRes > 0) {
				t = t.left;
			} else {
				t = t.right;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		traverInOrder(root, sb);
		sb.append("]");
		return sb.toString();
	}

	private void traverInOrder(Node<T> t, StringBuffer sb) {
		if( t != null) {
			traverInOrder(t.left, sb);
			if(sb.length() > 1) sb.append(",");
			sb.append(t.value);
			traverInOrder(t.right, sb);
		}
	}
}