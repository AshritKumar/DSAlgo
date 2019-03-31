package practice.algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCodeProbs {
	
	 public boolean find132pattern(int[] nums) {
		int i=0, j=-1;
		for(int t=0; t<nums.length; t++) {
			i = nums[t] < nums[i] ? t : i;
			if(nums[t] > nums[i])
				j = t;
			if(i < j) {
				for(int k = j+1; k<nums.length; k++) {
					if(nums[i] < nums[k] && nums[k] < nums[j])
						return true;
				}
			}
		}
		 
		 return false;     
	  }
	 
	 public int kthSmallest(int[][] matrix, int k) {
		 PriorityQueue<Integer> mh = new PriorityQueue<>(k, (a,b)->(b-a));
		 for(int[] arr : matrix) {
			 for(int a : arr) {
				 if(mh.size() < k)
					 mh.offer(a);
				 else {
					 if(mh.peek() > a) {
						 mh.poll();
						 mh.offer(a);
					 }
				 }
			 }
		 }
		 System.out.println(mh.peek());
		 return mh.peek();
	        
	 }
	 
	 
	 public int kthSmallestinMulTable(int m, int n, int k) {
		 // m - rows, n- cols
		 int lo = 1;
		 int hi = m*n;
		 while(lo < hi) {
			 int mid = (lo + hi)/2;
			 int count = countGraterThanMid(mid, m, n, k);
			/*
			 * if(count == k) return mid;
			 */
			 if(count >= k) {
				 hi = mid;
			 } else {
				 lo = mid+1;
			 }
		 }
		 
		 System.out.println(lo);
		 return lo;
	 }
	 
	 
	 private int countGraterThanMid(int mid, int m, int n, int k) {
		// check how many nums are less than mid for every row
		 int count = 0;
		 
		 for(int i=1; i<=m; i++) {
			 count += Math.min(mid/i, n);
		 }
		 System.out.println("mid = "+mid+" count = "+count);
		 return count;
		
	}
//-----------------------------	 
	 //https://leetcode.com/problems/find-k-th-smallest-pair-distance/
	 
	 /**approach 1
	  * Generate all the pairs, keep a Max hep with diff of pairs with k size*/

	 public int smallestDistancePairHeap(int[] nums, int k) {
		 PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
		 for(int i =0; i<nums.length; i++) {
			 for(int j= i+1; j<nums.length; j++) {
				 if(maxHeap.size() < k)
					 maxHeap.offer(Math.abs(nums[j] - nums[i]));
				 else {
					 if(maxHeap.peek() > Math.abs(nums[j] - nums[i])) {
						 maxHeap.poll();
						 maxHeap.offer(Math.abs(nums[j] - nums[i]));
					 }
				 }
			 }
		 }
		 // O(N^2 ln(k)) => O(N^2 ln(N^2))
		 System.out.println(maxHeap.peek());
		 return maxHeap.peek();
		
	 }
	 
	 /**Approach 2
	  * Sort the array, min diff of pairs can be 0 and max diff can be nums[len-1] - nums[0]
	  * mid is (min+max)/2
	  * count number of pairs with difference less than mid
	  * if count is >= k that means we have shift our mid to left => min = mid
	  * if count is <, we have to shift our mid to right max = mid+1
	  * iterate till min < max, at the end min will point to kth smallest distance pair*/
	 public int smallestDistanceParirBS(int[] nums, int k) {
		 Arrays.sort(nums);
		 printArr(nums);
		 // lowest diff can be 0
		 int min = 0;
		 // highest diff can be nums[len-1] - nums[0]
		 int max = nums[nums.length-1] - nums[0];
		 int mid = (min + max )/ 2;
		 while(min < max) {
			 mid = (min + max)/2;
			 System.out.println("Lo = "+min+", hi = "+max+", mid = "+mid);
			 int count = 0;
			 int st = 0;
			 // count all pairs whose difference is <= mid
			 for(int end=0; end<nums.length; end++) {
				 while(nums[end] - nums[st] > mid) {
					 ++st;
				 }
				 System.out.println("Mid = "+ mid+", count for "+nums[end]+" is "+ (end - st));
				 count += end - st;
			 }
			 System.out.println("Total count less than "+mid+" "+ count);
			 if (count >= k) {
				 max = mid;
			 } else {
				 min = mid+1;
			 }
		 }
		 System.out.println(min);
		 return min;
	 }
	 
	 // approach 3
	 public int smallestDistancePair(int[] nums, int k) {
		 Arrays.sort(nums);
		 printArr(nums);
		 int mul[] = new int[nums.length];
		 for(int i=1; i<nums.length; i++) {
			 if(nums[i] == nums[i-1])
				 mul[i] += mul[i-1] +1;
		 }
		 printArr(mul);
		 // twice the size of largest num
		 int maxLen = nums[nums.length -1] * 2;
		 int pref[] = new int[maxLen];
		 int left = 0;
		 for(int i=0; i<maxLen; i++) {
			 while(left < nums.length && nums[left] == i)
			 	left++;
			 pref[i] = left;
		 }
		 printArr(pref);
		 
		 int lo = 0;
	     int hi = nums[nums.length - 1] - nums[0];
	     int mi = (lo + hi)/2;
	     while(lo < hi) {
	    	 mi = (lo + hi)/2;
	    	 int count = 0;
	    	 System.out.println("   lo = "+lo+", hi = "+ hi+", mid = "+mi);
	    	 for (int i = 0; i < nums.length; ++i) {
	    		 System.out.print(" nums[i] = "+ nums[i]);
	    		 System.out.print(" ,pref[nums[i] + mi] = "+ pref[nums[i] + mi]);
	    		 System.out.print(", pref[nums[i]] = "+ pref[nums[i]]);
	    		 System.out.print(",  mul[i] = "+  mul[i]);
	    		 System.out.println(" => "+(pref[nums[i] + mi] - pref[nums[i]] + mul[i]));
	                count += pref[nums[i] + mi] - pref[nums[i]] + mul[i];
	            }
	    	 System.out.println("    count = "+count);
	            //count = number of pairs with distance <= mi
	            if (count >= k) hi = mi;
	            else lo = mi + 1;
	     }
	     System.out.println(lo+" "+mi+" "+hi);
		 return lo;
	 }
	 
//------------------------------------------------------------------------------------
	public static void main(String[] args) {
		 LeetCodeProbs lps = new LeetCodeProbs();
		// System.out.println(lps.find132pattern(new int[] {6, 12, 3, 4, 6, 11, 20}));
		 //lps.kthSmallest(new int[][] {{1,  5,  9}, {10, 11, 13}, {12, 13, 15} }, 8);
		 //int v = lps.kthSmallestinMulTable(42, 34, 401);
		 lps.smallestDistanceParirBS(new int[] {1, 3, 6, 5, 6, 2, 9, 3},  8);
	}
	
	private void printArr(int [] arr) {
		for(int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	
	
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
