package Leetcode;

import java.util.Stack;

public class BasicCalulatorII {
	private boolean comparePrecedence(char c1, char c2) {
		if (c1 == '*' || c1 == '/') {
			return true;
		} else if (c1 == '(' || c2 == '*' || c2 == '/') {
			return false;
		} else {
			return true;
		}
	}
	
	private void addOptrToQueue(Stack<Integer> stackInt, String str) {
		int op2 = stackInt.pop();
		int op1 = stackInt.pop();
		if (str.equals("+")) {
			stackInt.push(op1 + op2);
		} else if (str.equals("-")) {
			stackInt.push(op1 - op2);
		} else if (str.equals("*")) {
			stackInt.push(op1 * op2);
		} else if (str.equals("/")) {
			stackInt.push(op1 / op2);
		}
	}
	
	public int calculate(String s) {
		if (s == null || s.length() == 0) return 0;
		
		Stack<Integer> stack = new Stack<>();
		Stack<Character> optr = new Stack<>();
		s = s.trim();
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				continue;
			} else if (c == '(') {
				optr.push(c);
			} else if (c == '+' || c == '-' || c == '*' || c == '/') {
				while (!optr.empty()) {
					if (comparePrecedence(optr.peek(), c)) {
						addOptrToQueue(stack, String.valueOf(optr.pop()));
					} else {
						break;
					}
				}
				optr.push(c);
			} else if (c >= '0' && c <= '9') {
				int num = 0;
				while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					num = num * 10 + (s.charAt(i) - '0');
					i++;
				}
				i--;
				stack.push(num);
			} else if (c == ')') {
				while (!optr.isEmpty() && optr.peek() != '(') {
					addOptrToQueue(stack, String.valueOf(optr.pop()));
				}
				//remove the last '('
				if (!optr.isEmpty() && optr.peek() == '(') {
					optr.pop();
				}
			}
		}
		while (!optr.isEmpty()) {
			addOptrToQueue(stack, String.valueOf(optr.pop()));
		}
		return stack.peek();
	}
}
