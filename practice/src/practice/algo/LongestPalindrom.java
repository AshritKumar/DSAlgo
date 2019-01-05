package practice.algo;

public class LongestPalindrom {
	//forgeeksskeegfor
	// bad approach O(n^3) time complexity
	public static String getLongestPalindrom(String s) {
		int maxLen = 1;
		String longestPlai = "";
		for(int i=0; i<s.length(); i++) {
			for(int j=0; j<=i; j++) {
				String subs = s.substring(j, i+1);
				int st = 0, en = subs.length()-1;
				boolean isPali = true;
				while(st < en) {
					if(subs.charAt(st) != subs.charAt(en)) {
						isPali = false;
						break;
					}
					else {
						isPali = true;
					}
					st++;en--;
				}
				if(isPali) {
					System.out.println(subs); //-- to print all other palindroms
					if(subs.length() > maxLen) {
						maxLen = subs.length();
						longestPlai = subs;
					}
				}
			}
		}
		return longestPlai;
	}
	
	// O(n2) time complexity, O(n2) space complexity
	public static int longestPalindromDynm1(String s) {
		int l = s.length();
		boolean temp[][] = new boolean[l][l];
		// all string of len 1 are palindromes, so put true
		int start =0, maxLength = 0;
		
		for(int i = 0; i< l-1; i++)
			temp[i][i] = true;
		// check all two letter words for palindromes
		for(int i=1; i<l ; i++) {
			if(s.charAt(i-1) == s.charAt(i)) {
				start = i-1;
				maxLength = 2;
				temp[i-1][i] = true;
			}
		}
		// check for i th length palindromes
		for(int i = 3; i <= l; i++) {
			// from 0 to len-i, check if i characters from palindromes
			// j is starting point, k is ending point
			for(int j=0; j<=l-i; j++) {
				int k = i+j-1;
				// check if temp[j+1][k-1] is true and char at j and k are same, then its a palindrome
				if(s.charAt(j) == s.charAt(k) && temp[j+1][k-1]) {
					temp[j][k] = true;
					if(i > maxLength) {
						start = j;
						maxLength = i;
					}
					// to print all
					//System.out.println(s.substring(j, k+1));
				}
				
			}
		}
		if(maxLength > 1) {
			System.out.println(s.substring(start, start+maxLength));
		}
		return maxLength;
		
	}
	
	public static void main(String[] args) {//forgeeksskeegfor
		//System.out.println(getLongestPalindrom("forgeeksskeegfor"));
		int l = longestPalindromDynm1("iuguigiugguyyuhiunyugyftdtuvyufdxdxufuydtrdtrtxxgjhghgfdtrxrxcufiguifthgduasiduisadujhhdshfsdhofhisdiodhjsdhiuhahiicuiun");
		System.out.println(l);
	}

}
