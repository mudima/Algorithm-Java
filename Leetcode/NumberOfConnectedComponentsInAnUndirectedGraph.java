package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	/*
	 * S1: DFS
	 * hashmap to store every edge for each node
	 * time: O(N) each node is touched once, runtime 16ms
	 * space: O(N) hashmap N + visited N
	 */
	public int countComponentsDFS(int n, int[][] edges) {
		if (n <= 0) return 0;
		if (edges == null || edges.length == 0) return n;
		
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
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				dfs(i, -1, map, visited);
				count++;
			}
		}
		return count;
	}
	
	private void dfs(int cur, int parent, HashMap<Integer, List<Integer>> map, boolean[] visited) {
		if (visited[cur] == true) return;
		
		visited[cur] = true;
		for (int child : map.get(cur)) {
			if (child != parent && visited[child] == false) {
				dfs(child, cur, map, visited);
			}
		}
	}
	
	/*
	 * S2: BFS 
	 * hashmap,visited, queue
	 * time: O(N) for each node only offer/poll to the queue once, runtime 21ms
	 * space: hashmap N + visited N + queue N --> O(N)
	 */
	public int countComponentsBFS(int n, int[][] edges) {
		if (n <= 0) return 0;
		if (edges == null || edges.length == 0) return n;
		
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
		Queue<Integer> queue = new LinkedList<>();
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				queue.offer(i);
				visited[i] = true;
				while (!queue.isEmpty()) {
					int cur = queue.poll();
					for (int child : map.get(cur)) {
						if (visited[child] == false) {
							queue.offer(child);
							visited[child] = true;
						}
					}
				}
				count++;
			}
		}
		return count;
	}
	
	/*
	 * S3: Union Find
	 * time: find O(1), union O(logN), for each edge logN, total N-1 edges --> O(NlogN)
	 * space: O(N)
	 */
	private class UnionFind {
		int count;
		int[] ids, sz;
		
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
				ids[p] = rootQ;
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
	

	public int countComponentsUnionFind(int n, int[][] edges) {
		if (n <= 0) return 0;
		if (edges == null || edges.length == 0) return n;
		
		UnionFind uf = new UnionFind(n);
		for (int[] edge : edges) {
			int p = edge[0], q = edge[1];
			if (!uf.find(p, q)) {
				uf.union(p, q);
			}
		}
		return uf.count;
	}
}
