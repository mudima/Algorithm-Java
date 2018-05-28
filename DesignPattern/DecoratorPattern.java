package DesignPattern;

import java.util.*;

public class DecoratorPattern {
	//Static Decorator, like Peeklterator (xxIterator)
	//Dynamic Decorator: use the interface to do the dynamic binding to the original objects.
}

class PeekingIterator implements Iterator<Integer> {
	private Iterator<Integer> iter;
	private Integer peek;

	public PeekingIterator(Iterator<Integer> iterator) {
		iter = iterator;
		peek = null;
	}
	//Return the next element in the iterator without advancing the iterator
	public Integer peek() {
		if (peek != null) return peek;
		peek = iter.next();
		return peek;
	}
	//hasNext() and next() should behave the same as in the Iterator interface.
	@Override
	public Integer next() {
		if (peek != null) {
			Integer temp = peek;
			peek = null;
			return temp;
		}
		return iter.next();
	}
	@Override
	public boolean hasNext() {
		if (peek != null) return true;
		return iter.hasNext();
	}
}