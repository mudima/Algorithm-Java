package Leetcode;

public class MinimumPathSum {
	//S1: DFS recursively,从右下到左上
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
		
		return dfs(grid, grid.length - 1, grid[0].length - 1);
	}
	private int dfs(int[][] grid, int x, int y) {
		if (x == 0 && y == 0) return grid[0][0];
		
		int left = (x == 0) ? Integer.MAX_VALUE : dfs(grid, x - 1, y);
		int right = (y == 0) ? Integer.MAX_VALUE : dfs(grid, x, y - 1);
		return Math.min(left, right) + grid[x][y];
	}
	
	//S2： DP
	public int minPathSumS2(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
		
		int row = grid.length, col = grid[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}
}
