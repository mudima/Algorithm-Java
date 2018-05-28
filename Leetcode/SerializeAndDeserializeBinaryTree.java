package Leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		val = value;
		left = null;
		right = null;
	}
}

public class SerializeAndDeserializeBinaryTree {
	
	private final String nullNode = "#";
    private final String delimiter = ",";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nullNode);
            sb.append(delimiter);
            return;
        }
        sb.append(root.val).append(delimiter);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<String>();
        String[] dataArray = data.split(delimiter);
        Collections.addAll(queue, dataArray);
        return deserialize(queue);
    }
    private TreeNode deserialize(Queue<String> queue) {
        if (queue.size() == 0) return null;
        String s = queue.poll();
        TreeNode cur = null;
        if (!s.equals(nullNode)) {
            cur = new TreeNode(Integer.valueOf(s));
            cur.left = deserialize(queue);
            cur.right = deserialize(queue);
        }
        return cur;
    }
    
    //Test Main()
    public static void main(String[] args) {
    	SerializeAndDeserializeBinaryTree test = new SerializeAndDeserializeBinaryTree();
    	TreeNode root = new TreeNode(1);
    	TreeNode left = new TreeNode(2);                         //1
    	TreeNode right = new TreeNode(3);                    //2      3
    	root.left = left;                                //4  null  null  5
    	root.right = right;
    	TreeNode leftLeaf = new TreeNode(4);
    	TreeNode rightLeaf = new TreeNode(5);
    	left.left = leftLeaf;
    	right.right = rightLeaf;
    	
    	String result = test.serialize(root);
    	System.out.println(result);
    	TreeNode newTree = test.deserialize(result);
    	System.out.println(test.serialize(newTree));
    }
}
