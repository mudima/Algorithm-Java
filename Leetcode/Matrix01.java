package Leetcode;

import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
	//S1: BFS with queue
	//start with all 0s in matrix, then expand 4 directions if is unvisited ever, and update values
	//time: O(m*n)
	//space: queue m*n + result m*n = O(m*n)
	public int[][] updateMtrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return matrix;
		
		int rows = matrix.length, cols = matrix[0].length;
		int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int[][] result = new int[rows][cols];
		
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(new int[] {i, j});
					result[i][j] = 0;
				} else {
					result[i][j] = -1;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int distance = result[cur[0]][cur[1]];
			for (int[] dir : directions) {
				int x = cur[0] + dir[0], y = cur[1] + dir[1];
				if (x >= 0 && x < rows && y >= 0 && y < cols && result[x][y] == -1) {
					result[x][y] = distance + 1;
					queue.offer(new int[] {x, y});
				}
			}
		}
		return result;
	}
	
	//S2: DFS
	//time: initial m*n + dfs for one point m*n * m*n points = O((m*n)^2)
	//space: result m*n
	public int[][] updateMatrixS2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return matrix;
		
		int rows = matrix.length, cols = matrix[0].length;
		int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int[][] result = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 0) {
					result[i][j] = 0;
				} else {
					result[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (result[i][j] == 0) {
					dfs(matrix, result, i, j);
				}
			}
		}
		
		return result;
	}
	
	private void dfs(int[][] matrix, int[][] result, int x, int y) {
		int rows = matrix.length, cols = matrix[0].length;
		int cur = result[x][y];
		
		if (x - 1 >= 0 && matrix[x - 1][y] != 0 && result[x - 1][y] > cur + 1) {
			result[x - 1][y] = cur + 1;
			dfs(matrix, result, x - 1, y);
		}
		if (x + 1 < rows && matrix[x + 1][y] != 0 && result[x + 1][y] > cur + 1) {
			result[x + 1][y] = cur + 1;
			dfs(matrix, result, x + 1, y);
		}
		if (y - 1 >= 0 && matrix[x][y - 1] != 0 && result[x][y - 1] > cur + 1) {
			result[x][y - 1] = cur + 1;
			dfs(matrix, result, x, y - 1);
		}
		if (y + 1 < cols && matrix[x][y + 1] != 0 && result[x][y + 1] > cur + 1) {
			result[x][y + 1] = cur + 1;
			dfs(matrix, result, x, y + 1);
		}
	}
}
