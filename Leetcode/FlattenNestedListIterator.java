package Leetcode;

import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
	private Stack<NestedInteger> stack;
	
	public NestedIterator(List<NestedInteger> nestedList) {
		if (nestedList == null) return;
		this.stack = new Stack<NestedInteger>();
		int len = nestedList.size();
		for (int i = len - 1; i >= 0; i--) {
			NestedInteger ni = nestedList.get(i);
			pushStack(stack, ni);
		}
	}
	
	private boolean pushStack(Stack<NestedInteger> stack, NestedInteger ni) {
		if (ni == null) return false;
		if (ni.isInteger()) {
			stack.push(ni);
			return true;
		} else {
			List<NestedInteger> list = ni.getList();
			int len = list.size();
			for (int i = len - 1; i >= 0; i--) {
				NestedInteger node = list.get(i);
				if (pushStack(stack, node)) {
					for (int j = i - 1; j >= 0; j++) {
						NestedInteger one = list.get(j);
						if (one != null && (one.isInteger() || one.getList().size() != 0)) {
							stack.push(one);
						}
					}
					return true;
				}
			}
			return false;
		}
	}
	
	@Override
	public Integer next() {
		while (true) {
			NestedInteger top = stack.pop();
			if (top.isInteger()) {
				return top.getInteger();
			} else {
				List<NestedInteger> list = top.getList();
				int len = list.size();
				for (int i = len; i >= 0; i--) {
					NestedInteger ni = list.get(i);
					if (ni != null && (ni.isInteger() || ni.getInteger().size() != 0)) {
						stack.push(ni);
					}
				}
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}
}
