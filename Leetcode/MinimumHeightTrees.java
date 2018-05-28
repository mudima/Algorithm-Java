package Leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MinimumHeightTrees {
	//BFS: traversal the tree from leaves to root
	//for the longest path, find the middle node or two middle node
	//time:
	//space:
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> result = new LinkedList<>(); 
		//corner case
		if (n <= 0 || edges == null || edges.length != n - 1) return result;
		//build up graph
		List<Set<Integer>> graph = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new HashSet<Integer>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		//queue for BFS, only leaves can into queue
		List<Integer> leaves = new LinkedList<>();
		for (int i = 0; i < graph.size(); i++) {
			if (graph.get(i).size() == 1) {
				leaves.add(i);
			}
		}
		// BFS from leaves to root
		while (n > 2) {
			int size = leaves.size();
			for (int i = 0; i < size; i++) {
				int cur = leaves.remove(0);
				Set<Integer> adj = graph.get(cur);
				for (int ad : adj) {
					graph.get(ad).remove(cur);
					if (graph.get(ad).size() == 1) {
						leaves.add(ad);
					}
				}
			}
			n -= size;
		}
		
		return leaves;
	}
}	
