package practice.algo;

public class FormPalindrome {
	
	public static String formPalindrome(String s) {
		s = s.toLowerCase();
		int[] freq = new int[26];
		countFrequency(s,freq);
		if(!checkForPalindrome(freq, s)) {
			return "NO PLAINDROME CAN BE FROMED";
		}
		String oddPart = "";
		for(int i=0; i<freq.length; i++) {
			if(freq[i] % 2 != 0) {
				while(freq[i] > 0) {
					freq[i]--;
					oddPart += (char)(i+'a');
				}
			}
		}
		String front = "";
		String rear = "";
		int p = 0, len = freq.length;
		// p indicates the position of the letter which palindrome is starting, if p is 0 palindrome starting with a is formed
		// if its 1 palindrome with b is formed
		for(int n=0; n< len; n++) {
			int i=(n+p)%len;
			if(freq[i] != 0) {
				char c = (char)(i+'a');
				for(int k = freq[i]/2; k > 0; k--) {
					front = front+c;
					rear = c+rear;
				}
			}
		}
		return front+oddPart+rear;
	}
	
	private static boolean checkForPalindrome(int[] freq, String s) {
		int oc = 0;
		for(int i=0; i< freq.length; i++) {
			if(freq[i] % 2 != 0)
				oc++;
		}
		if(s.length() % 2 == 0 && oc > 0) 
			return false;
		else
			if(oc > 1)
				return false;
		return true;
	}

	private static void countFrequency(String s, int[] freq) {
		for(int i=0; i< s.length(); i++) {
			freq[s.charAt(i)-'a']++;
		}
		
	}

	public static void main(String[] args) {
		String s = formPalindrome("malayalam");
		System.out.println(s);
		// extra wrk
		int i=0,j=1,len=25;
		int k = (i+j)%25;
		//Integer.parseInt("16", 16);
		//System.out.println(Integer.toBinaryString(-13));
		//System.out.println(Integer.toBinaryString(13));
		System.out.println(6+(6 & -6));
		/*System.out.println(18 | 1);
		System.out.println(0b11111111111111111111111111110011);*/
	}

}
