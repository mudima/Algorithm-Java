package Leetcode;

import java.util.*;

/*
 * key idea is that  to generate gray code list for n, add 1<<(n-1) with the reverse order of the list for gray code n-1.
	0: {0}
	1: {0} + {1}                               1<<0 = 1
	2: {0,1}  + {11, 10}                       1<< 1 = 10
	3: {0, 1, 11, 10} + {110, 111, 101, 100}   1 << 2 = 100
 */
public class GrayCode {
	public List<Integer> grayCode(int n) {
		List<Integer> result = new ArrayList<>();
		if (n < 0) return result;
		result.add(0);
		for (int i = 0; i < n; i++) {
			int size = result.size();
			for (int j = size - 1; j >= 0; j--) {
				result.add(result.get(j) | 1 << i);
			}
		}
		return result;
	}
}
