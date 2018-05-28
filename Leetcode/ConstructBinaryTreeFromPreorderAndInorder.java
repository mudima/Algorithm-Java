package Leetcode;

public class ConstructBinaryTreeFromPreorderAndInorder {
	//time: O(n) * logn = nlogn
	//space: O(1)
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
		
		return findSubtrees(0, 0, preorder.length - 1, preorder, inorder);
	}
	
	private TreeNode findSubtrees(int preIdx, int start, int end, int[] preorder, int[] inorder) {
		if (preIdx > preorder.length - 1 || start > end) return null;
		
		TreeNode cur = new TreeNode(preorder[preIdx]);
		int inIdx = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == cur.val) {
				inIdx = i;
				break;
			}
		}
		
		cur.left = findSubtrees(preIdx + 1, start, inIdx - 1, preorder, inorder);
		cur.right = findSubtrees(preIdx + inIdx - start + 1, inIdx + 1, end, preorder, inorder);
		
		return cur;
	}
}
