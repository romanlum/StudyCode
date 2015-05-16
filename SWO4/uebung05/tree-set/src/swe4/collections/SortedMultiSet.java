package swe4.collections;

import java.util.*;

public interface SortedMultiSet<T extends Comparable<T>> 
	extends Iterable<T> 
{
	void add(T elem);
	boolean contains(T elem);
	T get(T elem);
	int size();
}