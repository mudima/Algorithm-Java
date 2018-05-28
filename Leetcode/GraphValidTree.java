package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphValidTree {
	/*
	 * S1: DSF
	 * hashmap<id, list<childrens>> for each node
	 * boolean dfs() to check if every node in list is valid children
	 * time: node N + edge N-1 --> O(N)
	 * space: hashmap N + visited N --> O(N)
	 */
	public boolean validTreeDFS(int n, int[][] edges) {
		int len = edges.length;
		if (edges == null || n != len + 1) return false; //必要不充分条件
		
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			List<Integer> list = new ArrayList<>();
			map.put(i, list);
		}
		boolean[] visited = new boolean[n];
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}
		
		if (!dfs(0, -1, map, visited)) return false;
		for (boolean v : visited) { //保证联通，所有点在同一集合
			if (!v) return false;
		}
		return true;
	}
	
	private boolean dfs(int cur, int parent, HashMap<Integer, List<Integer>> map, boolean[] visited) {
		if (visited[cur] == true) return false;
		visited[cur] = true;
		
		for (int child : map.get(cur)) {
			if (child != parent && dfs(child, cur, map, visited) == false) {
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * S2: BFS
	 * hashmap<id, list<children>> for each pair
	 * time: O(N)
	 * space: hashmap O(N) + visited O(N) + queue O(N) --> O(N)
	 */
	public boolean validTreeBFS(int n, int[][] edges) {
		if (edges == null || n != edges.length + 1) return false;
		
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			List<Integer> list = new ArrayList<>();
			map.put(i, list);
		}
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}
		
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (visited[cur] == false) return false; //查环
			visited[cur] = true;
			for (int child : map.get(cur)) { //如果有环会被加入queue两次
				if (!visited[child]) queue.offer(child);
			}
		}
		
		for (boolean v : visited) {
			if (v == false) return false;
		}
		
		return true;
	}
	
	/*
	 * S3: Union Find
	 * time: O(NlogN)
	 * space: int[] ids, sz O(N)
	 */
	class UnionFind {
		public int count; //count number of sets
		public int[] ids,sz;
		
		public UnionFind(int n) {
			this.count = n;
			this.ids = new int[n];
			this.sz = new int[n];
			for (int i = 0; i < n; i++) {
				ids[i] = i;
				sz[i] = 1;
			}
		}
		
		public boolean find(int p, int q) {
			return root(p) == root(q);
		}
		
		public void union(int p, int q) {
			int rootP = root(p), rootQ = root(q);
			if (sz[rootP] < sz[rootQ]) {
				ids[rootP] = rootQ;
				sz[rootQ] += sz[rootP];
				count--;
			} else {
				ids[rootQ] = rootP;
				sz[rootP] += sz[rootQ];
				count--;
			}
		}
		
		public int root(int p) {
			int tempP = p;
			while (ids[p] != p) {
				ids[p] = ids[ids[p]];
				p = ids[p];
			}
			ids[tempP] = p;
			return p;
		}
	}
	
	public boolean validTreeUionFind(int n, int[][] edges) {
		if (edges == null || n != edges.length + 1) return false;
		
		UnionFind uf = new UnionFind(n);
		for (int[] edge : edges) {
			int p = edge[0], q = edge[1];
			if (!uf.find(p, q)) {
				uf.union(p, q);
			} else {
				return false;
			}
		}
		return uf.count == 1;
	}
}
