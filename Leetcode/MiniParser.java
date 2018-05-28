package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class NestedInteger {
	//fields
	private List<NestedInteger> list;
	private Integer integer;
	//constructor
	public NestedInteger() {
		this.list = new ArrayList<>();
	}
	public NestedInteger(Integer value) {
		this.integer = value;
	}
	public NestedInteger(List<NestedInteger> list) {
		this.list = list;
	}
	//methods
	public boolean isInteger() {
		return integer != null;
	}
	
	public Integer getInteger() {
		return integer;
	}
	
	public void setInteger(int value) {
		this.integer = value;
	}
	
	public void add(NestedInteger ni) {
		if (this.list != null) {
			this.list.add(ni);
		} else {
			this.list = new ArrayList<>();
			this.list.add(ni);
		}
	}
	
	public List<NestedInteger> getList() {
		return list;
	}
	
	public String printNi() {
		if (this.isInteger()) {
			return String.valueOf(this.integer);
		} else {
			String result = "[";
			List<NestedInteger> list = this.getList();
			for (NestedInteger ni : list) {
				result += ni.printNi() + ",";
			}
			result += "]";
			return result;
		}
	}
}

public class MiniParser {
	public NestedInteger deserialize(String s) {
		if (s == null || s.length() == 0) return null;
		
		int len = s.length();
		Stack<NestedInteger> stack = new Stack<>();
		
		Integer curVal = null;
		int sign = 1;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				curVal = curVal == null ? (c - '0') : (curVal * 10 + (c - '0'));
			} else if (c == '-') {
				sign *= -1;
			} else if (c == '[') {
				stack.push(new NestedInteger());
			} else if (c == ']' || c == ',') {
				if (i >= 1 && s.charAt(i - 1) == '[') continue;
				else if (curVal != null) {
					stack.push(new NestedInteger(sign * curVal));
					curVal = null;
					sign = 1;
				}
				NestedInteger top = stack.pop();
				stack.peek().add(top);
			}
		}
		return stack.pop();
	}
}
