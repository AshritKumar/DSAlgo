package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;



public class MedianUnSorted {

	public static void getMedian(int[] integers){
		
		double median;
		
		// for lower half of numbers
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return -1 * o1.compareTo(o2); // we need biggest number at the top so we are multiplying by 1
											  // normally PQ stores lowest number at the top
			};
		});
		
		//for higher half of numbers
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o1.compareTo(o2); 
			};
		});
		
		ArrayList<Integer> aryList = new ArrayList<>();
		
		// take array and find medians
		for (int i = 0; i < integers.length; i++) {
			
			aryList.add(integers[i]);//this is just for printing 
			
			addNumber(integers[i], maxHeap, minHeap);
			rebalanceHeaps(maxHeap, minHeap);
			/*median = getMedian(maxHeap, minHeap);
			
			//printing the answer in pretty format
			Collections.sort(aryList);
			System.out.println("Median of List of numbers "+aryList.toString()+" is "+median);*/
		}
		
		median = getMedian(maxHeap, minHeap);
		
		//printing the answer in pretty format
		Collections.sort(aryList);
		System.out.println("Median of List of numbers "+aryList.toString()+" is "+median);
		
	}
	
		
	public static void addNumber(int number, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap){
		
		//if number is less than max number in the lower numbers heap
		if(maxHeap.size() == 0 || maxHeap.peek() > number){
			maxHeap.add(number);
		}else{
			minHeap.add(number);
		}
	}
	
	public static void rebalanceHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap){
		
		if(maxHeap.size() - minHeap.size() > 1){
			minHeap.add(maxHeap.poll());
		}else if (minHeap.size() - maxHeap.size() > 1){
			maxHeap.add(minHeap.poll());
		}
		
	}
	
	public static double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap){
		
		//odd
		if((maxHeap.size()+minHeap.size())%2 > 0){
			
			if(maxHeap.size() > minHeap.size()){
				return maxHeap.peek();
			}else
				return minHeap.peek();
			
		}else{
			//even		
			return (double) (maxHeap.peek()+minHeap.peek())/2;
			
		}
		
	}
	
	public static void main(String[] args) {
		
		//pass the test array
		int[] arr1 = {100,9,6,23,45};
		int[] arr2 = {12,29,18,8,53,37};
		findMedian(arr1,arr2);
		
	}


	private static void findMedian(int[] arr1, int[] arr2) {
double median;
		
		// for lower half of numbers
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return -1 * o1.compareTo(o2); // we need biggest number at the top so we are multiplying by 1
											  // normally PQ stores lowest number at the top
			};
		});
		
		//for higher half of numbers
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		int i=0;
		for(i=0; i<arr1.length && i<arr2.length; i++) {
			addNumber(arr1[i], maxHeap, minHeap);
			rebalanceHeaps(maxHeap, minHeap);
			
			addNumber(arr2[i], maxHeap, minHeap);
			rebalanceHeaps(maxHeap, minHeap);
		}
		
		while(i < arr1.length) {
			addNumber(arr1[i], maxHeap, minHeap);
			rebalanceHeaps(maxHeap, minHeap);
			i++;
		}
		
		while(i < arr2.length) {
			addNumber(arr2[i], maxHeap, minHeap);
			rebalanceHeaps(maxHeap, minHeap);
			i++;
		}
		System.out.println(maxHeap);
		System.out.println(minHeap);
		
		median = getMedian(maxHeap, minHeap);
		System.out.println(median);


		
	}
	
}