package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.imageio.ImageTypeSpecifier;

/*
 * for 0 - n char to remove:
 * check it is valid string, 
 * if valid add into result and just finish current search, 
 * else continue to search remove more chars
 * 
 * private boolean isValid()
 */
public class RemoveInvalidParatheses {
	/*
	 * S1: BFS to remove chars
	 * time: (n * n-1 * n-2 *...* 2 * 1)^2 = O(n^2n)
	 * space: queue n^n
	 */
	public List<String> removeInvalidParentheses(String s) {
		List<String> result = new ArrayList<>();
		if (s == null || s.length() == 0) return result;
		
		//BFS try to remove i chars from s
		Queue<String> queue = new LinkedList<>();
		queue.offer(s);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (isValid(cur)) {
					result.add(cur);
					continue;
				} else {
					for (int j = 0; j < cur.length(); j++) {
						if (cur.charAt(j) == '(' || cur.charAt(j) == ')') {
							String s1 = cur.substring(0, j);
							String s2 = j + 1 < cur.length() ? cur.substring(j + 1) : "";
							queue.offer(s1 + s2);
						}
					}
				}
			}
			if (!queue.isEmpty()) break;
		}
		return result;
	}
	
	private boolean isValid(String s) {
		if (s == null || s.length() == 0) return true;
		
		Stack<Character> stack = new Stack<>();
		char[] array = s.toCharArray();
		for (char c : array) {
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty()) return false;
				else stack.pop();
			}
		}
		return stack.isEmpty();
	}
	public static void main(String[] args) {
		RemoveInvalidParatheses test = new RemoveInvalidParatheses();
		String s1 = "";
		String s2 = ")(";
		String s3 = "()()()";
		String s4 = "()())()";
		String s5 = "(a)())()";
		
		List<String> res = test.removeInvalidParentheses(s1);
		for (String string : res) {
			if (string == "") {
				System.out.println("''" + ", ");
			} else {
				System.out.println(string + ", ");
		
			}
		}
	}
}
