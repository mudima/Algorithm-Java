package Leetcode;

import java.util.Stack;

public class FlattenBinaryTreetoLinkedList {
	//S1: pre order traversal recursively, root.right -> root.next
	public void flatten(TreeNode root) {
		if (root == null) return;
		
		TreeNode prev = null;
		preOrder(root, prev);
	}
	private void preOrder(TreeNode root, TreeNode prev) {
		if (root == null) return;
		
		if (prev != null) {
			prev.left = null;
			prev.right = root;
		}
		prev = root;
		preOrder(root.left, prev);
		preOrder(root.right, prev);
	}
	
	//S2: divide and conquer recursively 
	public void flattenS2(TreeNode root) {
		if (root == null) return;
		
		divideAndConquer(root);
	}
	private TreeNode[] divideAndConquer(TreeNode root) {
		if (root == null) return null;
		if (root.left == null && root.right == null) {
			TreeNode[] res = new TreeNode[2];
			res[0] = root;
			res[1] = root;
			return res;
		}
		
		TreeNode[] left = divideAndConquer(root.left);
		TreeNode[] right = divideAndConquer(root.right);
		root.left = null;
		TreeNode[] res = new TreeNode[2];
		res[0] = root;
		if (left != null && right != null) {
			root.right = left[0];
			left[1].right = right[0];
			res[1] = right[1];
		} else if (left == null) {
			root.right = right[0];
			res[1] = right[1];
		} else {
			root.right = left[0];
			res[1] = left[1];
		}
		return res;
	}
	
	//S3: stack
	public void flatten3(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null) {
			if (cur.right != null) stack.push(cur.right);
			if (cur.left != null) {
				cur.right = cur.left;
				cur.left = null;
			} else if (!stack.isEmpty()) {
				TreeNode top = stack.pop();
				cur.right = top;
			}
			cur = cur.right;
		}
	}
}
