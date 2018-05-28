package Leetcode;

import java.util.Stack;

public class BasicCalculator {
	/*
	 * time: O(N)
	 * space: 2 stacks O(N)
	 */
	public int calculate(String s) {
		if (s == null || s.length() == 0) return 0;
		
		int len = s.length();
		Stack<Integer> numb = new Stack<>();
		Stack<Integer> sign = new Stack<>();
		numb.push(0);
		sign.push(1);
		for (int i = 0; i <= len; i++) {
			char c = i == len ? '+' : s.charAt(i);
			if (c >= '0' && c <= '9') {
				int number = 0;
				while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					number = number * 10 + s.charAt(i++) - '0';
				}
				numb.push(number);
				i--;
			} else if (c == '(') {
				numb.push(0);
				sign.push(1);
			} else if (c == ')' || c == '+' || c == '-') {
				int numA = numb.pop(), numB = numb.pop();
				int sig = sign.pop();
				numb.push(numB + numA * sig);
				if (c == '+') sign.push(1);
				if (c == '-') sign.push(-1);
			}
		}
		return numb.pop();
	}
}
