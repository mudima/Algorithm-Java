package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
	//S1: sort and return Nth index, Time:O(nlogn) Space:O(1)
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length < k) return -1;
		
		Arrays.sort(nums);
		return nums[k - 1];
	}
	
	//S2: with k size minHeap, Time:O(nlogk) Space:O(k)
	public int findKthLargest(int[] nums, int k) {
		 if (nums == null || nums.length < k) return -1;
			
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		for (int i = 0; i < nums.length; i++) {
			minHeap.offer(nums[i]);
			if (i >= k) minHeap.poll();
		}
		return minHeap.poll();
	} 
	
	//S3: quick selection
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length < k) return -1;
		
		
	}
}
