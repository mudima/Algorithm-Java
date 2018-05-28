package Leetcode;

/*
 * remove 1 to 3, I start first.
 * if I want to win, I need to take last 3, then the other player must take last 4th stone.
 * if the other player need to take last 4th one, I need to take last 5th, 6th, 7th one...
 * if n % 4 == (1, 2, 3) I win, if n % 4 == 0 I lose. 
 */
public class NimGame {
	public boolean canWinNim(int n) throws Exception {
		if (n <= 0) throw new Exception();
		
		return n % 4 == 0 ? false : true;
	}
}
