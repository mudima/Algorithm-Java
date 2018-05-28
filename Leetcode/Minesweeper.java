package Leetcode;

import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.Queue;

/*
 * This is a typical Search problem, either by using DFS or BFS. Search rules:
 * If click on a mine ('M'), mark it as 'X', stop further search.
 * If click on an empty cell ('E'), depends on how many surrounding mine:
 *    2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
 *    2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
 */
public class Minesweeper {
	//BFS
	//time: O(m*n) each point in&out queue once
	//space: queue m*n O(m*n)
	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
						  {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	public char[][] updateBoardBFS(char[][] board, int[] click) {
		if (click == null || click.length == 0) return board;
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return board;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(click);
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if (board[click[0]][click[1]] == 'M') {//if hit mine, game over
				board[click[0]][click[1]] = 'X';
				return board;
			} else {//if not hit mine, search neighbors
				int adjMine = adjacentMine(board, cur);
				if (adjMine == 0) {
					board[cur[0]][cur[1]] = 'B';
					for (int[] dir : directions) {
						int x = cur[0] + dir[0], y = cur[1] + dir[1];
						if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'E') {
							queue.offer(new int[] {x, y});
							board[x][y] = 'B';
						}
					}
				} else {
					board[cur[0]][cur[1]] = (char)(adjMine + '0');
				}
			}
		}
		
		return board;
	}
	
	private int adjacentMine(char[][] board, int[] point) {
		int adjMine = 0;
		for (int[] dir : directions) {
			int x = point[0] + dir[0], y = point[1] + dir[1];
			if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] == 'M' || board[x][y] == 'X')) {
				adjMine++;
			}
		}
		return adjMine;
	}
	
	public static void main(String[] args) {
		int a = 1;
		char b = (char) (1 + 48);
		System.out.println(b);
	}
	
	//DFS
	//time: O(m*n) each point will be checked once
	//space: O(1)
	public char[][] updateBoardDFS(char[][] board, int[] click) {
		if (click == null || click.length == 0) return board;
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return board;
		
		dfs(board, click);
		return board;
	}
	
	private void dfs(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return;
		} else {
			int count = 0;
			for (int[] dir : directions) {
				int x = click[0] + dir[0], y = click[1] + dir[1];
				if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] == 'M' || board[x][y] == 'X')) {
					count++;
				}
			}
			
			if (count > 0) {
				board[click[0]][click[1]] = (char)(count + 48);
			} else {
				board[click[0]][click[1]] = 'B';
				for (int[] dir : directions) {
					int x = click[0] + dir[0], y = click[1] + dir[1];
					if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'E') {
						dfs(board, new int[] {x, y});
					}
				}
			}
		}
		
	}
}
