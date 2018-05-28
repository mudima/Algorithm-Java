package Leetcode;

import java.io.BufferedInputStream;
import java.text.DateFormatSymbols;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
	//S1: BFS + 2 queue for each ocean + 2 visited for each ocean
	//初始化两个visited, bfs for each grid, only expands equal or larger neighbors
	//time: 2 * bfs n*m = O(n*m)
	//space: 2 * (queue m*n + visited m*n) = O(n*m)
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> result = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return result;
		
		int rows = matrix.length, cols = matrix[0].length;
		boolean[][] pacific = new boolean[rows][cols];
		boolean[][] atlantic = new boolean[rows][cols];
		Queue<int[]> pQueue = new LinkedList<>();
		Queue<int[]> aQueue = new LinkedList<>();
		for (int i = 0; i < rows; i++) {
			pQueue.offer(new int[] {i, 0});
			pacific[i][0] = true;
			aQueue.offer(new int[] {i, cols - 1});
			atlantic[i][cols - 1] = true;
		}
		for (int i = 0; i < cols; i++) {
			pQueue.offer(new int[] {0, i});
			pacific[0][i] = true;
			aQueue.offer(new int[] {rows - 1, i});
			atlantic[rows - 1][i] = true;
		}
		
		bfs(matrix, pQueue, pacific);
		bfs(matrix, aQueue, atlantic);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					result.add(new int[] {i, j});
				}
			}
		}
		return result;
	}
	
	private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		int rows = matrix.length, cols = matrix[0].length;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1];
			if (x - 1 >= 0 && matrix[x - 1][y] >= matrix[x][y] && visited[x - 1][y] == false) {
				queue.offer(new int[] {x - 1, y});
				visited[x - 1][y] = true;
			}
			if (x + 1 < rows && matrix[x + 1][y] >= matrix[x][y] && visited[x + 1][y] == false) {
				queue.offer(new int[] {x + 1, y});
				visited[x + 1][y] = true;
			}
			if (y - 1 >= 0 && matrix[x][y - 1] >= matrix[x][y] && visited[x][y - 1] == false) {
				queue.offer(new int[] {x, y - 1});
				visited[x][y - 1] = true;
			}
			if (y + 1 < cols && matrix[x][y + 1] >= matrix[x][y] && visited[x][y + 1] == false) {
				queue.offer(new int[] {x, y + 1});
				visited[x][y + 1] = true;
			}
		}
	}
	
	
	//S2: DFS
	//2 visited for pacific and atlantic, recursively expand for neighbors and mark visited
	//time: 2 * dfs m*n = O(m*n)
	//space: 2 * visited m*n = O(m*n)
	public List<int[]> pacificAtlanticS2(int[][] matrix) {
		List<int[]> result = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return result;
		
		int rows = matrix.length, cols = matrix[0].length;
		boolean[][] pacific = new boolean[rows][cols];
		boolean[][] atlantic = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			dfs(matrix, pacific, i, 0);
			dfs(matrix, atlantic, i, cols - 1);
		}
		for (int i = 0; i < cols; i++) {
			dfs(matrix, pacific, 0, i);
			dfs(matrix, atlantic, rows - 1, i);
		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					result.add(new int[] {i, j});
				}
			}
		}
		return result;
	}
	
	private void dfs(int[][] matrix, boolean[][] visited, int x, int y) {
		int rows = matrix.length, cols = matrix[0].length;
		
		if (x - 1 >= 0 && matrix[x - 1][y] >= matrix[x][y] && visited[x - 1][y] == false) {
			visited[x - 1][y] = true;
			dfs(matrix, visited, x - 1, y);
		}
		if (x + 1 < rows && matrix[x + 1][y] >= matrix[x][y] && visited[x + 1][y] == false) {
			visited[x + 1][y] = true;
			dfs(matrix, visited, x + 1, y);
		}
		if (y - 1 >= 0 && matrix[x][y - 1] >= matrix[x][y] && visited[x][y - 1] == false) {
			visited[x][y - 1] = true;
			dfs(matrix, visited, x, y - 1);
		}
		if (y + 1 < cols && matrix[x][y + 1] >= matrix[x][y] && visited[x][y + 1] == false) {
			visited[x][y + 1] = true;
			dfs(matrix, visited, x, y + 1);
		}
		
	}
}
