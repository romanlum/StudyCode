/**
 * 
 */
package at.fh_ooe.sew4.stack3;

import at.fh_ooe.sew4.stack1.StackException;

class MyClass {

	@Override
	public String toString() {
		return "Instance of MyClass";
	}
}

/**
 * @author romanlum
 *
 */

@SuppressWarnings("rawtypes")
public class Stack3<T> {

	private int max;
	private int top;
	private Object[] data;

	public Stack3() {
		this(10);
	}

	public Stack3(int max) {
		this.max = max;
		data = new Object[max];
		top = -1;
	}

	public boolean empty() {
		return top < 0;
	}

	public boolean full() {
		return top == max - 1;
	}

	public void push(T o) throws StackException {
		if (full()) {
			throw new StackException("Stack is full!");
		}
		System.out.println("Insert object "+ o);
		top++;
		data[top] = o;
	}

	public T pop() throws StackException {
		if (empty()) {
			throw new StackException("Stack is empty");
		}
		
		top--;
		System.out.println("Removed object "+ data[top+1]);
		return (T)data[top - 1];

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack3<MyClass> s = new Stack3<MyClass>(10);
		Stack3<String> s2 = new Stack3<>(); // java >= 1.7
		try {
			s.push(new MyClass());
			s2.push("Hugo");
			s2.push("Hallo");
		} catch (StackException e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
