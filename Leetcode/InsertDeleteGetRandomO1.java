package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDeleteGetRandomO1<E> {
	private ArrayList<E> list;
	private HashMap<E, Integer> map;
	private Random random;
	
	public InsertDeleteGetRandomO1() {
		list = new ArrayList<E>();
		map = new HashMap<E, Integer>();
		random = new Random();
	}
	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(E data) {
		if (map.containsKey(data)) return false;
		list.add(data);
		map.put(data, list.size() - 1);
		return true;
	}
	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(E data) {
		if (!map.containsKey(data)) return false;
		int index = map.get(data);
		E last = list.get(list.size() - 1);
		map.remove(data);
		map.put(last, index);
		list.remove(list.size() - 1);
		list.set(index, last);
		return true;
	}
	/** Get a random element from the set. */
	public E getRandom() {
		int index = random.nextInt(list.size());
		return list.get(index);
	}
}
