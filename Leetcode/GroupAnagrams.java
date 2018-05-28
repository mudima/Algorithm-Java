package Leetcode;

import java.util.*;

public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new LinkedList<>();
		if (strs == null || strs.length == 0) return result;
		
		HashMap<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			String cur = strs[i];
			char[] curArray = cur.toCharArray();
			Arrays.sort(curArray);
			String sortedCur = String.valueOf(curArray);
			if (map.containsKey(sortedCur)) {
				List<String> sol = map.get(sortedCur);
				sol.add(cur);
			} else {
				List<String> newSol = new LinkedList<>();
				newSol.add(cur);
				result.add(newSol);
				map.put(sortedCur, newSol);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		GroupAnagrams test = new GroupAnagrams();
		String[] strs = new String[] {"abc", "bca", "ffe"};
		List<List<String>> result = test.groupAnagrams(strs);
		for (List<String> s : result) {
			for (String str : s) {
				System.out.print(str);
			}
			System.out.println("");
		}
	}
}
