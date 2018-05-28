package Leetcode;

import java.util.Stack;

public class DecodeString {
	public String decodeString(String s) {
		if (s == null || s.length() == 0) return s;
		
		Stack<StringBuilder> strStack = new Stack<>();
		Stack<Integer> countStack = new Stack<>();
		strStack.push(new StringBuilder());
		
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c <= '9' && c >= '0') {
				int count = 0;
				while (i < len && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
					count = count * 10 + s.charAt(i++) - '0';
				}
				countStack.push(count);
				i--;
			} else if (c == '[') {
				strStack.push(new StringBuilder());
			} else if (c == ']') {
				StringBuilder slide = strStack.pop();
				StringBuilder cur = new StringBuilder();
				int count = countStack.pop();
				while (count > 0) {
					cur.append(slide);
				}
				strStack.peek().append(cur);
			} else {
				strStack.peek().append(c);
			}
		}
		return strStack.pop().toString();
	}
}
