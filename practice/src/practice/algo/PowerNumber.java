package practice.algo;

public class PowerNumber {
	
	public static void main(String[] args) {
		Long res = doExpo(22,1l);
		System.out.println(res);
		System.out.println(2 >> 1);
		int a[] = new int[] {0,0,1,1};
		System.out.println(a[0]);
	}
	
	private static Long doExpo(int i, Long exp) {
		if(exp == 0)
			return 1l;
		Long res = doExpo(i, exp >> 1);
		if((exp & 1) != 1) {
			return res*res;
		}
		return i*res*res;
	}

	public int[] doRightShift(int[] i) {
		int r[] = new int[i.length];
		
		for (int m=i.length-1; m>0; m--) {
			r[m] = i[m-1];
		}
		return r;
	}

}
