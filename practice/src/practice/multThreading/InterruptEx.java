package practice.multThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptEx {
	volatile static int k=0;
	static int read = 0;
	static Lock l = new  ReentrantLock();
	public static  synchronized void update() {
		++k;
		read = k;
	}
	
	public static synchronized int read() {
		return read;
	}
	public static void main(String[] args) {
		InterruptEx ie = new InterruptEx();
		Runnable r1 = ()-> {
			for (int i=0; i<10; i++) {
				try {
					//++ie.k;
					InterruptEx.update();
					System.out.println("In Writing thread: updating value of K "+(InterruptEx.k));
					//Thread.currentThread().sleep(1000);
					if(Thread.interrupted()) {
						System.out.println("Alert!!!!!!");
						throw new InterruptedException();
					}
				} catch(InterruptedException e) {
					//e.printStackTrace();
					System.out.println("Iterrupted !!! returning"); // thread continues even if sleep throws Interrupted exp'n. need to return manually if wanted to stop the thread
					return;
				}
				
			}
		};
		
		Thread wt1 = new Thread(r1,"Writer Thread");
		Thread rt2  = new Thread(new RunnableTh1(), "Reader Thread");
		wt1.start();
		rt2.start();
		System.out.println("Value of K In Main Thread :"+ie.k);
		//System.out.println("Interrupting T1...");
		//wt1.interrupt();
		//System.out.println("T1 Interrupted...!");
	}
}

class RunnableTh1 implements Runnable{

	@Override
	public void run() {
		//InterruptEx ie = new InterruptEx();
		for(int i=0; i<10; i++) {
			InterruptEx.read();
			System.out.println("In Reading Thread: read K as "+InterruptEx.k+" to read as  "+InterruptEx.read);
		}
	}
	
}
