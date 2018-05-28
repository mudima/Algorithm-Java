package Leetcode;


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) {
		val = x;
	}
}

class Result {
	TreeNode node;
	boolean pExist;
	boolean qExist;
	public Result(TreeNode node, boolean p, boolean q) {
		this.node = node;
		pExist = p;
		qExist = q;
	}
}


public class LowestCommonAncestorOfBinaryTree {
	//p and q must exist in Binary Tree
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) return root;
		
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) return root;
		if (left == null) return right;
		if (right == null) return left;
		return null; 
	}
	
	//p and q may not exist in Binary Tree
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		Result res = LCA(root, p, q);
		if (res.pExist && res.qExist) return res.node;
		//at least one of p, q not exist in tree
		return null; 
	}
	private Result LCA(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) return new Result(null, false, false);
		
		Result left = LCA(root.left, p, q);
		Result right = LCA(root.right, p, q);
		
		boolean pExt = left.pExist || right.pExist || root == p;
		boolean qExt = left.qExist  || right.qExist || root == q;
		
		if (root == p || root == q) return new Result(root, pExt, qExt);
		if (left.node != null && right.node != null) return new Result(root, pExt, qExt);
		if (left.node == null) return new Result(right.node, pExt, qExt);
		if (right.node == null) return new Result(left.node, pExt, qExt);
		return new Result(null, false, false);
	}
}




