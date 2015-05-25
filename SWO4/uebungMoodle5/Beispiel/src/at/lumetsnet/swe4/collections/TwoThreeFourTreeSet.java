package at.lumetsnet.swe4.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TwoThreeFourTreeSet<T> extends AbstractSortedTreeSet<T> {

		
	private TTFNode<T> root;
	
	
	public TwoThreeFourTreeSet() {
		this(null);
	}
	public TwoThreeFourTreeSet(Comparator<T> comparator) {
		super(comparator);
		root = null;
	}
	@Override
	public boolean add(T elem) {
		if(root == null) {
			root = new TTFNode<>(comparator, null);
			root.addValue(elem);
			size++;
			return true;
		}
		
		TTFNode<T> tmp = root;
		while(tmp != null) {
			if(tmp.isFull()) {
				TTFNode<T> result = tmp.split();
				if(result.getParent() == null)
					root = result;
				tmp = result;
			} else {
				if(tmp.contains(elem))
					return false;
				
				if(tmp.hasChildren()) {
					tmp = tmp.getChild(elem);
				} else {
					tmp.addValue(elem);
					size++;
					return true;
				}
			}
		}
		//TODO:check exception here
		return false;
	}
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
	@Override
	public boolean contains(T elem) {
		return get(elem) != null;
	}
	
	@Override
	public T first() {
		if(root == null) {
			throw new NoSuchElementException("Set is empty");
		}
		TTFNode<T> tmp = root;
		while(tmp != null && tmp.getChild(0) != null) {
			tmp = tmp.getChild(0);
		}
		return tmp.getValue(0);
	}
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
	
	@Override
	public Iterator<T> iterator() {
		List<T> iteratorList = new ArrayList<T>();
		if(root != null)
			root.getVals(iteratorList);
		return iteratorList.iterator();
	}
	
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

}
