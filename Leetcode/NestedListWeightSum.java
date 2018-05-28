package Leetcode;

import java.util.List;

public class NestedListWeightSum {
	//DFS:
	//time: O(n)
	//space: O(1)
	public int depthSum(List<NestedInteger> nestedList) {
		if (nestedList == null) return 0;
		
		return dfs(nestedList, 1);
	}
	private int dfs(List<NestedInteger> list, int level) {
		if (list == null) return 0;
		
		int sum = 0;
		for (NestedInteger node : list) {
			if (node.isInteger()) {
				sum += node.getInteger() * level;
			} else {
				List<NestedInteger> cur = node.getList();
				sum += dfs(cur, level + 1);
			}
		}
		return sum;
	}
}
