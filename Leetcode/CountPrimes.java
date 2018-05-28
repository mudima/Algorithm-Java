package Leetcode;

import java.util.*;

public class CountPrimes {
	//S1: for (2 : n) check every number isPrime
	//Time: O(n^2)
	public int countPrimes(int n) {
		if (n <= 1) return 0;
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (isPrime(i)) count++;
		}
		return count;
	}
	private boolean isPrime(int n) {
		for (int i = 2; i <= n; i++) {
			if (i != n && n % i == 0) return false;
		}
		return true;
	}
	
	//S2: only check(%) the prime number smaller than n
	//Time: O(n)
	//Space: O(k)
	public int countPrimes1(int n) {
		if (n <= 1) return 0;
		
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			int size = primes.size();
			int j;
			for (j = 0; j < size; j++) {
				if (i % primes.get(j) == 0) break;
			}
			if (j == size) primes.add(i);
		}
		return primes.size();
	}
	
	//S3: dp思想，mark all multiple smaller than n
	//Time: O()
	public int countPrimes2(int n) {
		if (n <= 1) return 0;
		
		boolean[] notPrime = new boolean[n];
		for (int i = 2; i * i < n; i++) {
			if (!notPrime[i]) {
				for (int j = i; i * j < n; j++) {
					notPrime[i * j] = true;
				}
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (!notPrime[i]) count++;
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		CountPrimes test = new CountPrimes();
		System.out.println(test.countPrimes2(1));
		System.out.println(test.countPrimes2(2));
		System.out.println(test.countPrimes2(4));
		System.out.println(test.countPrimes2(10));
	}
}
