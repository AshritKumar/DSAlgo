package practice.algo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class GenericSorter <T> {
	
	  void printArray(T arr[])
	    {
	        int n = arr.length;
	        for (int i=0; i<n; ++i)
	            System.out.print(arr[i] + " ");
	 
	        System.out.println();
	    }
	  private void printArray(int[] arr) {
			for(int i: arr)
				System.out.print(i + " ");
			System.out.println();
		}
	
	public void insertionSort(Comparable<T> items[]) {
		for(int i=1; i<items.length; i++) {
			Comparable<T> curr = items[i];
			int j = i-1;
			while(j>=0 && items[j].compareTo((T) curr) > 0) {
				items[j+1] = items[j];
				j--;
			}
			items[j+1] = curr;
		}
	}
	/*--------Merge Sort -----------------*/
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
	/*-------Quick Sort--------*/
	
	public void quickSort(Comparable<T> arr[], int s, int e) {
		if(s < e) {
			// first create the partition and get pivot index
			int pvtIndx = randomPartition(arr, s, e);
			// recursively call quick sort for start  index to pvtIndx-1  and s pvtIndx+1 index to end
			quickSort(arr, s, pvtIndx-1);
			quickSort(arr, pvtIndx+1, e);
		}
	}
	
	private int partitionArray(Comparable<T>[] arr, int s, int e) {
		// choose last index as pivot;
		int pvt = e;
		// choose partition index as zero and start from 0 to end -1, if any element is less than the pivot swap positions that element and partionIndex element and increment partition indes
		// by the end of loop all elements left to partition index are less that it and elemnts right (till e-1) are grater.
		//lastly swap the elements in partIndex and pivot element
		int partIndx = s;
		for(int j=s ; j <e; j++) {
			if(arr[j].compareTo((T) arr[pvt]) <= 0) {
				swap(arr,j,partIndx);
				partIndx++;
			}
		}
		// lastly swap partIndex with pivot(end) index element
		swap(arr,partIndx,pvt);
		return partIndx;
	}
	
	private int randomPartition(Comparable<T> arr[], int s, int e) {
		Random r = new Random();
		int randPvt = r.nextInt(e-s) + s;
		swap(arr,randPvt,e);
		return partitionArray(arr, s, e);
	}

	private void swap(Comparable<T>[] arr, int e1, int e2) {
		Comparable<T> temp = arr[e1];
		arr[e1] = arr[e2];
		arr[e2] = temp;
		
	}
	
//-------------- counting Sort-------------------//
private Integer[] countingSort(Integer[] arr, int i) {
	int count[] = new int[10];
	Arrays.fill(count, 0);
	Integer[] out = new Integer[arr.length];
	// mark counts of the i th place of the digits in array - 9,2,6,1,15,7,1,0
	for(int k=0; k<arr.length; k++) 
		++count[(arr[k]/i)%10];
	
	System.out.println(i+" th count ");
	printArray(count);
	// sum the counts adj places to find exact position of each element
		for(int k = 1; k<count.length; k++)
			count[k] = count[k] + count[k-1];
		
	//sort based on ith position and copy it to out array
	for(int k=arr.length-1; k >=0 ; k--	) {
		out[ count[ (arr[k]/i)%10 ] - 1 ] = arr[k];
		--count[(arr[k]/i)%10];
	}
	/*for(int k=0; k<arr.length; k++)
		arr[k] = out[k];*/
	System.out.println("Sorted to "+i+" Place");
	printArray((T[])out);
	return out;
	
}



//--------------- Radix Sort---------------------//
public void radixSort(Integer arr[]) {
	int max = findMax(arr);
	int i = 1;
	arr = countingSort(arr, i);
	max = max/10;
	i = i*10;
	while(max > 0) {
		arr = countingSort(arr,i);
		max = max/10;
		i = i*10;
	}
}


	private int findMax(Integer[] arr) {
	int max = arr[0];
	for(int i=1; i<arr.length; i++)
		max = arr[i] > max ? arr[i]: max;
	return max;
}

	public static void main(String[] args) {
		GenericSorter<Integer> gnSrt = new GenericSorter<>();
		Integer arr[] = new Integer[] {23,26,54,3485,88,98,83,45,66,12,26};
		//intSrt.insertionSort(arr);
		//gnSrt.printArray(arr);
		//gnSrt.quickSort(arr, 0, arr.length-1);
		//gnSrt.printArray(arr);
		gnSrt.mergeSort(arr,0,arr.length-1);
	}

}
