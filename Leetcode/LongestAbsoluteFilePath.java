package Leetcode;

import java.util.Stack;

public class LongestAbsoluteFilePath {
	public int lengthLongestPath(String input) {
		if (input == null || input.length() == 0) return 0;
		
		int len = input.length(), i = 0, max = 0;
		Stack<int[]> stack = new Stack<>();
		
		while (i < len) {
			if (i < len && input.charAt(i) == '\n') i++;
			
			int level = 0;
			while (i < len && input.charAt(i) == '\t') {
				i++;
				level++;
			}
			
			int fileLen = 0;
			boolean isFile = false;
			while (i < len && input.charAt(i) != '\n') {
				if (input.charAt(i) == '.' && i + 1 < len && (input.charAt(i + 1) != '\n' || input.charAt(i + 1) != '\t')) {
					isFile = true;
				}
				fileLen++;
				i++;
			}
			
			while (!stack.isEmpty() && stack.peek()[1] >= level) {
				stack.pop();
			}
			
			int[] pushMe = new int[] {(stack.isEmpty() ? 0 : (stack.peek()[0] + 1)) + fileLen, level};
			stack.push(pushMe);
			if (isFile) {
				max = Math.max(max, pushMe[0]);
			}
		}
		return max;
	}
}
