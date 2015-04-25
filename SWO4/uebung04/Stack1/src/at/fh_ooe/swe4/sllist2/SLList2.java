package at.fh_ooe.swe4.sllist2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLList2<T> {

	private static class Node<T> {
		public Node<T> next;
		public T val;
		
		public Node(Node<T> next, T val) {
			this.next = next;
			this.val = val;
		}
	}
	
	private static class SLListIterator<T> implements Iterator<T> {
		
		private Node<T> cur;
		
		public SLListIterator(Node<T> head) {
			this.cur = head;
		}

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public T next() {
			if(cur == null) {
				throw new NoSuchElementException();
			}
			T n = cur.val;
			cur = cur.next;
			return n;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private Node<T> head;
	private Node<T> tail;
	
	public void prepend(T obj) {
		head = new Node<T>(head,obj);
		if(tail == null) {
			tail = head;
		}
	}
	
	public SLListIterator<T> iterator() {
		return new SLListIterator<>(head);
	}
	
	public static void main(String[] args) {
		SLList2<String> sl2 = new SLList2<>();
		sl2.prepend("test1");
		sl2.prepend("test2");
		Iterator<String> it = sl2.iterator();
		while(it.hasNext()) {
			System.out.println("Item: " + it.next());
		}

	}

}
