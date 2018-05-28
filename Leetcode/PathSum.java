package Leetcode;

import java.util.*;

public class PathSum {
	//S1: DFS recursively
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		if (root.left == null && root.right == null && sum == root.val) {
			return true;
		}
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}
	
	//S2: Pre-order traversal iteratively
	//Time: O(N)
	//Space: O(logN)
	static class NodeSum {
		TreeNode node;
		int curSum;
		NodeSum(TreeNode node, int val) {
			this.node = node;
			curSum = val;
		}
	} 
	public boolean hasPathSumS2(TreeNode root, int sum) {
		if (root == null) return false;
		Stack<NodeSum> stack = new Stack<>();
		stack.push(new NodeSum(root, root.val));
		while (!stack.isEmpty()) {
			NodeSum cur = stack.pop();
			if (cur.node.left == null && cur.node.right == null) {
				if (sum == cur.curSum) return true;
			} else if (cur.node.left != null) {
				stack.push(new NodeSum(cur.node.left, cur.curSum + cur.node.left.val));
			} else if (cur.node.right != null) {
				stack.push(new NodeSum(cur.node.right, cur.curSum + cur.node.right.val));
			}
		}
		return false;
	}
}
