package practice.multThreading;

public class SynchEx2 {
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	public void meth1() {
		System.out.println(Thread.currentThread().getName()+": Method 1");
		synchronized (lock1) {
			System.out.println(Thread.currentThread().getName()+"Calling method2()");
			meth2();
			
		}
		
	}
	
	public void meth2() {
		System.out.println(Thread.currentThread().getName()+": Method 2");
		synchronized (lock2) {
			System.out.println(Thread.currentThread().getName()+": Calling method1()");
			try {
				System.out.println(Thread.currentThread().getName()+": Meth 2 critical");
				lock1.wait();
				System.out.println(Thread.currentThread().getName()+": --Meth 2 critical");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		SynchEx2 x2 = new SynchEx2();
		
		Thread t1 = new Thread(()-> x2.meth1(),"Meth1 Thread");
		Thread t2 = new Thread(()-> x2.meth2(),"Meth2 Thread");
		t1.start();
		t2.start();
	}

}
