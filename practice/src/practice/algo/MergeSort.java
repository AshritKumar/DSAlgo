package practice.algo;

import java.util.Stack;

public class MergeSort {
	
	public void mergeSort(Integer[] arr, int s, int e) {
		if(s < e) {
			int m = (s+e) >> 1;
			mergeSort(arr, s, m);
			mergeSort(arr, m+1, e);
			merge(arr,s,e,m);
		}
	}
	
	private void merge(Integer[] arr, int s, int e, int m) {
		// create two temp arrays
		Integer[] ar1 = new Integer[m-s+1];
		Integer[] ar2 = new Integer[e-m];
		//copy values into temp arrays
		for(int i1=0; i1<ar1.length; i1++)
			ar1[i1] = arr[s+i1];
		for(int i2=0; i2<ar2.length; i2++)
			ar2[i2] = arr[m+1+i2];
		
		// compare values of temp arrays
		int k=s, i=0, j=0;
		for(; i<ar1.length && j<ar2.length && k<=e; k++) {
			if(ar1[i] < ar2[j]) {
				arr[k] = ar1[i];
				i++;
			}
			else {
				arr[k] = ar2[j];
				j++;
			}
		}
		//copy remaining elements of ar1 if any
		while(i < ar1.length) {
			arr[k] = ar1[i];
			i++;k++;
		}
		while(j < ar2.length) {
			arr[k] = ar2[j];
			j++;k++;
		}

	}
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		Integer[] arr = new Integer[] {23,12,1,56,2,-1,1,100,3,77,99,8,78,2,2,2,772,1,23,44,44,56,65,9};
		ms.mergeSort(arr, 0, arr.length-1);
		
		for(int i : arr)
			System.out.print(i+" ");
		
		Stack<Integer> st = new Stack<>();
		st.push(10);
		st.push(20);
		st.push(30);
		
		System.out.println(st.peek());
	}

}
