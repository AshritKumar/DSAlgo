package practice.algo;

import java.util.Calendar;
import java.util.TimeZone;

public class PatternSearch {
	static int maxLen=0, maxPrefIndx = 0;
	// LPS
	
	
	
	
	public static int[] getPrefixArryFromPattern(String patt) {
		int prefix[] = new int[patt.length()];
		// j points at start initially, i iterates through the length of the string
		//j only moves if there is a match between jth character and ith character
		int j=0;
		for(int i=1; i<patt.length() && j >=0;) {
			// Compare characters at j and i
			if(patt.charAt(j) == patt.charAt(i)) {
				// if its a match update prefix array and increment i , j 
				prefix[i] = j+1;
				// mark the max value in prefix arry to get max length of prefix which is also a suffix
				if(prefix[i] > maxLen) {
					maxLen = prefix[i];
					maxPrefIndx = i;
				}
				j++;
				i++;
			} else {
				// if j reaches the start of string update prefix[i] = 0 and increment i
				if(j == 0) {
					prefix[i] = 0;
					i++;
				} else
					j = prefix[j-1];
			}
		}
		return prefix;
	}
	
	public static String getLongestPrefixAndSufix(String patt) {
		int prefArr[] = getPrefixArryFromPattern(patt);
		printArray(prefArr);
		String maxSuffPref = patt.substring(0, maxLen);
		System.out.println("Max length of String is "+maxLen+" and String is "+maxSuffPref);
		System.out.println("from (0 to "+(maxLen-1)+") and ("+(maxPrefIndx - maxLen + 1+" to "+maxPrefIndx+")"));
		return maxSuffPref;
	}
	
	public static boolean findMatchKMP(String s , String patt) {
		// first find the prefix array for pattern
		int prefArr[] = getPrefixArryFromPattern(patt);
		printArray(prefArr);
		// iterate through the string
		int j=0, i=0;
		boolean match = false;
		for(; i<s.length() && j < patt.length() ;) {
			if(s.charAt(i) == patt.charAt(j)) {
				j++;
				i++;
			} else {
				if(j>0)
					j = prefArr[j-1];
				else
					i++;
			}
			//System.out.println(j);
		}
		if(j == patt.length())
			match = true;
		System.out.println(j+i);
		return match;
		
	}
	public static void main(String[] args ) {
		//acacabacacabacacac
		String s = "xaxaxaykjkjuikjkxaxxaxykkkjkljhjhjxaxaxayxaxkjkjxaxaxaykjkjujkhkjhjkhkjhjhkjh";
		System.out.println(s);
		getLongestPrefixAndSufix(s);
		boolean m = findMatchKMP("xaxaxaykjkjuikjkxaxxaxykkkjkljhjhjxaxaxayxaxkjkjxaxaxaykjkjujkhkjhjkhkjhjhkjh", "jkj");
		System.out.println(m);
		
		Calendar c = Calendar.getInstance();
				
		System.out.println(c.getClass().getName());
	}
	
	public static void printArray(int arr[]) {
		for(int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}
}

class Test{
	public static void main(String[] args) {
		System.out.println("Tejhjh");
	}
}
