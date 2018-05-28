package Leetcode;

import java.util.Stack;

/*
 * stack always store left nodes
 * time: amortised O(1), each node in/out stack once
 * space: O(logN) = O(h)
 */
public class BinarySearchTreeIterator {
	private Stack<TreeNode> stack;
	
	public BinarySearchTreeIterator(TreeNode root) {
		stack = new Stack<>();
		
		TreeNode cur = root;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
	}
	
	/**@return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	/**@return the next smallest number */
	public int next() {
		TreeNode result = stack.pop();
		
		TreeNode cur = result.right;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
		return result.val;
	}
}
