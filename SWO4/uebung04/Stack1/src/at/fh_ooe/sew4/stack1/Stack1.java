/**
 * 
 */
package at.fh_ooe.sew4.stack1;

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
public class Stack1 {

	private int max;
	private int top;
	private Object[] data;

	public Stack1() {
		this(10);
	}

	public Stack1(int max) {
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

	public void push(Object o) throws StackException {
		if (full()) {
			throw new StackException("Stack is full!");
		}
		System.out.println("Insert object "+ o);
		top++;
		data[top] = o;
	}

	public Object pop() throws StackException {
		if (empty()) {
			throw new StackException("Stack is empty");
		}
		
		top--;
		System.out.println("Removed object "+ data[top+1]);
		return data[top - 1];

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack1 s = new Stack1(10);
		try {
			s.push(1);
			s.push(new MyClass());
			s.push("Hugo");
		} catch (StackException e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
