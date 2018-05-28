package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
	//S1:DFS
	//time: O(N)
	//space: system stack logN, O(1)
	public List<String> bianryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		if (root == null) return result;
		
		StringBuilder curPath = new StringBuilder();
		curPath.append(root.val + "->");
		dfs(root, result, curPath);
		return result;
	}
	
	private void dfs(TreeNode root, List<String> result, StringBuilder curPath) {
		if (root.left == null && root.right == null) {
			int l = curPath.length();
			result.add(new String(curPath.delete(l - 2, l)));
		}
		
		int len = curPath.length();
		if (root.left != null) {
			curPath.append(root.left.val + "->");
			dfs(root.left, result, curPath);
			curPath.delete(len, curPath.length());
		}
		if (root.right != null) {
			curPath.append(root.right.val + "->");
			dfs(root.right, result, curPath);
			curPath.delete(len ,curPath.length());
		}
	}
	
}
