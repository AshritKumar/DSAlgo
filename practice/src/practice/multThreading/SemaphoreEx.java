package practice.multThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreEx {
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		long start = System.currentTimeMillis();
		Connection con = Connection.getInsatnce();
		con.connect();
		ExecutorService esc = Executors.newCachedThreadPool();
		Runnable doProcess = (con::connect); // method reference
		for(int i=0; i<50; i++) {
			esc.submit(doProcess);
		}
		esc.shutdown();
		esc.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("completed");
		long stop = System.currentTimeMillis();
		System.out.println("Time Taken = "+(double)((stop - start)/1000));
		
	}
}

class Connection{
	private  int conn = 0;
	private static Connection c = new Connection();
	Semaphore sem = new Semaphore(10);
	private Connection(){
		System.out.println("Constructor called ..");
	}
	
	static Connection getInsatnce() {
		return c;
	}
	
	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		synchronized (this) {
			++conn;
			System.out.println(Thread.currentThread().getName()+" - Got connection number - "+conn);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			--conn;
			System.out.println(Thread.currentThread().getName()+" - DONE releasing conection ");
		}
		sem.release();
	}
}
