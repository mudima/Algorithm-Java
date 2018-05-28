package Leetcode;

class TreeLinkNode {
	int val;
	TreeLinkNode left;
	TreeLinkNode right;
	TreeLinkNode next;
	
	public TreeLinkNode(int value) {
		val = value;
		left = null;
		right = null;
		next = null;
	}
	public TreeLinkNode(int value, TreeLinkNode left, TreeLinkNode right) {
		val = value;
		this.left = left;
		this.right = right;
		next = null;
	}
}


public class PopulatNextRightPointersInEachNode {
	//S1: Iteratively level by level
	//time: O(N)
	//space: O(1)
	public void connect(TreeLinkNode root) {
		if (root == null || (root.left == null && root.right == null)) return;
		
		TreeLinkNode levelStart = root;
		while (levelStart != null) {
			TreeLinkNode cur = levelStart;
			while (cur != null) {
				if (cur.left != null && cur.right != null) cur.left.next = cur.right;
				if (cur.right != null && cur.next != null) cur.right.next = cur.next.left;
				cur = cur.next;
			}
			levelStart = levelStart.left;
		}
	}
	
	//S2: recursively top to bottom
	//time: 1+2+4+...+n/2-1 = O(n)
	//space: O(1)
	public void connectS2(TreeLinkNode root) {
		if (root == null) return;
		
		if (root.left != null) {
			root.left.next = root.right;
			if (root.next != null && root.right != null) {
				root.right.next = root.next.left;
			}
		}
		
		connectS2(root.left);
		connectS2(root.right);
	}
	
}
