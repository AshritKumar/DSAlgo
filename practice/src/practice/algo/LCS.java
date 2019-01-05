package practice.algo;

public class LCS {
	public int[][] buildLcsMatrx(String w1, String w2){
		int w1l  = w1.length();
		int w2l = w2.length();
		int lcs[][] = new int[w2l+1][w1l+1];
		for(int i = 0; i<=w2l; i++) {
			for(int j=0; j<=w1l; j++) {
				if(i==0 || j==0)
					lcs[i][j] = 0;
				else if(w2.charAt(i-1) == w1.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1]+1;
				} else {
					lcs[i][j] = max(lcs[i][j-1], lcs[i-1][j]);
				}
			}
			//s = s+"\n";
		}
		return lcs;
	}

	private int max(int i, int j) {
		if(i > j)
			return i;
		return j;
	}
	
	public String getLcs(String w1, String w2) {
		StringBuilder sb = new StringBuilder();
		int m[][] = buildLcsMatrx(w1,w2);
		printMatrix(m);
		int w1l = w1.length();
		int w2l = w2.length();
		int i=w2l,j=w1l;
		while(i>0 && j>0) {
			if(w1.charAt(j-1) == w2.charAt(i-1)) {
				sb = sb.append(w2.charAt(i-1));
				i--;j--;
			} else if(m[i][j-1] > m[i-1][j])
				j--;
			else
				i--;
		}
		System.out.println(sb.reverse().toString());
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) {
		LCS l = new LCS();
		l.getLcs("uyuyuiuughgshgjh","ygsaghghjsagdhsghuhjkhk");
	}

	private static void printMatrix(int[][] m) {
		int col = m[0].length;
		for(int i = 0; i< m.length; i++) {
			for(int j=0; j< col; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
