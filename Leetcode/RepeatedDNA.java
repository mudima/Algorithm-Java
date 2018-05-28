package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// four status for A/B/C/D, using 2 bits to present 4 status
//10 chars = 2 * 10 = 20 bits --> int
//time: O(N)
//space: hashmap O(N)
public class RepeatedDNA {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> res = new ArrayList<String>();
		if (s == null || s.length() < 10) return res;
		int len = s.length();
		int key = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 9; i++) {
			key = (key << 2) | convert(s.charAt(i));
		}
		
		for (int i = 9; i < len; i++) {
			//last 20 bits maintains, front 12 bits set to 0.
			//0xFFFFF = 4 bits * 5 = 1111 1111 1111 1111 1111.
			key = (key << 2) & 0xFFFFF | convert(s.charAt(i));
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
				if (map.get(key) == 1) {
					res.add(s.substring(i - 9, i + 1));
				}
			} else {
				map.put(key, 0);
			}
		}
		return res;
	}
	
	private int convert(char c) {
		switch(c) {
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: return -1;
		}
	}
}
