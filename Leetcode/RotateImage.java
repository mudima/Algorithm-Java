package Leetcode;

import java.util.Arrays;

public class RotateImage {
	//4 for loops
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
		
		rotate(matrix, 0, matrix.length);
	}
	private void rotate(int[][] matrix, int offset, int size) {
		if (size == 0 || size == 1) return;
		
		int[] temp = new int[size - 1];
		for (int i = 0; i < size - 1; i++) {
			temp[i] = matrix[offset + size - 1 - i][offset];
			matrix[offset + size - 1 - i][offset] = matrix[offset + size - 1][offset + size - 1 - i];
		}
		for (int i = 0; i < size - 1; i++) {
			matrix[offset + size - 1][offset + size - 1 - i] = matrix[offset + i][offset + size - 1];
		}
		for (int i = 0; i < size - 1; i++) {
			matrix[offset + i][offset + size - 1] = matrix[offset][offset + i];
		}
		for (int i = 0; i < size - 1; i++) {
			matrix[offset][offset + i] = temp[i];
		}
		rotate(matrix, offset + 1, size - 2);
	}
	
	//symmetric swap
	public void rotate(int[][] matrix) {
		for(int i = 0; i<matrix.length; i++){
			for(int j = i; j<matrix[0].length; j++){
				int temp = 0;
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for(int i =0 ; i<matrix.length; i++){
			for(int j = 0; j<matrix.length/2; j++){
				int temp = 0;
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length-1-j];
				matrix[i][matrix.length-1-j] = temp;
			}
		}
	}
	
	
	//Test Main()
	public static void main(String[] args) {
		RotateImage test = new RotateImage();
		//int[][] image1 = null;
		//int[][] image2 = new int[][] {{1}};
		//int[][] image3 = new int[][] {{1, 2}, {3, 4}};
		int[][] image4 = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		System.out.println(Arrays.deepToString(image4));
		test.rotate(image4);
		System.out.println(Arrays.deepToString(image4));
	}
}
