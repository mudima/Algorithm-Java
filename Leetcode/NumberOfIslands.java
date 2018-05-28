package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 无向图求联通块：
 * 遍历每一个点，如果是‘1’，进行search并标记visited
 */
public class NumberOfIslands {
	//S1: DFS
	//Time: O(m*n)
	//Space: O(m*n) -> can reduce to O(1) if grid[][] is mutable. change all visited '1' to '0'.
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
		
		int row = grid.length, col = grid[0].length;
		int result = 0;
		boolean[][] visited = new boolean[row][col]; //false:unvisited, true:visited
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1' && visited[i][j] == false) {
					dfs(grid, visited, i, j);
					result++;
				}
			}
		}
		return result;
	}
	private void dfs(char[][] grid, boolean[][] visited, int xIndex, int yIndex) {
		int row = grid.length, col = grid[0].length;
		if (xIndex >= 0 && xIndex < row && yIndex >= 0 && yIndex < col && grid[xIndex][yIndex] == '1' && visited[xIndex][yIndex] == false) {
			visited[xIndex][yIndex] = true;
			
			dfs(grid, visited, xIndex - 1, yIndex);
			dfs(grid, visited, xIndex + 1, yIndex);
			dfs(grid, visited, xIndex, yIndex - 1);
			dfs(grid, visited, xIndex, yIndex + 1);
		}
	}
	
	//S2: BFS
	//Time: O(m*n)
	//Space: queue size less than O(m*n)
	class Cell {
		char val;
		int x;
		int y;
		public Cell(char value, int xIndex, int yIndex) {
			val = value;
			x = xIndex;
			y = yIndex;
		}
	}
	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
		
		int row = grid.length, col = grid[0].length;
		int result = 0;
		Queue<Cell> queue = new LinkedList<Cell>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					queue.offer(new Cell(grid[i][j], i, j));
					while (!queue.isEmpty()) {
						Cell cur = queue.poll();
						grid[cur.x][cur.y] = '0';
						if (cur.x - 1 >= 0 && grid[cur.x - 1][cur.y] == '1') queue.offer(new Cell('1', cur.x - 1, cur.y));
						if (cur.x + 1 < row && grid[cur.x + 1][cur.y] == '1') queue.offer(new Cell('1', cur.x + 1, cur.y));
						if (cur.y - 1 >= 0 && grid[cur.x][cur.y - 1] == '1') queue.offer(new Cell('1', cur.x, cur.y - 1));
						if (cur.x - 1 < col && grid[cur.x][cur.y + 1] == '1') queue.offer(new Cell('1', cur.x, cur.y + 1));
					}
					result++;
				}
			}
		}
		return result;
	}
	
	//S3: Union Find
}
