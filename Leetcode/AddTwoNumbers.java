package Leetcode;
/*
 * The digits are stored in reverse order.
 */
public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode dummy = new ListNode(0), cur = dummy;
		while (l1 != null && l2 != null) {
			int curVal = l1.val + l2.val + carry;
			carry = curVal < 10 ? 0 : 1; 
			cur.next = new ListNode(curVal % 10);
			cur = cur.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		if (l2 != null) {
			while (l2 != null) {
				int curVal = l2.val + carry;
				carry = curVal < 10 ? 0 : 1;
				cur.next = new ListNode(curVal % 10);
				cur = cur.next;
				l2 = l2.next;
			}
		} else if (l1 != null) {
			while (l1 != null) {
				int curVal = l1.val + carry;
				carry = curVal < 10 ? 0 : 1;
				cur.next = new ListNode(curVal % 10);
				cur = cur.next;
				l1 = l1.next;
			}
		}
		if (carry != 0) {
			cur.next = new ListNode(1);
		}
		return dummy.next;
	} 
}
