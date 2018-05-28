package Leetcode;

import java.util.*;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return result;
     
        spiralPrint(matrix, 0, matrix.length, matrix[0].length, result);
        return result;
    }
	
	private void spiralPrint(int[][] matrix, int offset, int rowSize, int colSize, List<Integer> result) {
		if (rowSize == 0 || colSize == 0) return;
		if (rowSize == 1) {
			for (int i = offset; i < offset + colSize; i++) {
				result.add(matrix[offset][i]);
			}
			return;
		}
		if (colSize == 1) {
			for (int i = offset; i < offset + rowSize; i++) {
				result.add(matrix[i][offset]);
			}
			return;
		}
		
		for (int i = 0; i < colSize - 1; i++) {
			result.add(matrix[offset][offset + i]);
		}
		for (int i = 0; i < rowSize - 1; i++) {
			result.add(matrix[offset + i][offset + colSize - 1]);
		}
		for (int i = 0; i < colSize - 1; i++) {
			result.add(matrix[offset + rowSize - 1][offset + colSize - 1 - i]);
		}
		for (int i = 0; i < rowSize - 1; i++) {
			result.add(matrix[offset + rowSize - 1 - i][offset]);
		}
		
		spiralPrint(matrix, offset + 1, rowSize - 2, colSize - 2, result);
	}
	
	//test main()
	public static void main(String[] args) {
		SpiralMatrix test = new SpiralMatrix();
		int[][] matrix = new int[][] {{2, 5, 8},
										{4, 0, -1}};
		System.out.println(test.spiralOrder(matrix));
	}
}
