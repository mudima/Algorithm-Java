package Leetcode;

import java.util.Stack;

public class SimplePath {
	public String simplifyPath(String path) {
		if (path == null || path.length() == 0) return path;
		
		Stack<StringBuilder> stack = new Stack<>();
		int len = path.length();
		
		for (int i = 0; i < len; i++) {
			char c = path.charAt(i);
			if (c == '/') {
				continue;
			} else {
				StringBuilder str = new StringBuilder();
				while (i < len && path.charAt(i) != '/') {
					str.append(path.charAt(i++));
				}
				i--;
				if (str.equals(".")) {
					//do nothing.
				} else if (str.equals("..") && !stack.isEmpty()) {
					stack.pop();
				} else {
					stack.push(str.insert(0, '/'));
				}
			}
		}
		
		if (stack.isEmpty()) return "/";
		StringBuilder result = new StringBuilder();
		while (!stack.isEmpty()) {
			result.insert(0, stack.pop());
		}
		return result.toString();	
	}
}
