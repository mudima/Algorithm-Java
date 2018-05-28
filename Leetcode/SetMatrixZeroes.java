package Leetcode;
/*
 * Use row[0] and col[0] as marks to record if this row/col has 0 to set
 * O(m) check row[0] has 0 or not -> boolean rowZero
 * O(n) check col[0] has 0 or not -> boolean colZero
 * O(m*n) to mark
 * O(m*n) to set
 * O(m) to set row[0]
 * O(n) to set col
 * Time: O(m*n)
 * Space: O(1)
 */
public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
		
		boolean firstRowHasZero = false;
		boolean firstColHasZero = false;
		int row = matrix.length, col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			if (matrix[i][0] == 0) {
				firstRowHasZero = true;
				break;
			}
		}
		for (int i = 0; i < col; i++) {
			if (matrix[0][i] == 0) {
				firstColHasZero = true;
				break;
			}
		}
		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if (firstRowHasZero) {
			for (int i = 0; i < row; i++) {
				if (matrix[i][0] != 0) matrix[i][0] = 0; 
			}
		}
		if (firstColHasZero) {
			for (int i = 0; i < col; i++) {
				if (matrix[0][i] != 0) matrix[0][i] = 0; 
			}
		}
	}
	
	//only use 2 for loop sets
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
		
		//boolean firstRowHasZero = false;
		boolean firstColHasZero = false;
		int row = matrix.length, col = matrix[0].length;
		
		for (int i = 0; i < row; i++) {
			if (matrix[i][0] == 0) firstColHasZero = true;
			for (int j = 1; j < col; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = row - 1; i >= 0; i--) {
			for (int j =  col - 1; j >= 1; j--) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
			if (firstColHasZero) matrix[i][0] = 0;
		}
		
	}
}
