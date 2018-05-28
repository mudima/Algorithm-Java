package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInString {
	public List<Integer> findAnagrams(String s, String p) {
		if (s == null || s.length() == 0 || p == null || p.length() ==0) return null;
		int lenS = s.length(), lenP = p.length();
		if (lenP > lenS) return null;
		
		int[] templet = new int[26];
		int[] window = new int[26];
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < lenP; i++) {
			char charP = p.charAt(i);
			char charS = s.charAt(i);
			templet[charP - 'a']++;
			window[charS - 'a']++;
		}
		if (checkAnagrams(templet, window)) result.add(0);
		
		for (int i = lenP; i < lenS; i++) {
			char delete = s.charAt(i - lenP);
			char add = s.charAt(i);
			window[delete - 'a']--;
			window[add - 'a']++;
			if (checkAnagrams(templet, window)) result.add(i - lenP + 1);
		}
		return result;
	}
	private boolean checkAnagrams(int[] templet, int[] window) {
		for (int i = 0; i < 26; i++) {
			if (templet[i] != window[i]) return false;
		}
		return true;
	}
	
	//Test Main()
	public static void main(String[] args) {
		FindAllAnagramsInString test = new FindAllAnagramsInString();
		String s = "abcdabc";
		String p = "abc";
		List<Integer> result = test.findAnagrams(s, p);
		for (Integer i : result) {
			System.out.println(i);
		}
	}
}
