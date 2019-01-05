package practice.multThreading;

public class SynchronizedRGB {

    // Values must be between 0 and 255.
    private int red;
    private int green;
    private int blue;
    private String name;

    private void check(int red,
                       int green,
                       int blue) {
        if (red < 0 || red > 255
            || green < 0 || green > 255
            || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red,
                           int green,
                           int blue,
                           String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red,
                    int green,
                    int blue,
                    String name) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }
    
    public static void main(String[] args) {
    	SynchronizedRGB color =
    		    new SynchronizedRGB(100, 150, 233, "Pitch Black");
    	int myColorInt = color.getRGB();      //Statement 1
    	String myColorName = color.getName(); //Statement 2
    	
    	Thread c1 = new Thread(() ->  {
    		color.set(10, 20, 30, "T1 Color");
    		int myColorInt1 = color.getRGB();      //Statement 1
        	String myColorName1 = color.getName(); //Statement 2
    		System.out.println(Thread.currentThread()+": color :"+myColorInt1+" , Name "+myColorName1);
    	},"Thread T1");
    	Thread c2 = new Thread(() ->  {
    		color.set(10, 20, 30, "T2 Color");
    		int myColorInt1 = color.getRGB();    
    		System.out.println("******************");
    		System.out.println("******************");
    		System.out.println("******************");
    		System.out.println("******************");
        	String myColorName1 = color.getName(); 
    		System.out.println(Thread.currentThread()+": color :"+myColorInt1+" , Name "+myColorName1);
    	},"Thread T2");
    	Thread c3 = new Thread(() ->  {
    		color.set(10, 20, 30, "T3 Color");
    		int myColorInt1 = color.getRGB(); 
        	String myColorName1 = color.getName(); 
    		System.out.println(Thread.currentThread()+": color :"+myColorInt1+" , Name "+myColorName1);
    	},"Thrwad T3");
    	
    	c1.start();
    	c2.start();
    	c3.start();
    	
		System.out.println(Thread.currentThread()+": color :"+myColorInt+" , Name "+myColorName);
	}
}
