package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthofBinaryTree {
	//S1: recursively, getDepth from left and right
	//time: O(N)
	//space: O(1)
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		
		int leftDepth = minDepth(root.left);
		int rightDepth = minDepth(root.right);
		
		if (leftDepth == 0 && rightDepth == 0) return 1;
		if (leftDepth == 0) return rightDepth + 1;
		if (rightDepth == 0) return leftDepth + 1;
		return Math.min(leftDepth, rightDepth) + 1;
	}
	
	//S2: level order traversal, stop at the first leaf node
	//time: O(N)
	//space: queue n O(N)
	public int minDepthS2(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int depth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur.left == null && cur.right == null) return depth;
				if (cur.left != null) queue.offer(cur.left);
				if (cur.right != null) queue.offer(cur.right);
			}
			depth++;
		}
		return depth;
	}
}
