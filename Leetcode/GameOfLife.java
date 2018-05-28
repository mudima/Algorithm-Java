package Leetcode;
/*
 * Solution: int -> 32 bits = 16 newState + 16 curState; state = 0 dead/1 alive
 * time: 8 * N -> O(N)
 * space: directions O(1) -> in place with original board O(1)
 */
public class GameOfLife {
	public void gameOfLife(int[][] board) {
		if (board == null || board[0].length == 0) return;
		
		int row = board.length, col = board[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int alive = checkNeib(board, i, j);
				int curState = board[i][j] & 0xFFFF, newState = 0;
				if (curState == 1 && alive < 2) newState = 0;
				else if (curState == 1 && (alive == 2 || alive == 3)) newState = 1;
				else if (curState == 1 && alive > 3) newState = 0;
				else if (curState == 0 && alive == 3) newState = 1;
				
				board[i][j] = (newState << 16) | curState;
			}
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = board[i][j] >> 16;
			}
		}
	}
	
	private int checkNeib(int[][] board, int i, int j) {
		int row = board.length, col = board[0].length;
		int alive = 0;
		int[][] directions = {
				{-1, -1},
				{-1, 0},
				{-1, 1},
				{0, -1},
				{0, 1},
				{1, -1},
				{1, 0},
				{1, -1}
		};
		for (int[] dir : directions) {
			int x = i + dir[0], y = j + dir[1];
			if (x >= 0 && x < row && y >= 0 && y < col && (board[x][y] & 0xFFFF) == 1) {
				alive++;
			}
		}
		return alive;
	}
}
