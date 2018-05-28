package Leetcode;

public class SingleNumberII {
	//time: N*32 + 32 --> O(N)
	//space: O(N)
	public int singleNumber(int[] nums) throws Exception {
		if (nums == null || nums.length == 0) throw new Exception();
		
		int[] sum = new int[32];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < 32; j++) {
				if (((nums[i] >> j) & 1) == 1) sum[j]++;
			}
		}
		
		int result = 0;
		for (int k = 0; k < 32; k++) {
			if (sum[k] % 3 != 0) result |= (1 << k);
		}
		
		return result;
	}
}
