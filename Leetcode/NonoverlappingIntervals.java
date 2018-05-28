package Leetcode;

import java.util.Arrays;

/*
 * Interval - sort start point ascending
 * compare with the smallest end point: if overlaped count++, else update rangeEnd
 * 
 * time: sort nlogn + one pass check n = O(nlogn)
 * space: O(1)
 */

public class NonoverlappingIntervals {
	public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals == null || intervals.length <= 1) return 0;
        
        Arrays.sort(intervals, (i1, i2) -> i1.end - i2.end);
        int rangeEnd = intervals[0].end;
        int removeCount = 1;
        for (int i = 1; i < intervals.length; i++) {
            Interval cur = intervals[i];
            if (cur.start < rangeEnd) {
                removeCount++;
                rangeEnd = Math.min(rangeEnd, cur.end);
            } else {
            	rangeEnd = cur.end;
            }
        }
        return removeCount;
    }
}

class Interval {
	int start;
	int end;
	
	Interval() { 
		start = 0; 
		end = 0; 
	}
	Interval(int s, int e) { 
		start = s; 
		end = e; 
	}
}