00000000.0.0.0..0..0..0.0
..package Leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache {
	//S1: HashMap + doubly LinkedList
	private class Node {
		int key;
		int value;
		Node prev;
		Node next;
		public Node(int key, int value) {
		this.key = key;
		this.value = value;
		prev = null;
		next = null;
	}
	}

	private int capacity;
	private HashMap<Integer, Node> map;
	private Node dummyHead;
	private Node dummyTail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, Node>();
		dummyHead = new Node(-1, -1);
		dummyTail = new Node(-1, -1);
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}
	public int get(int key) {
		if (!map.containsKey(key)) return -1;
		
		Node target = map.get(key);
		target.prev.next = target.next;
		target.next.prev = target.prev;
		target.next = dummyTail;
		target.prev = dummyTail.prev;
		target.prev.next = target;
		dummyTail.prev = target;
		
		return target.value;
	}
	public void put(int key, int value) {
		if (map.containsKey(key)) {
		Node cur = map.get(key);
		cur.value = value;
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		cur.next = dummyTail;
		cur.prev = dummyTail.prev;
		dummyTail.prev = cur;
		return;
		}
		if (map.size() == capacity) {
			Node old = dummyHead.next;
			dummyHead.next = old.next;
			old.next.prev = dummyHead;
			old.next = null;
			old.prev = null;
		}		
		Node newNode = new Node(key, value);
		newNode.next = dummyTail;
		newNode.prev = dummyTail.prev;
		newNode.prev.next = newNode;
		dummyTail.prev = newNode;
		map.put(key, newNode);
	}

	
	//S2: LinkedHashMap override removeEldest()
	private LinkedHashMap<Integer, Integer> map;
	private final int cacheCapacity;
	public LRUCache(int capacity) {
		cacheCapacity = capacity;
		map = new LinkedHashMap<Integer, Integer>(cacheCapacity, 0.75f, true){
			@Override
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > cacheCapacity;
			}
		};
	}
	public int get(int key) {
		return map.getOrDefault(key, -1);
	}
	public void put(int key, int value) {
		map.put(key, value);
	}
	
	
	//Test Main()
	public static void main(String[] args) {
		LRUCache test = new LRUCache(3);
		test.put(1, 1);
		test.put(2, 2);
		test.put(3, 3);
		test.get(1);
		test.put(4, 4);
		//System.out.print(test.dummyHead.next.value);
		
		Node cur = test.dummyHead;
		while (cur.next != test.dummyTail) {
			cur = cur.next;
			System.out.print(cur.value + ",");
		}
	}
}
