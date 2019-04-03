package practice.algo;

import java.util.ArrayList;

public class MaxSumSubArray {
	
	public static void main(String[] args) {
		
		//getMaxSumSubArrayOptimized(new int[] {-2, -3, 4, -1, 2, -1, -5, -3});
		getMaxSumSubArrayOptimized_f(new int[] {-2, -3, 4, -1, 2, -1, -5, -3});
	}
	
	// time complexity o(n^3).
	public static int getMaxSumSubArray(int[] arr) {
		int ans = Integer.MIN_VALUE;
		for(int subArrSize=1; subArrSize<=arr.length; subArrSize++) {
			for(int stIndx=0; stIndx<arr.length; stIndx++) {
				if(subArrSize+stIndx > arr.length)
					break;
				int sum = 
						0;
				for(int k=stIndx; k<subArrSize+stIndx; k++) {
					sum += arr[k];
					System.out.print(arr[k]+" ");
				}
				System.out.print(" -> "+sum);
				ans = Math.max(sum, ans);
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println(ans);
		return ans;
	}
	
	// improved sol. O(n^2)
	public static int getMaxSumSubArrayImp1(int[] arr) {
		
		int ans = Integer.MIN_VALUE;
		int sum = 0;
		
		for(int stIndx = 0; stIndx< arr.length; stIndx++) {
			sum = 0;
			for(int subArrSize = 1; subArrSize <= arr.length; subArrSize++) {
				if(stIndx + subArrSize > arr.length)
					break;
				System.out.print(arr[stIndx + (subArrSize-1)]+" ");
				sum += arr[stIndx + (subArrSize-1)];
				System.out.print(" -> "+ sum+"\n");
				ans = Math.max(sum, ans);
			}
			
		}
		System.out.println("ans "+ans);
		return ans;
		
	}
	
	// O(n) - Kadane’s Algorithm, will not work if all values are -ve
	public static int getMaxSumSubArrayOptimized(int[] arr) {
		
		int ans = Integer.MIN_VALUE;
		int sum = 0;
		ArrayList<Integer> sumArr = new ArrayList<>();
		
		for(int i=0; i<arr.length; i++) {
			sum = sum+arr[i];
			sumArr.add(arr[i]);
			if(sum < 0) {
				sum = 0;
				sumArr.clear();
			}
			else if(ans < sum) {
				ans = sum;
			}
			System.out.println(sumArr+" -> "+sum);
			/*
			 * if(sum+arr[i] >= 0) { sum += arr[i]; sumArr.add(arr[i]);
			 * System.out.println(arr[i]); System.out.println(sumArr); } else { ans =
			 * Math.max(sum, ans); sum = 0; sumArr.clear(); }
			 */
				
		}
		ans = Math.max(sum, ans);
		System.out.println(ans);
		return ans;
		
	}
	
	// sol'n works for all -ve numbers as well
	public static int getMaxSumSubArrayOptimized_f(int[] arr) {
		int ans = arr[0];
		int sum = arr[0];
		
		for(int i=1; i<arr.length; i++) {
			System.out.print("prev sum => "+sum+" calc sum =>");
			//sum = Math.max(arr[i], arr[i]+sum);
			if(sum+arr[i] > arr[i]) {
				sum += arr[i];
			} else {
				sum = arr[i];
			}
			if(ans < sum) {
				ans = sum;
			}
			System.out.println(" arr["+i+"] = "+arr[i]+", ans = "+ans);
		}
		
		System.out.println(ans);
		return ans;
	}

}
