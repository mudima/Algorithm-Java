package Leetcode;

public class ReverseString {
	/*
	 * S1: char[], for loop from back to front
	 * time: O(n)
	 * space: O(n)
	 */
	public String reverseStringArray(String s) {
		if (s == null || s.length() == 0) return s;
		
		int len = s.length();
		char[] array = new char[len];
		for (int i = len - 1; i >= 0; i--) {
			array[len - 1 - i] = s.charAt(i);
		}
		return array.toString();
	}
	
	/*
	 * S2: StringBuilder API
	 */
	public String reverseStringBuilder(String s) {
		if (s == null || s.length() == 0) return s;
		
		return new StringBuilder(s).reverse().toString();
	}
	
	/*
	 * S3: char[], two pointers from side to middle
	 * time: n/2 -> O(n)
	 * space: O(n)
	 */
	public String reverseStringHalf(String s) {
		if (s == null || s.length() == 0) return s;
		
		int len = s.length(), left = 0, right = len - 1;
		char[] array = new char[len];
		while (left < right) {
			array[left] = s.charAt(right);
			array[right] = s.charAt(left);
			left++;
			right--;
		}
		return array.toString();
	}
	
	/*
	 *S4: recursion, divide and conquer
	 *time: 1+2+4+8+...+n/2+n= n -> O(n)
	 *space: nlogn -> O(nlogn)
	 */
	public String reverseStringRecursion(String s) {
		if (s == null || s.length() <= 1) return s;
		
		int len = s.length();
		String left = s.substring(0, len/ 2);
		String right = s.substring(len/2, len);
		return reverseStringRecursion(right) + reverseStringRecursion(left);
	}
	
}
