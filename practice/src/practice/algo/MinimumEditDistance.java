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
	
	public static int minumEditDistanceDynamic(String s1, String s2) {
		// create a array of [s1+1][s2+1] extra row column for empty string comparisions as well
		int distArr[][] = new int[s1.length()+1][s2.length()+1];
		// first row with 0, 1, 2...
		for(int i=0; i<s2.length()+1; i++)
			distArr[0][i] = i;
		// first column with 0, 1, 2...
		for(int i=0; i<s1.length()+1; i++)
			distArr[i][0] = i;
		
		// for each row
		for(int i=1; i<s1.length()+1; i++) {
			//each column
			for(int j=1; j< s2.length()+1; j++) {
				// compute distance with left cell
				int hlDist = distArr[i][j-1] + 1;
				
				// compute distance top cell
				int vtDist = distArr[i-1][j] + 1;
				
				// compute diagonal dist
				int diagDist = distArr[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1);
				
				distArr[i][j] = min(hlDist, vtDist, diagDist);
			}
		}
		
		//printArray(distArr);
		return distArr[s1.length()][s2.length()];
	}
	
	private static void printArray(int[][] distArr) {
		for(int i=0; i<distArr.length; i++) {
			for(int j=0; j<distArr[i].length; j++)
				System.out.print(distArr[i][j]+"  ");
			System.out.println();
		}
		
	}

	public static int findEditDistRecursive(String s1, String s2) {
		return minimumEditDistanceRecursive(s1, s2, s1.length(), s2.length());
	}
	
	
	public static void main(String[] args) {
		System.out.println(minumEditDistanceDynamic("hakunamatata","hakurata"));
	}

}
