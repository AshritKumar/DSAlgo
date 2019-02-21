package practice;

import java.util.ArrayList;
import java.util.List;
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
		sortLargest();
		
		
	}
	
	public void printList(ArrayList<String> al) {
		al.add("hey");
		System.out.println(al.hashCode());
		for(String s : al)
			System.out.println(s);
	}
	
	public static void sortLargest() {
		List<Integer> al = new ArrayList<>();
		al.add(10);
		al.add(969);
		al.add(9);
		al.sort((i1,i2) -> {
			String s1 = i1.toString();
			String s2 = i2.toString();
			return (s2+s1).compareTo(s1+s2);
		});
		System.out.println(al);
		System.out.println("a".compareTo("a"));
	}
	
		
	}


class Runn extends Thread{

	@Override
	public void run() {
		//throw new RuntimeException();
		
	}
}
