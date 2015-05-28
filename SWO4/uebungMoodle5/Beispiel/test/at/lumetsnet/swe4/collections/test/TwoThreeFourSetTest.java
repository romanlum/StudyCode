package at.lumetsnet.swe4.collections.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import at.lumetsnet.swe4.collections.SortedTreeSet;
import at.lumetsnet.swe4.collections.TwoThreeFourTreeSet;

public class TwoThreeFourSetTest extends SortedTreeSetTestBase {
  
  @Override
  protected <T> TwoThreeFourTreeSet<T> createSet(Comparator<T> comparator) {  
    return new TwoThreeFourTreeSet<T>(comparator);
  }
  
  @Test
  public void testEmptyConstructor() {
	  TwoThreeFourTreeSet<Integer> set = new TwoThreeFourTreeSet<>();
	  assertEquals(null, set.comparator());
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
  public void testSingleNodeHeight() {
	  SortedTreeSet<Integer> set = createSet();
	  set.add(1);
	  assertEquals(0, set.height());
	  assertEquals(1, set.size());
  }
  
  @Test
  public void testHeightAfterSplit() {
	  SortedTreeSet<Integer> set = createSet();
	  set.add(1);
	  set.add(2);
	  set.add(3);
	  set.add(4); //split
	  assertEquals(1, set.height());
	  assertEquals(4, set.size());
	  
  }
  
}
