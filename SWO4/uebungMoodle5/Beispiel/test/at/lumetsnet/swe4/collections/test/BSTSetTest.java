package at.lumetsnet.swe4.collections.test;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import at.lumetsnet.swe4.collections.BSTSet;
import at.lumetsnet.swe4.collections.SortedTreeSet;


public class BSTSetTest extends SortedTreeSetTestBase {
  
  @Override
  protected <T> SortedTreeSet<T> createSet(Comparator<T> comparator) {  
    return new BSTSet<T>(comparator);
  }
  

  @Test
  public void testEmptyContructor() {
	  BSTSet<Integer> set = new BSTSet<Integer>();
	  assertEquals(0, set.size());
	  assertEquals(-1, set.height());
  }
  
  @Test
  public void testHeight() {
	  SortedTreeSet<Integer> set = createSet();
	  set.add(2);
	  assertEquals(0, set.height());
	  set.add(1);
	  assertEquals(1, set.height());
	  set.add(0);
	  assertEquals(2, set.height());
  }
  
  @Test
  public void testEmptyTreeHeight() {
	  SortedTreeSet<Integer> set = createSet();
	  assertEquals(-1, set.height());
  }

}