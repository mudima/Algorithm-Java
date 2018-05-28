package Leetcode;

/*
 * Array - one pass check
 * for every idx, check idx - 1, idx, dix + 1, attention for edge cases
 * 
 * time: one pass O(m)
 * space: O(1)
 */
public class CanPlaceFlower {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0 || flowerbed.length < n) return false;
        
        int idx = 0, len = flowerbed.length;
        while (idx < len && n > 0) {
            if (flowerbed[idx] == 0 && (idx == 0 || flowerbed[idx - 1] == 0) && (idx == len - 1 || flowerbed[idx + 1] == 0)) {
                //modified the original array. if not, idx += 2 every time
            	flowerbed[idx] = 1;
                n--;
            }
            idx++;
        }
        return n == 0;
    }
}
