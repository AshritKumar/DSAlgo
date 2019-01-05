package practice.algo;

public class FibonacciEx {
	public static void main(String[] args) {
		RecursiveFib rf = new RecursiveFib();
		Double val = IterativeFid.findFibonacci(80);
		//System.out.println(val);
		//System.out.printf("%.0f\n", val);
		System.out.println(2%5);
		System.out.println(FibOptimized.calcFiib(11));
		
	}
}
//bad impl recursion time complexity 2 pow n
class RecursiveFib{
	public static Integer findFibonacci(int n) {
		if(n == 1)
			return 1;
		if(n== 0)
			return 0;
		return findFibonacci(n-1)+findFibonacci(n-2);
	}
}

// complexity O(n)
class IterativeFid{
	public static Double findFibonacci(int n) {
		Double f0 = 0d, f1 = 1d, c=0d;
		if(n== 0)
			return f0;
		for (int i = 2; i<=n; i++) {
			c = (Double)f0+f1;
			f0 = f1;
			f1 = c;
		}
		return f1;
	}
}

class FibOptimized{
	
	private static int[][] doMultiply(int f[][], int m[][]) {
			int k = f[0][0]*m[0][0] + f[0][1]*m[1][0];
			int l = f[0][0]*m[0][1] + f[0][1]*m[1][1];
			int n = f[1][0]*m[0][0] + f[1][1]*m[1][0];
			int o = f[1][0]*m[0][1] + f[1][1]*m[1][1];
			f[0][0] = k;
			f[0][1] = l;
			f[1][0] = n;
			f[1][1] = o;
			
			return f;

	}
	
	private static void exponentiate(int f[][], int n) {
		if(n==0 || n==1)
			return;
		int m[][] = new int [][] {{1,1},{1,0}};
		exponentiate(f, n/2);
		doMultiply(f, f);
		if(n%2 != 0)
			doMultiply(f, m);
	}
	
	public static int calcFiib(int n) {
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		int f[][] = new int[][] {{1,1},{1,0}};
		exponentiate(f, n-1);
		return f[0][0];
		
	}
}