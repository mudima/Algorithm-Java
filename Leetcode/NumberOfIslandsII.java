package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsII {
	//Q1
	public int[] numPic(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return null;
		
		int row = matrix.length, col = matrix[0].length;
		int i , j = 0;
		int[] result = new int[4];
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					result[0] = i;
					result[1] = j;
					int x = 0, y = 0;
					while (i + x < row && j + y < col && matrix[i + x][j + y] == 0) {
						x++;
						y++;
					}
					result[2] = x;
					result[3] = y;
					break;
				}
			}
			if (j != col) break; 
		}
		return result;
	}
	
	//Q2
	public List<int[]> findPics(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return null;
		
		int row = matrix.length, col = matrix[0].length;
		int i , j = 0;
		List<int[]> result = new ArrayList<int[]>();
		boolean[][] visited = new boolean[row][col];
		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				//left up point of rectangle
				if (matrix[i][j] == 0 && visited[i][j] == false) {
					int[] curRes = new int[4];
					visited[i][j] = true;
					curRes[0] = i;
					curRes[1] = j;
					int[] cur = checkZeros(matrix, visited, i, j);
					curRes[2] = cur[0];
					curRes[3] = cur[1];
					result.add(curRes);
				}
				
			} 
		}
		return result;
	}
	private int[] checkZeros(int[][] matrix, boolean[][] visited, int i, int j) {
		int row = matrix.length, col = matrix[0].length;
		int x, y = 0;
		for (x = 0; i + x < row; x++) {
			for (y = 0; j + y < col; y++) {
				if (matrix[i + x][j + y] == 0) {
					visited[i + x][j + y] = true;
				} else {
					break;
				}
			}
		}
		return new int[] {x, y};
	}
	
	
	//Q3
	
	public static void main(String[] args) {
		NumberOfIslandsII test = new NumberOfIslandsII();
		int[][] matrix1 = new int[][] {{1, 1}, {1, 1}};
		int[][] matrix2 = new int[][] {{0, 1}, {1, 0}};
		int[][] matrix3 = new int[][] {{0, 0}, {0, 0}};
		
		List<int[]> result = test.findPics(matrix2);
		if (result == null || result.size() == 0) System.out.println("null");
		else {
			for (int[] e : result) {
				System.out.print(Arrays.toString(e));
			}
		}
		//System.out.println("matrix2" + Arrays.deepToString(test.findPics(matrix2)));
		//System.out.println("matrix3" + Arrays.deepToString(test.findPics(matrix3)));
	}
}

