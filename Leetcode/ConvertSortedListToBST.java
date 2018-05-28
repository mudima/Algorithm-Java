package Leetcode;

public class ConvertSortedListToBST {
	//S1: divide and conquer, while loop find middle point
	//Time: O(NlogN)
	//Space: O(N)
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return new TreeNode(head.val);
		
		return findSubTree(head, null);
	}
	
	private TreeNode findSubTree(ListNode start, ListNode end) {
		if (start == end) {
			return new TreeNode(start.val);
		}
		
		ListNode slow = start;
		ListNode fast = start;
		
		while (fast.next != end && fast.next.next != end) {
			slow = slow.next;
			fast = fast.next.next;
		}
		TreeNode mid = new TreeNode(slow.val);
		
		if (slow != start) mid.left = findSubTree(start, slow);
		if (slow.next != end) mid.right = findSubTree(slow.next, end);
		
		return mid;
	}
	
}
