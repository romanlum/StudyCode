package swe4.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import swe4.collections.BSTMultiSet;
import swe4.collections.SortedMultiSet;

public class BSTMultiSetTest {
	
	@Test
	public void testAdd() {
		SortedMultiSet<Integer> s = new BSTMultiSet<>();
		assertEquals(0, s.size());
		s.add(5);
		assertEquals(1, s.size());
		s.add(17);
		assertEquals(2, s.size());
	}
	
	@Test
	public void testGet() {
		SortedMultiSet<Integer> s = new BSTMultiSet<>();
		s.add(5);
		s.add(1);
		s.add(3);
		assertEquals(1, s.get(1).intValue());
		assertEquals(3, s.get(3).intValue());
	}
	
	@Test(expected = NoSuchElementException.class) 
	public void testException() {
		SortedMultiSet<Integer> s = new BSTMultiSet<Integer>();
		s.add(5);
		s.add(1);
		
		Iterator<Integer> it = s.iterator();
		it.next();
		it.next();
		it.next();
	}
}