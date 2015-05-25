package at.lumetsnet.swe4.collections.test;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import at.lumetsnet.swe4.collections.RedBlack;
import at.lumetsnet.swe4.collections.SortedTreeSet;
import at.lumetsnet.swe4.collections.TwoThreeFourTreeSet;

public class TwoThreeFourSetTest extends SortedTreeSetTestBase {
  
  @Override
  protected <T> TwoThreeFourTreeSet<T> createSet(Comparator<T> comparator) {  
    return new TwoThreeFourTreeSet<T>(comparator);
  }

  @Test
  public void testHeight() {
    final int NELEMS = 10000;
    SortedTreeSet<Integer> set = createSet();

    for (int i=1; i<=NELEMS; i++) {
      set.add(i);
      int h = set.height();
      int n = set.size();
      assertTrue("height(set) <= ld(size(set))+1", h <= Math.log((double)n)/Math.log(2.0)+1);
    }
  }
  
  @Test
  public void singleNodeTest() {
	  SortedTreeSet<Integer> set = createSet();
	  set.add(1);
	  set.add(2);
	  set.add(3);
	  
	  assertEquals((Integer)1, set.get(1));
	  assertEquals((Integer)2, set.get(2));
	  assertEquals((Integer)3, set.get(3));
  }
  
  @Test
  public void splitRootNodeTest() {
	  SortedTreeSet<Integer> set = createSet();
	  set.add(1);
	  set.add(2);
	  set.add(3);
	  set.add(4);
	  
	  assertEquals((Integer)1, set.get(1));
	  assertEquals((Integer)2, set.get(2));
	  assertEquals((Integer)3, set.get(3));
	  assertEquals((Integer)4, set.get(4));
  }
  
  @Test
  public void splitMiddleNodeTest() {
	  SortedTreeSet<Integer> set = createSet();
	  for(int i = 1 ; i <= 8; i++) {
		  set.add(i);
	  }
	  for(int i = 1 ; i <= 8; i++) {
		  assertEquals((Integer)i, set.get(i));
	  }
  }
  
  @Test
  public void split2ParentTest() {
	  SortedTreeSet<Integer> set = createSet();
	  for(int i = 15 ; i >= 1; i--) {
		  set.add(i);
	  }
	  for(int i = 1 ; i <=15; i++) {
		  assertEquals((Integer)i, set.get(i));
	  }
  }
}
