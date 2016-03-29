package swe4.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

import swe4.util.Util;

// Buffer offers the basic functionality of a java.util.concurrent.BlockingQueue.
// get -> take, put -> put
public class BufferService extends UnicastRemoteObject implements Buffer {
	private Queue<Character> queue; // element collection
	private int max; // maximum size of queue
						// Buffer will block when additional
						// elements are inserted.

	public BufferService(int max) throws RemoteException {
		this.queue = new LinkedList<>();
		this.max = max;
	} // Buffer

	private void print(char ch, String dir, String postfix) {
		System.out.printf("Buffer  (%.3f): %c %s [", Util.currTime(), ch, dir);
		int i = 0;
		for (char c : queue)
			System.out.print((i++ > 0 ? ", " : "") + c);
		System.out.println("]" + postfix);
	}

	public boolean empty() {
		return queue.isEmpty();
	} // empty

	public boolean full() {
		return queue.size() >= max;
	} // full

	public synchronized void put(char ch) {
		while (full()) {
			print(ch, "==>", ": rejected because of full buffer!");
			Util.wait(this);
		} // while

		print(ch, "==>", "");
		queue.offer(ch);
		notifyAll();
	} // put

	public synchronized char get() {
		while (empty()) {
			System.out.println("Consumer: Buffer empty.");
			Util.wait(this);
		} // while
		char ch = queue.poll();
		print(ch, "<==", "");
		notifyAll();
		return ch;
	} // get

	private static int getPort(String hostPort) {
		int idx = hostPort.lastIndexOf(':');
		if (idx == -1) {
			return 1099;
		} else {
			return Integer.parseInt(hostPort.substring(idx + 1));
		}
	}

	public static void main(String[] args) {
		// version2:
		try {
			LocateRegistry.createRegistry(getPort(args[0]));
			BufferService service = new BufferService(10);
			//Remote serviceStub = UnicastRemoteObject.exportObject(service,0); // 0 nimm registry port
			
			Naming.rebind("rmi://" + args[0] + "/Buffer",service);

			System.out.println("Service available on port " + "rmi://"
					+ args[0] + "/Buffer");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
} // Buffer

