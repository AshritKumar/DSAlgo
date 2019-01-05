package practice.algo;

public class ExtendedGcd {
	
	public static void main(String[] args) {
		String lm = "643";
		int a = 20;
		System.out.println(10* (lm.charAt(0) - '0'));
		System.out.println((int)'0');
		System.out.println(reduceB(a, lm));
	}
	
	 private static int reduceB(int a, String b) 
	    {
	        int result = 0;
	        for (int i = 0; i < b.length(); i++) 
	        {
	            result = (result * 10 +
	                      b.charAt(i) - '0') % a;
	        }
	        return result;
	    }

}
