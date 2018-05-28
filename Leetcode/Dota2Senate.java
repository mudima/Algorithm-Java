package Leetcode;

public class Dota2Senate {
	public String predictPartyVictory(String senate) {
		if (senate == null || senate.length() == 0) return "";
        if (senate.length() == 1) return senate.equals("R") ? "Radiant" : "Dire";
        
        int len = senate.length();
        char[] array = senate.toCharArray();
        int r = findNext(array, 'R', -1);
        int d = findNext(array, 'D', -1);
        
        int idx = 0;
        while (r != -1 && d != -1) {
            if (array[idx] == 'R') {
                array[d] = 'N';
                d = findNext(array, 'D', r);
            } else if (array[idx] == 'D'){
                array[r] = 'N';
                r = findNext(array, 'R', d);
            }
            idx = (idx + 1) % len;
        }
        if (r == -1) return "Dire";
        return "Radiant";
    }
    
    private int findNext(char[] array, char party, int curIdx) {
    	int idx = curIdx == -1 ? 0 : curIdx;
        for (int i = 0; i < array.length; i++) {
            if (array[idx] == party) return idx;
            idx = (idx + 1) % array.length;
        }
        return -1;
    }
    
    public static void main(String[] args) {
    	Dota2Senate test = new Dota2Senate();
    	
    	char[] s1 = {'R'};
    	char[] s2 = {'R', 'R'};
    	char[] s3 = {'R', 'D'};
    	char[] s4;
    	
    	//System.out.println(test.findNext(s3, 'D', -1));
    	//System.out.println(test.findNext(s3, 'R', -1));
    	System.out.println(test.predictPartyVictory("DRRDRDRDRDDRDRDR"));
    	

    }
}
