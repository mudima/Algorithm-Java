package Leetcode;

public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		int len = nums.length;
		int[] result = new int[len];
		result[0] = 1;
		for (int i = 1; i < len; i++) {
			result[i] = result[i - 1] * nums[i - 1];
		}
		int right = 1;
		for (int i = len - 1; i >= 0; i--) {
			result[i] = right * result[i];
			right = right * nums[i];
		}
		return result;
	}
	//Time: O(n)
	//Space: O(1)
	
}
