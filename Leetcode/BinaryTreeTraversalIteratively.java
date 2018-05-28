package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTraversalIteratively {
	//pre-order traversal
	public List<Integer> preOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;
		
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				result.add(cur.val);
				cur = cur.left;
			} else {
				cur = stack.pop().right;
			}
		}
		return result;
	}
	
	//in-order traversal
	public List<Integer> inOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;
		
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				cur = stack.pop();
				result.add(cur.val);
				cur = cur.right;
			}
		}
		return result;
	}
	
	//post-order traversal
	public List<Integer> postOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode cur = null, prev = null;
		while (!stack.isEmpty()) {
			cur = stack.peek();
			if (prev == null || prev.left == cur || prev.right == cur) {
				if (cur.left != null) {
					stack.push(cur.left);
				} else if (cur.right != null) {
					stack.push(cur.right);
				} else {
					result.add(stack.pop().val);
				}
			} else if (prev == cur.left) { //finish traverse its left subtree
				if (cur.right != null) {
					stack.push(cur.right);
				} else {
					result.add(stack.pop().val);
				}
			} else if (prev == cur.right) { //finish traverse its right subtree
				result.add(stack.pop().val);
			}
			prev = cur;
		}
		return result;
	}
}
