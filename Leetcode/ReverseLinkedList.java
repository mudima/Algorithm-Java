package Leetcode;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}

public class ReverseLinkedList {
	//S1: dummy node 神龙摆尾
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode dummy = new ListNode(0), cur = head;
		while (cur != null) {
			ListNode node = cur;
			cur = cur.next;
			node.next = dummy.next;
			dummy.next = node;
		}
		return dummy.next;
	}
	
	//S2: while loop
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode pre = null, cur = head;
		while (cur != null) {
			ListNode nextCur = cur.next;
			cur.next = pre;
			pre = cur;
			cur = nextCur;
		}
		return pre;
	}
	
	//S3: recursion
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode newHead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		
		return newHead;
	}
}
