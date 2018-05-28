package Leetcode;

import java.util.Arrays;
import java.util.Random;

/*
 * Shuffle a set of numbers without duplicates.
 * Any permutation must equally likely to be returned.
 */
public class ShuffleAnArray {
	public int[] nums;
	
	public ShuffleAnArray(int[] nums) {
		this.nums = new int[nums.length];
		for (int i = 0; i  < nums.length; i++) {
			this.nums[i] = nums[i];
		}
	}
	
	public int[] shuffle() {
		if (nums == null || nums.length <= 1) return nums;
		
		int len = nums.length;
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			Random rand = new Random();
			int k = rand.nextInt(i + 1);
			result[i] = result[k];
			result[k] = nums[i];
		}
		return result;
	}
	
	public int[] reset() {
		return nums;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6};
		ShuffleAnArray test = new ShuffleAnArray(nums);
		int[] result = test.shuffle();
		System.out.println(Arrays.toString(result));
		int[] result2 = test.reset();
		System.out.println(Arrays.toString(result2));
		
	}
}
