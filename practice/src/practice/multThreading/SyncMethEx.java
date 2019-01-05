package practice.multThreading;

import java.awt.print.Printable;
import java.util.concurrent.Executors;

class SynchronizedCount{
	private  int c = 0;
	private boolean read= true;
	
	 public void increment() {
		 synchronized(this) {
			 for(int k = 0; k<= 100; k++) {
				 if(read) {
					 try {
						System.out.println("\n"+Thread.currentThread().getName()+": Waiting, ");
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 }
				 System.out.println("\n"+Thread.currentThread().getName()+": Done with Waiting Increamenting C to "+ (++c) );
				// ++c;
				 read = true;
				 notifyAll();
			 } 
		 }
    }

    public void decrement() {
        c--;
    }

    public  int value() {
    	synchronized (this) {
    		while(c < 100) {
    			if(! read) {
            		try {
            			System.out.println(Thread.currentThread().getName()+": Waiting for update, ");
        				wait();
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
            	}
        		read = false;
        		notifyAll();
        		System.out.println(Thread.currentThread().getName()+":got update reading C val is "+c);
        		
    		}
    		return 100;
		}
    }
}

public class SyncMethEx {
	
	public static void getVals(int kVal) {
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedCount sc = new SynchronizedCount();
		Runnable r1= ()->{
			sc.increment();
			System.out.println(Thread.currentThread().getName()+": Increamenting counter");
		};
		
		Runnable r2= ()->{
			System.out.println(Thread.currentThread().getName()+": Decrementing counter");
			sc.decrement();
		};
		

		Runnable r3= ()->{
			System.out.println(Thread.currentThread().getName()+": value of c is "+sc.value());
			//if(sc.value() == 500)
		};
		Thread incT = new Thread(r1,"Inc Thread");
		//Thread decT = new Thread(r2,"Dec Thread");
		Thread readT = new Thread(r3,"Read Thread");
		incT.start();
		//decT.start();
		//incT.join();
		readT.start();
		
	}
   
}
