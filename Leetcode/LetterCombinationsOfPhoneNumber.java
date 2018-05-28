package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {
	char[][] map = new char[][] {
		{}, 
		{}, 
		{'a', 'b', 'c'}, 
		{'d', 'e', 'f'}, 
		{'g', 'h', 'i'}, 
		{'j', 'k', 'l'}, 
		{'m', 'n', 'o'}, 
		{'p', 'q', 'r', 's'},
		{'t', 'u', 'v'},
		{'w', 'x', 'y', 'z'}
		};
	public List<String> letterCombinations(String digits) {
			List<String> result = new ArrayList<String>();
			if (digits == null || digits.length() == 0) return result;
			
			char[] array = digits.toCharArray();
			StringBuilder sol = new StringBuilder();
			dfs(array, map, result, sol, 0);
			return result;
		}
	private void dfs(char[] array, char[][] map, List<String> result, StringBuilder sol, int index) {
			int len = array.length;
			if (index >= len) {
				result.add(sol.toString());
				return;
			}
			int number = Integer.valueOf(Character.toString(array[index]));
			char[] numChars = map[number];
			if (numChars.length == 0) {
				dfs(array, map, result, sol, index + 1);
			} else {
				for (char c : numChars) {
					sol.append(c);
					dfs(array, map, result, sol, index + 1);
					sol.deleteCharAt(sol.length() - 1);
				}
			}
	}
	
	//Test Main()
	public static void main(String[] args) {
		LetterCombinationsOfPhoneNumber test = new LetterCombinationsOfPhoneNumber();
		String digits = "123";
		List<String> result = test.letterCombinations(digits);
		for (String s : result) {
			System.out.println(s);
		}
	}
}
