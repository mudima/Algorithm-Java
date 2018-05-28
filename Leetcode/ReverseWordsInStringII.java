package Leetcode;
/*
 * Reverse whole char[]
 * Reverse each word between space
 * Time: O(n)
 * Space: O(1) -> in place
 */
public class ReverseWordsInStringII {
	public void reverseWords(char[] s) {
		if (s == null || s.length <= 1) return;
		
		reverse(s, 0, s.length - 1);
		int left = 0, right = 0;
		while (right < s.length) {
			while (right < s.length && s[right] != ' ') {
				right++;
			}
			reverse(s, left, right - 1);
			left = right + 1;
			right = left;
		}
	}
	private void reverse(char[] str, int start, int end) {
		if (start == end) return;
		
		while (start < end) {
			char temp = str[start];
			str[start] = str[end];
			str[end] = temp;
			start++;
			end--;
		}
	}
}
