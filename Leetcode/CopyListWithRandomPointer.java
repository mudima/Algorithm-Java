package Leetcode;

import java.util.HashMap;


class RandomListNode {
	int label;
	RandomListNode next, random;
	public RandomListNode(int x) {
		this.label = x;
	}
}

public class CopyListWithRandomPointer {
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) return head;
		
		RandomListNode cur = head;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		
		while (cur != null) {
			//update cur Node self
			if (!map.containsKey(cur)) {
				RandomListNode newNode = new RandomListNode(cur.label);
				map.put(cur, newNode);
			}
			//update cur.next
			if (cur.next != null && !map.containsKey(cur.next)) {
				RandomListNode newNext = new RandomListNode(cur.next.label);
				map.put(cur.next, newNext);
				map.get(cur).next = newNext;
			} else {
				map.get(cur).next = map.get(cur.next);
			}
			//update cur.random
			if (cur.random != null && !map.containsKey(cur.random)) {
				RandomListNode newRandom = new RandomListNode(cur.random.label);
				map.put(cur.random, newRandom);
				map.get(cur).random = newRandom;
			} else {
				map.get(cur).random = map.get(cur.random);
			}
			cur = cur.next;
		}
		return map.get(head);
	}
	
	//Test Main()
	public static void main(String[] args) {
		CopyListWithRandomPointer test = new CopyListWithRandomPointer();
		RandomListNode oHead = new RandomListNode(1);
		RandomListNode o2 = new RandomListNode(2);
		RandomListNode o3 = new RandomListNode(3);
		oHead.next = o2;
		oHead.random = o3;
		o2.next = o3;
		RandomListNode cur = oHead;
		while (cur != null) {
			System.out.print(cur.label);
			cur = cur.next;
		}
		System.out.println("");
		RandomListNode newHead = test.copyRandomList(oHead);
		RandomListNode curNew = newHead;
		while (curNew != null) {
			System.out.print(curNew.label);
			curNew = curNew.next;
		}	}
}
