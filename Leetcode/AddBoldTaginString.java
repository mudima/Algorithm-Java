package Leetcode;
/*
 * String - substring overlap interval
 * for each char in string as start, check with each words in dict to find "longest target"
 * if found in dict, mark substring as '1', else mark as '0'
 * check binary mark array, add tag to include all '1' substring
 * 
 *  time: n = s.length, m = dict.length, O(n*m)
 *  space: boolean array marks[] = n, stringbuilder = n, O(n)
 */
public class AddBoldTaginString {
	public String addBoldTag(String s, String[] dict) {
        if (s == null || s.length() == 0 || dict == null || dict.length == 0) return s;
        
        //find start/end index for each tag pair
        boolean[] marks = new boolean[s.length()];
        int end = -1;
        for (int i = 0; i < s.length(); i++) {
        	int maxLen = 0;
        	for (String word : dict) {
        		int len = word.length();
        		if (i + len <= s.length() && s.substring(i, i + len).equals(word) && len > maxLen) {
        			end = Math.max(end, i + len);
        			maxLen = len;
        		}
        	}
        	if (end > i) {
        		marks[i] = true;
        	}
        }
        
        //build new string with tags
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < marks.length; i++) {
        	if (marks[i]) {
        		if (i == 0 || marks[i - 1] == false) {
        			res.append("<b>");
        		}
        		res.append(s.charAt(i));
        		if (i == marks.length -1 || marks[i + 1] == false) {
        			res.append("</b>");
        		}
        		
        	} else {
        		res.append(s.charAt(i));
        	}
        }
        return new String(res);
    }
	
	public static void main(String[] args) {
		AddBoldTaginString test = new AddBoldTaginString();
		String s1 = "abcxyz123";
		String[] dict1 = {"abc", "123"};
		String s2 = "aaabbcc";
		String[] dict2 = {"aaa", "aab", "bc"};
		
		System.out.println(test.addBoldTag(s1, dict1));
		System.out.println(test.addBoldTag(s2, dict2));
	}
}
