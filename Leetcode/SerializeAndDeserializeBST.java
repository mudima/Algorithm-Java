package Leetcode;

import java.util.*;

public class SerializeAndDeserializeBST {
	private final String delimiter = ","; 
	//S1:serialization: pre-order traversal -> recursion
	public String serialize(TreeNode root) {
		if (root == null) return null;
		
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		return sb.toString();
	}
	private void serialize(TreeNode root, StringBuilder sb) {
		if (root == null) return;
		sb.append(root).append(delimiter);
		serialize(root.left, sb);
		serialize(root.right, sb);
	}
	//S2:serialization: pre-order traversal -> while loop
	public String serialize(TreeNode root) {
		if (root == null) return null;
		
		StringBuilder sb = new StringBuilder();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			sb.append(cur.val).append(delimiter);
			if (cur.left != null) stack.push(cur.left);
			if (cur.right != null) stack.push(cur.right);
		}
		return sb.toString();
	}
	
	
	
	//S1:deserialization -> two queues, recursively
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0) return null;
		
		String[] dataArray = data.split(delimiter);
		Queue<Integer> queue = new LinkedList<>();
		for (String value : dataArray) {
			queue.offer(Integer.valueOf(value));
		}
		return deserialize(queue);
	}
	private TreeNode deserialize(Queue<Integer> queue) {
		if (queue.isEmpty()) return null;
		
		TreeNode cur = new TreeNode(queue.poll());
		Queue<Integer> leftTree = new LinkedList<>();
		while (!queue.isEmpty() && queue.peek() < cur.val) {
			leftTree.offer(queue.poll());
		}
		cur.left = deserialize(leftTree);
		cur.right = deserialize(queue);
		return cur;
	}
	
	//S2:deserialization -> keep Max/Min, recursively
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0) return null;
		
		
	}
}
