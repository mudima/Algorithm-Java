package Leetcode;

public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
		if (n <= 0) return null;
		int[][] result = new int[n][n];
		spiralPrint(0, n, 1, result);
		return result;
	}
	
	private void spiralPrint(int offset, int size, int beginNum, int[][] result) {
		if (size == 0) return;
		if (size == 1) {
			result[offset][offset] = beginNum;
			return;
		}

		for (int i = 0; i < size - 1; i++) {
			result[offset][offset + i] = beginNum;
			beginNum++;
		}
		for (int i = 0; i < size - 1; i++) {
			result[offset + i][offset + size - 1] = beginNum;
			beginNum++;
		}
		for (int i = 0; i < size - 1; i++) {
			result[offset + size - 1][offset + size -1 - i] = beginNum;
			beginNum++;
		}
		for (int i = 0; i < size - 1; i++) {
			result[offset + size - 1 - i][offset] = beginNum;
			beginNum++;
		}
		
		spiralPrint(offset + 1, size - 2, beginNum, result);
	}
}
