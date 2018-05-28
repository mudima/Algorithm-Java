package Leetcode;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class IntersectionOfTwoLinkedLists {
	//S1: longer one check firstly
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		int sizeA = 0, sizeB = 0;
		ListNode curA = headA, curB = headB;
		while (curA != null) {
			sizeA++;
			curA = curA.next;
		}
		while (curB != null) {
			sizeB++;
			curB = curB.next;
		}
		return sizeA > sizeB ? help(headB, headA, sizeB, sizeA) : help(headA, headB, sizeA, sizeB);
	}
	private ListNode help(ListNode headS, ListNode headL, int sizeS, int sizeL) {
		ListNode curS = headS, curL = headL;
		while (sizeS < sizeL) {
			curL = curL.next;
			sizeL--;
		}
		while (curL != null && curS != null) {
			if (curL == curS) return curL;
			else {
				curS = curS.next;
				curL = curL.next;
			}
		}
		return null;
	}
	
	//S2: each pointer goes 2 lists
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		ListNode curA = headA, curB = headB;
		while (curA != curB) {
			curA = curA == null ? headB : curA.next;
			curB = curB == null ? headA : curB.next;
		}
		return curA;
	}
	
	//Test main()
	public static void main() {}
	
}
