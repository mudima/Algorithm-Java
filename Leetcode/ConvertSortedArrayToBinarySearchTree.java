package Leetcode;

public class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
	        if (nums == null || nums.length == 0) return null;
			int len = nums.length;
			return sortedArrayToBST(nums, 0, len - 1);
	}
	    
	private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		if (start > end)
			if (start == end) {
				TreeNode leaf = new TreeNode(nums[start]);
				return leaf;
			}
			
			int mid = start + (end - start) / 2;
			TreeNode cur = new TreeNode(nums[mid]);
			if (mid != start) cur.left = sortedArrayToBST(nums, start, mid - 1);
			if (mid != end) cur.right = sortedArrayToBST(nums, mid + 1, end);
			return cur;
	}
}
