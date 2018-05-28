package Leetcode;

import java.util.Iterator;

public class PeekingIterator {
	private Iterator<Integer> iter;
	private Integer peek;
	
	public PeekingIterator(Iterator<Integer> iterator) {
		//initailize any member here.
		iter = iterator;
		peek = null;
	}
	
	//Return the next element in the iterator without advancing the iterator
	public Integer peek() {
		if (peek != null) return peek;
		return peek = iter.next();
	}
	
	//hasNext() and next() should behave the same as in the iterator interface.
	//Override them if needed.
	@Override
	public Integer next() {
		if (peek != null) {
			Integer res = peek;
			peek = null;
			return res;
		}
		return iter.next();
	}
	
	@Override
	public boolean hasNext() {
		if (peek == null) return iter.hasNext();
		return true;
	}
}
