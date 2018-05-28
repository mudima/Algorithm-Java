package Leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
	//S1: BFS-level order traversal, keep max for current level
	//time: O(n)
	//space: queue n = O(n)
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> result = new LinkedList<>();
		if (root == null) return result;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				max = Math.max(max, cur.val);
				if (cur.left != null) queue.offer(cur.left);
				if (cur.right != null) queue.offer(cur.right);
			}
			result.add(max);
		}
		return result;
	}
	
	//S2: DFS-pre order traversal recursively, use level to expand and update result list
	//time: each node n * list.get n = O(n*n)
	//space: O(1)
	public List<Integer> largestValuesS2(TreeNode root) {
		List<Integer> result = new LinkedList<>();
		if (root == null) return result;
		
		preOrder(result, root, 0);
		return result;
	}
	
	private void preOrder(List<Integer> result, TreeNode root, int level) {
		if (root == null) return;
		
		if (result.size() == level) {
			result.add(root.val);
		} else {
			if (result.get(level) < root.val) {
				result.set(level, root.val);
			}
		}
		
		preOrder(result, root.left, level + 1);
		preOrder(result, root.right, level + 1);
	}
}
