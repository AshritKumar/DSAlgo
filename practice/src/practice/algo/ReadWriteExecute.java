package practice.algo;

import java.util.HashMap;

public class ReadWriteExecute {
    public static int symbolicToOctal(String permString) {
        HashMap<Character,Integer> permVals = new HashMap<>();
        permVals.put('r',4);
        permVals.put('w', 2);
        permVals.put('x',1);
        permVals.put('-',0);
        int pos = 0;
        int psum = 0;
        String result = "";
        for(int i=0; i<permString.length(); i++) {
        	if(pos == 3) {
        		//System.out.println(pos);
        		result += psum;
        		pos = 0;
        		psum = 0;
        	}
        	char perm = permString.charAt(i);
        	//System.out.print(perm);
        	Integer val = permVals.get(perm);
        	//System.out.println();
        	if(val == null)
    			throw new UnsupportedOperationException("Invalid character + "+perm+" not supported");
        	psum += val;
        	pos++;
        }
        result += psum;
        int intRes = Integer.parseInt(result);
        System.out.println(result);
        return intRes;
        
    }

    public static void main(String[] args) {
        // Should write 752
        System.out.println(ReadWriteExecute.symbolicToOctal("rwxr-x-w-"));
    }
}