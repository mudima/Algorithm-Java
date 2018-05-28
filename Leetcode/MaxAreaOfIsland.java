package Leetcode;

import java.text.DateFormatSymbols;

public class MaxAreaOfIsland {
	// DFS + visited
	// time:
	// space:
	public int maxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
			return 0;

		int rows = grid.length, cols = grid[0].length;
		boolean[][] visited = new boolean[rows][cols];
		int maxArea = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (visited[i][j] == false && grid[i][j] == 1) {
					int curSol = dfs(grid, visited, i, j);
					maxArea = Math.max(maxArea, curSol);
				}
			}
		}
		return maxArea;
	}

	private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
		int rows = grid.length, cols = grid[0].length;
		if (i >= 0 && i < rows && j >= 0 && j < cols && visited[i][j] == false && grid[i][j] == 1) {
			visited[i][j] = true;
			return 1 + dfs(grid, visited, i - 1, j) + dfs(grid, visited, i + 1, j) + dfs(grid, visited, i, j - 1) + dfs(grid, visited, i, j + 1);
		}

		return 0;
	}

	public static void main(String[] args) {
		MaxAreaOfIsland test = new MaxAreaOfIsland();
		int[][] grid = {{1}};
		boolean[][] visited = {{false}};
		System.out.println(test.dfs(grid, visited, 0, 0));

	}
}
