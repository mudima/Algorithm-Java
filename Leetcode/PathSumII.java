package Leetcode;

import java.util.*;

public class PathSumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) return result;
		
		List<Integer> sol = new ArrayList<>();
		dfs(root, sum, result, sol);
		return result;
	}
	private void dfs(TreeNode root, int sum, List<List<Integer>> result, List<Integer> sol) {
		if (root.left == null && root.right == null && sum == root.val) {
			sol.add(root.val);
			result.add(new ArrayList<Integer>(sol));
			sol.remove(sol.size() - 1);
			return;
		}
		
		if (root.left != null) {
			sol.add(root.val);
			dfs(root.left,  sum - root.val, result, sol);
			sol.remove(sol.size() - 1);
		}
		if (root.right != null) {
			sol.add(root.val);
			dfs(root.right, sum - root.val, result, sol);
			sol.remove(sol.size() - 1);
		}
	}
}
