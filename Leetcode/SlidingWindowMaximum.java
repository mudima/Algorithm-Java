package Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer a, Integer b) {
		if (Integer.valueOf(a) == Integer.valueOf(b)) return 0;
		return Integer.valueOf(a) > Integer.valueOf(b) ? -1 : 1;
	} 
}

public class SlidingWindowMaximum {
	
	//S1: heap Time: O(nlogn) Space: O(k)
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k > nums.length) return null;
		
		int len = nums.length;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new MyComparator());
		int[] result = new int[len - k + 1];
		for (int i = 0; i < k; i++) {
			maxHeap.offer(nums[i]);
		}
		int j = 0;
		result[j++] = maxHeap.peek();
		for (int i = k; i < len; i++) {
			maxHeap.remove(nums[i - k]);
			maxHeap.offer(nums[i]);
			result[j++] = maxHeap.peek();
		}
		return result;
	}
	

	//S2: doubly queue
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k > nums.length) return null;
		
		int len = nums.length;
		int[] result = new int[len - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i < len; i++) {	
			if (!deque.isEmpty() && deque.peekFirst() == i - k) deque.poll();
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
					deque.pollLast();
			}
			deque.offerLast(i);
			if (i >= k - 1) result[i + 1 - k] = nums[deque.peekFirst()];
		}
		return result;
	}
	
	
	//Test Main()
	public static void main(String[] args) {
		SlidingWindowMaximum test = new SlidingWindowMaximum();
		int[] nums = new int[] {1, 2, 3, 4, 1, 0};
		int[] result = test.maxSlidingWindow(nums, 3);
		System.out.println(Arrays.toString(result));
		
	}
}
