package Leetcode;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Interval - sort start point
 * compare with current smallest range end, if overlap can be shoot, else add one shot
 * 
 * time: one pass to check array n + sort nlogn = O(nlogn)
 * space: O(1)
 */
public class MinimumNumberOfArrowsBurstBalloons {
	
	public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0 || points[0] == null || points[0].length == 0) return 0;
        
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]) return p1[1] - p2[1];
                else return p1[0] - p2[0];
            }
        });
        
        int rangeEnd = points[0][1];
        int arrowCount = 1;
        for (int i = 1; i < points.length; i++) {
            int[] curBal = points[i];
            if (curBal[0] <= rangeEnd) {
                rangeEnd = Math.min(rangeEnd, curBal[1]);
            } else {
                arrowCount += 1;
                rangeEnd = curBal[1];
            }
        }
        return arrowCount;
    }
}
