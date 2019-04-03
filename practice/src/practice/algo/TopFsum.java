package practice.algo;

import java.util.HashMap;

//Leetcode 454 Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
public class TopFsum {
	
	public static void main(String[] args) {
		TopFsum tf = new TopFsum();
		tf.fourSumCount(new int[] {1, 2}, new int[] {-2, -1}, new int[] {-1, 2}, new int[] {0, 2});
	}
	
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int sz = A.length;
		int res = 0;
		HashMap<Integer, Integer> sum1 = new HashMap<>();
		
		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				sum1.put(A[i]+B[j], sum1.getOrDefault(A[i]+B[j], 0)+1);
			}
		}
		
		for(int c : C) {
			for(int d: D) {
				res += sum1.getOrDefault(-(c+d), 0);
			}
		}
		
		System.out.println(res);
		return res;
        
    }

}
