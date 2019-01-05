package practice.multThreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorsEx1 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exs = Executors.newFixedThreadPool(10);
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
		bq.put(10);
		System.out.println("Added one item....");
		bq.put(20);
		System.out.println("added the second item ....");
		bq.take();
		bq.take();
		System.out.println("Removed two");
		bq.take();
		System.out.println("**********8");
		
	}

}
