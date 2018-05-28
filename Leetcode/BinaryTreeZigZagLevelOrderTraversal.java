package Leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class BinaryTreeZigZagLevelOrderTraversal {
	public List<List<Integer>> zigZagTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) return result;
		
		Deque<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean oddLevel = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> curLevel = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				if (oddLevel) {
					TreeNode cur = queue.pollFirst();
					curLevel.add(cur.val);
					if (cur.right != null) queue.offerLast(cur.right);
					if (cur.left != null) queue.offerLast(cur.left);
				} else {
					TreeNode cur = queue.pollLast();
					curLevel.add(cur.val);
					if (cur.left != null) queue.offerFirst(cur.left);
					if (cur.right != null) queue.offer(cur.right);
				}
			}
			result.add(curLevel);
			oddLevel = (!oddLevel);
		}
		return result;
	}
	
	public static void main(String[] args) {
		BinaryTreeZigZagLevelOrderTraversal test = new BinaryTreeZigZagLevelOrderTraversal();
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3= new TreeNode(3);
		root.left = n2;
		root.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4;
		n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		n3.left = n6;
		n3.right = n7;
		
		List<List<Integer>> res = test.zigZagTraversal(root);
		for (int i = 0; i < res.size(); i++) {
			List<Integer> curLevel = res.get(i);
			for (int j = 0; j < curLevel.size(); j++) {
				System.out.print(curLevel.get(j));
			}
			System.out.println();
		}

	}
}
