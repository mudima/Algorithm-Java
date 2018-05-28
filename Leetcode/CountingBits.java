package Leetcode;
/*
 * Solution: dp from 0 to upper limit, find the law
 * time: O(N)
 * space: O(N)
 */
public class CountingBits {
	public int[] countBits(int num) {
		int[] dp = new int[num + 1];
		dp[0] = 0;
		if (num == 0) return dp;
		
		for (int i = 1; i <= num; i++) {
			if ((i & 1) == 0) {
				dp[i] =  dp[i / 2];
			} else {
				dp[i] = dp[i / 2] + 1;
			}
		}
		return dp;
	}
}
