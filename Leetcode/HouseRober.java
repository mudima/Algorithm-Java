package Leetcode;

public class HouseRober {
/*
 * DP S1: time O(n), space O(2n)
 * dp[i] -> for ith room, the max money can stole
 * dp[0] = room[0], dp[1] = max(room[0], room[1])
 * dp[i] = max(not stole i, stole i) = max(dp[i - 1], room[i] + notRoom[i - 1])
 * return dp[n - 1]
 */
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int len = nums.length;
		int[] dp = new int[len];
		int[] notRob = new int[len];
		dp[0] = nums[0];
		notRob[0] = 0;
		for (int i = 1; i < len; i++) {
			dp[i] = Math.max(dp[i - 1], nums[i] + notRob[i - 1]);
			notRob[i] = dp[i - 1];
		}
		return dp[len - 1];
	}
	
	//DP S2: reduce space to O(1)
	public int robS2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		int len = nums.length;
		int dp = nums[0];
		int notRob = 0;
		
		for (int i = 1; i < len; i++) {
			int temp = dp;
			dp = Math.max(temp, nums[i] + notRob);
			notRob = temp;
		}
		return dp;
	}
	
}
