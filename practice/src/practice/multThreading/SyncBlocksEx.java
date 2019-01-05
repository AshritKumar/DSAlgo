package practice.multThreading;

import java.util.List;
import java.util.ArrayList;

public class SyncBlocksEx {
	volatile static String[] namesL =  {"ashrit", "Ramesh", "Krish", "Rinku", "Raj", "Giri", "Lelu", "Sai", "Teju", "Shiva", "Usman", "Arun",
				"ashrit", "Ramesh", "Krish", "Rinku", "Raj", "Giri", "Lelu", "Sai", "Teju", "Shiva", "Usman", "Arun",
				"ashrit", "Ramesh", "Krish", "Rinku", "Raj", "Giri", "Lelu", "Sai", "Teju", "Shiva", "Usman", "Arun",
				"ashrit", "Ramesh", "Krish", "Rinku", "Raj", "Giri", "Lelu", "Sai", "Teju", "Shiva", "Usman", "Arun"};
	public static void main(String[] args) throws InterruptedException {
		
		Names nm = new Names();
		//Names nm1 = new Names();
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		System.out.println(cld.toString());
		System.out.println(new Names().hashCode() +" ,"+ new Names().hashCode());
		Runnable r = ()->{
			for (int k=0 ; k<24; k++)
				nm.addNames(namesL[k]);
		};
		
		Runnable r1 = ()->{
			for (int k=24 ; k<48; k++)
				nm.addNames(namesL[k]);
		};
		
		Thread t1 = new Thread(r, "T One");
		Thread t2 = new Thread(r1, "T Two");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(nm.getCount());
		System.out.println(nm.getNameList().size());
		for(Object s : nm.getNameList())
			System.out.print(s+" ");
	}
}

class Names{
	volatile private List nameList = new ArrayList();
	private int count = 0;
	private String name;
	
	public void addNames(String n) {
		//System.out.println(Thread.currentThread().getName()+" trting to enter synch block");
		synchronized (this) {
			//System.out.println(Thread.currentThread().getName()+" Entered !!!!!");
			this.name = n;
			++count;
		}
		nameList.add(n);
		//System.out.println(Thread.currentThread().getName()+"***************************");
		//System.out.println(Thread.currentThread().getName()+"***************************");
		//System.out.println(Thread.currentThread().getName()+"***************************");
		//System.out.println(Thread.currentThread().getName()+"***************************");
		
	//	System.out.println(Thread.currentThread().getName()+ " Done  ***************************");
	}
	
	public int getCount() {
		return count;
	}
	public List getNameList() {
		return nameList;
	}
	
}
