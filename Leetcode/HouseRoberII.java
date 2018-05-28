package Leetcode;

public class HouseRoberII {
	/*
	 * DP same with HouseRober, but nums[0] and nums[len - 1] is neighbor.
	 * compare rob[0]-[len - 2] and rob[1]-[len - 1]
	 * time:
	 * space:
	 */
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		//rob from nums[0] to nums[len - 2]
		int dpF = nums[0];
		int notRobF = 0;
		for (int i = 1; i < nums.length - 1; i++) {
			int temp = dpF;
			dpF = Math.max(temp, nums[i] + notRobF);
			notRobF = temp;
		}
		//rob from nums[1] to nums[len - 1]
				int dpL = nums[1];
				int notRobL = 0;
				for (int i = 2; i < nums.length; i++) {
					int temp = dpL;
					dpL = Math.max(temp, nums[i] + notRobL);
					notRobL = temp;
				}
		
		return Math.max(dpF, dpL);
	}
}
