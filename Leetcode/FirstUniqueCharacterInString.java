package Leetcode;

public class FirstUniqueCharacterInString {
	public int firstUniqChar(String s) {
		if (s == null || s.length() == 0) return -1;
		char[] array = s.toCharArray();
		int[] count = new int[26];
		for (char c : array) {
			count[c - 'a']++;
		}
		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (count[c - 'a'] == 1) return i;
		}
		return -1;
	}
}
