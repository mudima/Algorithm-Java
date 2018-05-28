package Leetcode;

import java.util.Arrays;
import java.util.Random;

public class RandomPickIndex {
	private int[] nums;
	
	public RandomPickIndex(int[] nums) {
		if (nums == null || nums.length == 0) return;
		
		this.nums = Arrays.copyOf(nums, nums.length);
	}
	
	public int pick(int target) {
		int len = nums.length, picked = -1, count = 0;
		for (int i = 0; i < len; i++) {
			if (target == nums[i]) {
				count++;
				int rand = new Random().nextInt(count);
				if (rand == 0) {
					picked = i;
				}
			}
		}
		return picked;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 3, 3};
		RandomPickIndex test = new RandomPickIndex(nums);
		
		System.out.print(test.pick(3));
	}
}
