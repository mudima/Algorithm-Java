package Leetcode;

public class MajorityElement {
	public int majorityElement(int[] nums) throws Exception {
		if (nums == null || nums.length == 0) throw new Exception("InvalidInputException");
		
		int len = nums.length;
		int[] sum = new int[32];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 32; j++) {
				if (((nums[i] >> j) & 1) == 1) sum[j]++;
			}
		}
		
		int majorityElement = 0;
		for (int k = 0; k < 32; k++) {
			if (sum[k] > len / 2) {
				majorityElement |= (1 << k);
			}
		}
		return majorityElement;
	}
}
