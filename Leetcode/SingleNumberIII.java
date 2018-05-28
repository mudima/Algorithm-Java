package Leetcode;

public class SingleNumberIII {
	public int[] singleNumberIII(int[] nums) {
		int[] result = new int[2];
		if (nums == null || nums.length <= 1) return result;
		if (nums.length == 2) return nums;
		
		int AxorB = 0, len = nums.length;
		//get A xor B
		for (int i = 0; i < len; i++) {
			AxorB ^= nums[i];
		}
		//get the bit A and B is different
		int k = 0;
		while (((AxorB >> k) & 1) == 0) {
			k++;
		}
		int bitFlag = 1 << k;
		//separate all numbers by different bit into 2 group, xor within group
		int A = 0, B = 0;
		for (int i = 0; i < len; i++) {
			if ((nums[i] & bitFlag) == 0) {
				A ^= nums[i];
			} else B ^= nums[i];
		}
		result[0] = A;
		result[1] = B;
		return result;
	}
}
