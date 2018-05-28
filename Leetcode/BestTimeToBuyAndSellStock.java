package Leetcode;

public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1) return 0;
		
		int minToBuy = Integer.MAX_VALUE, maxToSell = Integer.MIN_VALUE, maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minToBuy) {
				minToBuy = prices[i];
				maxToSell = prices[i];
			} else {
				maxToSell = Math.max(maxToSell, prices[i]);
			}
			maxProfit = Math.max(maxProfit, maxToSell - minToBuy);
		}
		return maxProfit;
	}
	
	//Test Main()
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock test = new BestTimeToBuyAndSellStock();
		//int[] prices = new int[] {7, 1, 5, 3, 6, 4};
		int[] prices = new int[] {7, 6, 5, 3, 1};
		System.out.println(test.maxProfit(prices));
	}
}
