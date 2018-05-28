package Leetcode;

public class PalindromeLinkedList {
	/*
	 * find middle -> reverse back half part -> check elements
	 * Time: n + n/2 + n/2 = O(n)
	 * Space: O(1)
	 */
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) return true;
		//find middle point
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode firstHalf = head, secondHalf = slow.next;
		slow.next = null;
		//reverse second half list
		ListNode newSecondHalf = reverse(secondHalf);
		//check elements value identity 
		while (firstHalf != null && newSecondHalf != null) {
			if (firstHalf.val != newSecondHalf.val) return false;
			firstHalf = firstHalf.next;
			newSecondHalf = newSecondHalf.next;
		}
		return true;
	}
	private ListNode reverse(ListNode head) {
		if (head.next == null) return head;
		ListNode cur = head, pre = null;
		while (cur != null) {
			ListNode nextCur = cur.next;
			cur.next = pre;
			pre = cur;
			cur = nextCur;
		}
		return pre;
	}
	//Test main()
	public static void main(String[] args) {
		PalindromeLinkedList test = new PalindromeLinkedList();
		//list: 1->2->2->3
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(1);
		System.out.println(test.isPalindrome(head));
	}
}
