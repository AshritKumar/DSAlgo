package practice.algo;

import java.util.PriorityQueue;

public class Graph {
	int freq;
	String word;
	
	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<>(); //"i", "love", "leetcode", "i", "love", "coding", "coding"
		String []w = new String[] {"aa", "aaa", "a"};
		for(String s: w) {
			pq.offer(s);
			if(pq.size() >3)
				pq.poll();
		}
			
		System.out.println("abj".compareTo("asl"));
		
	}

}
