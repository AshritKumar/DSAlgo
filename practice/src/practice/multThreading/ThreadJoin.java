package practice.multThreading;

public class ThreadJoin {
	static Object l = new Object();
	static int count =0;
	static Runnable r1 = ()-> {
		System.out.println(Thread.currentThread().getName()+" Starting.....");
		for(int k =0; k<10000; k++) {
			synchronized (l) {
				//System.out.println(Thread.currentThread().getName()+" "+count);
				++count;
			}
			//System.out.println(Thread.currentThread().getName()+" - "+k);
			
			try {
				if(1==0)
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Stating main..");
		Thread t1 = new Thread(r1, "Thread 1");
		Thread t2 = new Thread(r1, "Thread 2");
		Thread t3 = new Thread(r1, "Thread 3");
		Thread t4 = new Thread(r1, "Thread 4");
		//Thread.currentThread().join(); // calling join on Thread.currentThread() makes the thread  to wait indefineltly
		t1.start();
		t2.start();
		t1.join(); 
		t2.join();// makes any currently executing thread wait until the thread on which join was called upon finished. In this case main will not 
				  //execute until t1 and t2 are done
		t3.start();
		t4.start();
		t3.join();
		t4.join();
		for(int m = 0; m<5; m++) {
			System.out.println("FROM MAIN !! "+m);
		}
		System.out.println("Count = "+count);
	}

}
