package Leetcode;

import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue {
	//S1: BFS traversal whole tree
	//update most left value for each level
	//time: O(n)
	//space: queue n O(n)
	public int findBottomLeftValue(TreeNode root) {
		if (root.left == null && root.right == null) return root.val;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int leftBottomValue = root.val;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (i == 0) {
					leftBottomValue = cur.val;
				}
				if (cur.left != null) queue.offer(cur.left);
				if (cur.right != null) queue.offer(cur.right);
			}
		}
		return leftBottomValue;
	}
	
	//S2: DFS
	//divide and conquer, return int[] {leftValue, depth} from left and right, return deepest leftValue
	//time: traversal every node once O(n)
	//space: result 2 * n = O(n)
	public int findBottomLeftValueS2(TreeNode root) {
		if (root.left == null && root.right == null) return root.val;
		
		int[] result = leftBottomValue(root, 0);
		return result[0];
	}
	
	private int[] leftBottomValue(TreeNode root, int depth) {
		if (root == null) return null;
		if (root.left == null && root.right == null) return new int[] {root.val, depth};
		
		int[] leftRes = leftBottomValue(root.left, depth + 1);
		int[] rightRes = leftBottomValue(root.right, depth + 1);
		
		if (leftRes == null) {
			return rightRes;
		} else if (rightRes == null) {
			return leftRes;
		} else {
			return leftRes[1] < rightRes[1] ? rightRes : leftRes; 
		}
		
	}
}
