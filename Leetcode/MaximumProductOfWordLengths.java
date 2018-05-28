package Leetcode;

public class MaximumProductOfWordLengths {
	public int maxProduct(String[] words) {
		if (words == null || words.length == 0) return 0;
		
		int len = words.length;
		int[] mask = new int[len];
		//time: O(N * k) k is average length of words
		//space: O (N * 26)
		for (int i = 0; i < len; i++) {
			String s = words[i];
			for (char c : s.toCharArray()) {
				mask[i] = mask[i] | (1 << (c - 'a'));
			}
		}
		int max = 0;
		//time: N-1 + N-2 + N-3 + ... + 2 + 1 -> O(N^2)
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if ((mask[i] & mask[j]) == 0) {
					max = Math.max(max, words[i].length() * words[j].length());
				}
			}
		}
		return max;
	}
}
