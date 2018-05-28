package Leetcode;

/* Mathmatics/Encode
 * 1000 buckets, only 1 is poison. A pig will die within 15min after drink poison.
 * how many pigs needed to find out poison within in 1 hour?
 */
public class PoorPigs {
	//S1: Math.log().ceil()
	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) throws Exception {
		if (buckets <= 0) return 0;
		if (minutesToDie > minutesToTest) throw new Exception();
		
		int round = minutesToTest / minutesToDie + 1;
		double pigs = Math.log(buckets) / Math.log(round);
		return (int)Math.ceil(pigs);
	}
	
	//S2: iterative: while loop
	public int poorPigsIteration(int buckets, int minutesToDie, int minutesToTest) {
		if (buckets <= 0) return 0;
		
		int round = minutesToTest / minutesToDie + 1;
		int i = 0;
		while (Math.pow(i, round) < buckets) {
			i++;
		}
		return i;
	}
	
}
