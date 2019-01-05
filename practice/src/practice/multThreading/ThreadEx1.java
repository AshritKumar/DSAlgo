package practice.multThreading;

public class ThreadEx1 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " "+ Thread.currentThread().getState()+" "+ Thread.currentThread().isAlive());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("++++++++++++++++++++++++++");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("*******");
			}
		});
		System.out.println(t1.getName()+" - "+ t1.getState()+" "+t1.isAlive());
		System.out.println("<><><><><><>");
		
		
		Runnable r1 = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("This thread is using rnnable lambda expr");
		};
		
		Thread tr1 = new Thread(r1,"Runnable Thread");
		System.out.println(tr1.getName()+" - "+ tr1.getState()+" "+tr1.isAlive());
		tr1.setDaemon(true);
		System.out.println(tr1.getName()+" - "+ tr1.getState()+" "+tr1.isAlive());
		tr1.start();
		t1.start();
		System.out.println(Thread.activeCount() +" "+ Thread.currentThread().getThreadGroup());
		Thread.sleep(1000);
		System.out.println(tr1.getName()+" - "+ tr1.getState()+" "+tr1.isAlive()+" "+tr1.isDaemon());
		Thread.sleep(1000);
		System.out.println(t1.getName()+" - "+ t1.getState()+" "+t1.isAlive());
		Thread.sleep(2000);
		System.out.println(t1.getName()+" - "+ t1.getState()+" "+t1.isAlive());
		
	}

}
