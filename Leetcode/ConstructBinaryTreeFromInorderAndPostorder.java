package Leetcode;

public class ConstructBinaryTreeFromInorderAndPostorder {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
		
		int len = inorder.length;
		
		return buildTree(inorder, 0, len - 1, postorder, 0, len - 1);
	}
	
	private TreeNode buildTree(int[] inorder,int inStart, int inEnd, int[] postorder, int pStart, int pEnd) {
		int len = inorder.length;
		if (pStart == pEnd) {
			TreeNode leaf = new TreeNode(postorder[pStart]);
			return leaf;
		}
		
		if (inStart == inEnd) {
			TreeNode leaf = new TreeNode(inorder[inStart]);
			return leaf;
		}
		
		int inIdx = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (postorder[pEnd] == inorder[i]) {
				inIdx = i;
				break;
			}
		}
		
		TreeNode cur = new TreeNode(postorder[pEnd]);
		int leftNodes = inIdx - inStart;
		int rightNodes = inEnd - inIdx;
		if (inIdx < len - 1) {
			cur.right = buildTree(inorder,inIdx + 1, inEnd, postorder, pEnd - rightNodes, pEnd - 1);
		}
		if (inIdx > 0) {
			cur.left = buildTree(inorder, inStart, inIdx - 1, postorder, pStart, pStart + leftNodes - 1);
		}
		
		return cur;
	}
}
