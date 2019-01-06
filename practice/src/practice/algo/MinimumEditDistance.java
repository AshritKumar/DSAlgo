package practice.algo;

public class MinimumEditDistance {
	
	public static int minimumEditDistanceRecursive(String s1, String s2, int s1len, int s2len) {
		if(s1len == 0)
			return s2len;
		if(s2len == 0)
			return s1len;	
		int diff = s1.charAt(s1len-1) == s2.charAt(s2len -1) ? 0 : 1;
		int distPrefix = minimumEditDistanceRecursive(s1, s2, s1len-1, s2len-1) + diff;
		int distAtoBSuf = minimumEditDistanceRecursive(s1, s2, s1len, s2len-1) +1;
		int distAsufToB = minimumEditDistanceRecursive(s1, s2, s1len-1, s2len) +1;
		return min(distPrefix, distAtoBSuf, distAsufToB);
	}

	private static int min(int distPrefix, int distAtoBSuf, int distAsufToB) {
		return Math.min(distPrefix, Math.min(distAtoBSuf, distAsufToB));
	}
	
	public static int findEditDistRecursive(String s1, String s2) {
		return minimumEditDistanceRecursive(s1, s2, s1.length(), s2.length());
	}
	
	public static void main(String[] args) {
		System.out.println(findEditDistRecursive("hakunamatata","hakumata"));
	}

}
