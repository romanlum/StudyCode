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
		if(left != null) {
			left.parent = this;
			children.add(0, left);
		}
		if(right != null) {
			right.parent = this;
			children.add(1, right);
		}
	}

	void getVals(List<T> iteratorList) {
		if (children.size() != 0) {
			for (int i = 0; i < values.size(); i++) {
				children.get(i).getVals(iteratorList);
				iteratorList.add(values.get(i));
			}
			children.get(children.size() - 1).getVals(iteratorList);
		} else
			iteratorList.addAll(values);
	}

	public boolean isFull() {
		return values.size() == 3;
	}

	public boolean hasChildren() {
		return children.size() != 0;
	}

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

	public TTFNode<T> getChild(T elem) {
		if (children.size() == 0)
			return null;
		return (children.get(getChildIndex(elem)));
	}

	private TTFNode<T> getChildByPosition(int index) {
		if(index < children.size())
			return children.get(index);
		return null;
	}
	public TTFNode<T> split() {
		TTFNode<T> tmpParent = parent;
		if (tmpParent == null) {
			tmpParent = new TTFNode<T>(comparator,null);
		}
		tmpParent.addValue(values.get(1));
		
		TTFNode<T> left = new TTFNode<T>(comparator, tmpParent, values.get(0),
				getChildByPosition(0),
				getChildByPosition(1));
		
		TTFNode<T> right = new TTFNode<T>(comparator, tmpParent, values.get(2),
				getChildByPosition(2),
				getChildByPosition(3));
		
		int childIndex = tmpParent.getChildIndex(this.values.get(0));
		if(childIndex < tmpParent.children.size()) 
			tmpParent.children.remove(childIndex);
		tmpParent.children.add(childIndex, right);
		tmpParent.children.add(childIndex, left);

		if (tmpParent.values.size() == 3)
			return tmpParent.split();

		return tmpParent;
	}

	public T get(T elem) {
		int idx = values.indexOf(elem);
		if (idx == -1)
			return null;
		return values.get(idx);
	}

	public boolean contains(T elem) {
		return values.contains(elem);
	}

	public void setChild(int pos, TTFNode<T> child) {
		if (pos < 0 && pos > 3)
			throw new IndexOutOfBoundsException("Index not valid");

		if (child == null)
			return; // TODO: check

		if (children.size() < (pos + 1))
			children.add(pos, child);
		else
			children.set(pos, child);
	}

	public TTFNode<T> getChild(int pos) {
		if (pos < 0 && pos > 3)
			throw new IndexOutOfBoundsException("Index not valid");
		if ((pos + 1) < children.size()) {
			return children.get(pos);
		}
		return null;
	}

	public void addValue(T value) {
		values.add(value);
		values.sort(comparator);
	}

	public T getValue(int pos) {
		return values.get(pos);
	}
	
	public TTFNode<T> getParent() {
		return parent;
	}
	
	public T getLastValue() {
		return values.get(values.size()-1);
	}
	
	public TTFNode<T> getLastChild() {
		if(children.size() == 0) return null;
		return children.get(children.size()-1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		values.forEach(x -> builder.append(x + ","));
		return builder.toString();
	}

}
