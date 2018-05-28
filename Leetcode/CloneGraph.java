package Leetcode;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {
	//S1: traversal graph by BFS, use hashmap to check visited
	//time: O(N)
	//space: queue n + visited n = O(N)
	public UndirectedGraphNode cloneGraphS1(UndirectedGraphNode node) {
		if (node == null) return null;
		if (node.neighbors == null || node.neighbors.size() == 0) return new UndirectedGraphNode(node.label);
		
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		queue.offer(node);
		HashMap<Integer, UndirectedGraphNode> visited = new HashMap<>();
		UndirectedGraphNode newGraph = new UndirectedGraphNode(node.label);
		visited.put(node.label, newGraph);
		
		while (!queue.isEmpty()) {
			UndirectedGraphNode curNode = queue.poll();
			UndirectedGraphNode newNode = visited.get(curNode.label);
			for (UndirectedGraphNode nei : curNode.neighbors) {
				if (!visited.containsKey(nei.label)) {
					queue.offer(nei);
					visited.put(nei.label, new UndirectedGraphNode(nei.label));
				}
				newNode.neighbors.add(visited.get(nei.label));
			}
		}
		return newGraph;
	}
	
	//S2:DFS with hashmap to check visited
	//time: O(N)
	//space: hashmap O(N)
	public UndirectedGraphNode cloneGraphS2(UndirectedGraphNode node) {
		if (node == null) return node;
		if (node.neighbors == null || node.neighbors.size() == 0) return new UndirectedGraphNode(node.label);
		
		UndirectedGraphNode newGraph = new UndirectedGraphNode(node.label);
		HashMap<Integer, UndirectedGraphNode> visited = new HashMap<>();
		
		return dfs(node, visited);
	}
	
	private UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> visited) {
		//base case
		if (node == null) return null;
		if (visited.containsKey(node.label)) return visited.get(node.label);
		
		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		visited.put(node.label, newNode);
		for (UndirectedGraphNode nei : node.neighbors) {
			newNode.neighbors.add(dfs(nei, visited));
		}
		return newNode;
	}
}


class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	
	public UndirectedGraphNode(int x) {
		this.label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}
