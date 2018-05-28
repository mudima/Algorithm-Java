package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class PerfectSquares {
	/*
	 * S1: BFS
	 * build a collection of square numbers 1 to n
	 * think each square number connects with others as a graph:
	 * node i -> j = i + one square number, verse vice.
	 * do BFS to this graph, record levels, until find given target, return levels as count of square numbers
	 * 
	 * time: traversal all the node O(n^n), runtime 50ms
	 * space: squareNumbers n^(1/2) = m + leastCount n + queue m^n = O(n^n)
	 */
	public int numSquaresS1(int n) {
		if (n <= 0) return 0;
		
		//squareNumbers stores all usable square numbers smaller or equal to n.
		ArrayList<Integer> squareNumbers = new ArrayList<Integer>();
		//leastCount[m] stores least number of square numbers that sum up to m. 
		int[] leastCount = new int[n + 1];
		//initiate squareNumbers and leastCount[square number]
		//edge case: if n is square number return 1;
		for (int i = 0; i * i <= n; i++) {
			if (i * i == n) return 1;
			squareNumbers.add(i * i);
			leastCount[i * i] =  1;
		}
		
		//BFS keep steps level counted
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i : squareNumbers) {
			queue.offer(i);
		}
		int count = 1;
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				int cur = queue.poll();
				for (int j : squareNumbers) {
					if (j + cur == n) { //valid result condition
						return count + 1;
					} else if (j + cur < n && leastCount[j + cur] == 0) {
						//if the number m has been visited, no need to visit again with more numbers
						queue.offer(j + cur);
						leastCount[j + cur] = j + cur;
					} else if (j + cur > n) {
						//if current sum is already larger than target, no need to continue this level
						break;
					}
				}

			}
			count++;
		}
		return 0;
	}
	
	
	/*
	 * S2: Dynamic Programming
	 * int[] dp = new int[n + 1];
	 * dp[i] stands for least number of square number sum to i
	 * dp[i] = min(dp[i - j*j]) + 1;
	 * 
	 * time: one pass 1 to n -> 1 + 2 + 3 +...+ n-1 + n = O(n^2), runtime 57ms
	 * space: dp[] O(n)
	 */
	public int numSquaresS2(int n) {
		if (n <= 0) return 0;
		
		int[] leastCount = new int[n + 1];
		//initiate dp[]
		for (int i = 1; i <= n; i++) {
			if (i * i == n) {
				return 1;
			}else if (i * i < n) {
				leastCount[i * i] = 1;
			}
			if (leastCount[i] == 0) {
				leastCount[i] = Integer.MAX_VALUE; 
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j * j <= i; j++) {
				leastCount[i] = Math.min(leastCount[i], leastCount[i - j * j] +1);
			}
		}
		return leastCount[n];
	}
	
	/*
	 * S3: Mathematical methods
	 * 4-square theorem: every natural number can be sum of 4 integers' square. n = a^2 + b^2 + c^2 + d^2
	 * 3-sqare theorem: every natural number can be sum of 3 integers' square, if not in condition n = 4^x * (8 * y + 7)
	 * 
	 * there are only for result: 1, 2, 3, 4
	 * check 1 -> check 4 -> check 2 -> return 3
	 * 
	 * time: check1->1 + check4->1 + check2->n = O(n)
	 * space: O(1)
	 */
	public int numSquaresS3(int n) {
		if (n <= 0) return 0;
		
		//check result 1
		int sqrtN = (int)Math.sqrt(n);
		if (sqrtN * sqrtN == n) return 1;
		
		//check result 4
		int m = n;
		while (m % 4 == 0) {
			m /= 4;
		}
		if (m % 8 == 7) return 4;
		
		//check result 2
		for (int i = 1; i * i < n; i++) {
			int rest = n - i * i;
			int sqrtRest = (int)Math.sqrt(rest);
			if (sqrtRest * sqrtRest == rest) return 2;
		}
		return 3;
	}
	
	
	public static void main(String[] args) {
		PerfectSquares test = new PerfectSquares();
		System.out.println(test.numSquaresS3(0));
		System.out.println(test.numSquaresS3(1));
		System.out.println(test.numSquaresS3(12));
		System.out.println(test.numSquaresS3(13));

	}
}
