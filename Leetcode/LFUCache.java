package Leetcode;

import java.util.HashMap;


public class LFUCache {
	
	class Node {
		int key;
		int val;
		int freq;
		Node prev;
		Node next;
		public Node(int key, int value) {
			this.key = key;
			this.val = value;
			freq = 0;
			prev = null;
			next = null;
		}
	}
	
	//S1: HashMap + Doubly LinkedList
	private HashMap<Integer, Node> map; 
	private int cacheCapacity;
	private Node dummyHead;
	private Node dummyTail;
	
	public LFUCache(int capacity) {
		cacheCapacity = capacity;
		map = new HashMap<>();
		dummyHead = new Node(-1, -1);
		dummyTail = new Node(-1, -1);
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}
	
	public int get(int key) {
		if (!map.containsKey(key)) return -1;
		
		Node target = map.get(key);
		target.freq++;
		updateFreq(target);
		
		return target.val;
	}
	
	public void put(int key, int value) {
		if (map.containsKey(key)) {
			map.get(key).freq++;
			map.get(key).val = value;
			updateFreq(map.get(key));
			return;
		}
		Node newNode = new Node(key, value);
		newNode.freq = 1;
		if (map.size() == cacheCapacity) {
			Node delete = dummyHead.next;
			dummyHead.next = delete.next;
			dummyHead.next.prev = dummyHead;
			delete.next = null;
			delete.prev = null;
		}
		insertNode(newNode, dummyHead.next);
		updateFreq(newNode);
		map.put(key, newNode);
		
	}
	
	
	private void deletNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
	}
	private void insertNode(Node target, Node nodeAfter) {
		target.next = nodeAfter;
		target.prev = nodeAfter.prev;
		nodeAfter.prev.next = target;
		nodeAfter.prev = target;
	}
	private void updateFreq(Node node) {
		if (node.freq < node.next.freq) return;
		Node cur = node.next;
		deletNode(node);
		while (cur != dummyTail && cur.freq <= node.freq) {
			cur = cur.next;
		}
		insertNode(node, cur);
	}
	
	
	//Test Main()
		public static void main(String[] args) {
			LFUCache test = new LFUCache(2);
			test.put(1, 1);
			test.put(2, 2);
			//test.get(1);
			test.put(3, 3);
			
			test.put(4, 4);
			test.put(5, 5);
			//System.out.print(test.dummyHead.next.val);
			
			Node cur = test.dummyHead;
			while (cur.next != test.dummyTail) {
				cur = cur.next;
				System.out.print(cur.val + ",");
			}
		}
}
