package Leetcode;

import java.util.HashSet;

/*
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 */
public class SingleNumber {
	/*
	 * S1: Hash Set
	 * time: O(n)
	 * space: O(n)
	 */
	public int singleNumber(int[] nums) throws Exception {
		if (nums == null || nums.length == 0) throw new Exception();
		
		HashSet<Integer> set = new HashSet<>();
		for (int e : nums) {
			if (set.contains(e)) set.remove(e);
			else set.add(e);
		}
		return set.iterator().next();
	}
	
	/*
	 * bit operation: XOR
	 * time: O(n)
	 * space: O(1)
	 */
	public int singleNumberXOR(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum ^= i;
		}
		return sum;
	}
}
