package swe4.threading;

import java.util.LinkedList;
import java.util.Queue;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.NoFixedFacet;

import swe4.util.Util;


// Buffer offers the basic functionality of a java.util.concurrent.BlockingQueue.
// get -> take, put -> put
public class Buffer {
  private Queue<Character> queue; // element collection
  private int              max;   // maximum size of queue
                                  // Buffer will block when additional
                                  // elements are inserted.

  public Buffer(int max) {
    this.queue = new LinkedList<>();
    this.max = max;
  } // Buffer

  private  void print(char ch, String dir, String postfix) {
    System.out.printf("Buffer  (%.3f): %c %s [", Util.currTime(), ch, dir);
    int i=0;
    for (char c : queue)
      System.out.print((i++ > 0 ? ", " : "") + c);
    System.out.println("]" + postfix);
  }

  public  boolean empty() {
    return queue.isEmpty();
  } // empty

  public  boolean full() {
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
} // Buffer

