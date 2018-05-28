package Leetcode;

public class BitwiseANDofNumbersRange {
	/*
	 * find longest prefix of m and n, put 0s after it.
	 * time: 32 bits, shift right less than 32 times. O(1)
	 * space: O(1)
	 */
	public int rangeBitwiseAnd(int m, int n) {
		if (m == n) return m;
		
		int i = 0;
		while (m != n) {
			m = m >> 1;
			n = n >> 1;
			i++;
		}
		return m << i;
	}
}
