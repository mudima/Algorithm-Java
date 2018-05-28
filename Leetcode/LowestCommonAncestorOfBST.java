package Leetcode;

/*
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
*/
public class LowestCommonAncestorOfBST {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) return null;
		
		int max = Math.max(p.val, q.val), min = Math.min(p.val, q.val);
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val >= min && cur.val <= max) return cur;
			else if (cur.val < min) cur = cur.right;
			else cur = cur.left;
		}
		return null;
	}
}
