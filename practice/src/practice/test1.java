package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class test1 {
	public static void main(String[] args) {
		test1 t = new test1();
		Integer arr[][]  = new Integer[5][];
		int i = 0;
		ArrayList<String> a = new ArrayList<>();
		a.add("one");
		a.add("Two");
		a.add("Three");
		//new Runn().start();
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random r = new Random();
				for(int i = 0; i<1; i++) {
					Math.sin(r.nextDouble());
				}
				System.out.println("Done");
				
			}
		});
		t3.start(); 
		
	/*	LinkedList<Integer> l = new LinkedList<>();
		l.add(1, 20);
		l.add(10);
		l.add(20);
		l.add(3, 30);
		l.remove(1);
		l.get(1);*/
		
		practice.algo.LinkedList<Integer> pl = new practice.algo.LinkedList<>();
		pl.sortedInsert(10);
		pl.sortedInsert(4);
		pl.sortedInsert(6);
		pl.sortedInsert(19);
		pl.sortedInsert(100);
		pl.sortedInsert(5);
		pl.sortedInsert(-1);
		pl.sortedInsert(3);
		pl.sortedInsert(45);
		pl.delete(1);
		System.out.println(pl);
		
		
	}
	
	public void printList(ArrayList<String> al) {
		al.add("hey");
		System.out.println(al.hashCode());
		for(String s : al)
			System.out.println(s);
	}
	
		
	}


class Runn extends Thread{

	@Override
	public void run() {
		//throw new RuntimeException();
		
	}
}
