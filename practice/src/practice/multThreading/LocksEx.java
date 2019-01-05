package practice.multThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksEx {
	public static void main(String[] args) throws InterruptedException {
		LockTest lt = new LockTest();
		Thread t1 = new Thread(()->lt.threa1Counter());
		Thread t2 = new Thread(()->lt.threa12Counter());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(lt.counter);
	}

}

class LockTest{
	Lock l1 = new ReentrantLock();
	Lock l2 = new ReentrantLock();
	int counter = 0;
	public void threa1Counter() {
		getlocks(l1, l2);
		for(int i=0 ; i<10000; i++) {
			counter++;
		}
		l1.unlock();
		l2.unlock();
	}
	public void threa12Counter() {
		getlocks(l1, l2);
		for(int i=0 ; i<10000; i++) {
			counter++;
		}
		l2.unlock();
		l1.unlock();
	}
	
	public void getlocks(Lock l1, Lock l2) {
		boolean gl1 = false, gl2 = false;
		while(true) {
			try {
				gl1 = l1.tryLock();
				gl2 = l2.tryLock();
			} finally {
				if(gl1 && gl2)
					return;
				if(gl1)
					l1.unlock();
				if(gl2)
					l2.unlock();
			}
			
		}
	}
}
