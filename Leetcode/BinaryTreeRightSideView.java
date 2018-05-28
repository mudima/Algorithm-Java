package Leetcode;

import java.util.*;

public class BinaryTreeRightSideView {
	//S1: leve order traversal
	//Time: O(n)
	//Space: O(n) -> queue
	public List<Integer> rightSideViewS1(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				if (size == 1) result.add(queue.peek().val);
				TreeNode cur = queue.poll();
				if (cur.left != null) queue.offer(cur.left);
				if (cur.right != null) queue.offer(cur.right);
				size--;
			}
		}
		return result;
	}
	
	//S2: recursively
	//Time: O(n)
	//Space: O(1)
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;
		
		rightSideView(root, 0, result);
		return result;
	}
	private void rightSideView(TreeNode root, int level, List<Integer> result) {
		if (root == null) return;
		if (result.size() == level) result.add(root.val);
		rightSideView(root.right, level + 1, result);
		rightSideView(root.left, level + 1, result);
		
	}
}
