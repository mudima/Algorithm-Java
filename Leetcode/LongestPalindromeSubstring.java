package Leetcode;

public class LongestPalindromeSubstring {
	//S1: primitive Time: O(n^3) Space: O(1)
	public String longestPalindrome(String s) {
		if (s == null || s.length() <= 1) return s;
		
		int len = s.length();
		String result = "";
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				String str = s.substring(i, j);
				if (str.length() > result.length() && isPalindrome(str)) {
					result = str;
				}
			}
		}
		return result;
	}
	private boolean isPalindrome(String s) {
		int len = s.length();
		int i = 0, j = len - 1;
		while (i < j) {
			if (s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			} else {
				return false;
			}
		}
		return true;
	}
	
	
	//S2: search from middle to side Time O(n^2)
	String result = "";
	public String longestPalindrome(String s) {
		if (s == null || s.length() <= 1) return s;
		
		int len = s.length();
		for (int i = 0; i < len - 1; i++) {
			extendPalindrome(s, i, i);
			extendPalindrome(s, i, i + 1);
		}
		return result;
	}
	private void extendPalindrome(String s, int i, int j) {
		int len = s.length();
		while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		if ((j - i - 1) > result.length()) result = s.substring(i + 1, j);
	}
	
	
	//Test Main()
	public static void main(String[] args) {
		LongestPalindromeSubstring test = new LongestPalindromeSubstring();
		String input = "abccbac";
		System.out.println(test.longestPalindrome(input));
	}
	
}
