package Leetcode;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) return 0;
		
		HashMap<Character, Integer> map = new HashMap<>();
		int start = 0, end = 0;
		int max = 0, len = s.length();
		for (end = 0; end < len; end++) {
			char c = s.charAt(end);
			if (map.containsKey(c)) {
				//abba start only move forward
				start = Math.max(start, map.get(c) + 1);
			}
			map.put(c,end);
			max = Math.max(max,end - start + 1);
		}
		return max;
	}
}
