package practice.algo;

import javax.swing.ButtonModel;

public class NextSmallestPalindrome {
	
	public static void main(String[] args) {
		Integer[] p = getNextPalindrome(767868756);
		printNum(p);
	}

	static void printNum(Integer[] p) {
		if(p[0] < 0)
			System.out.print("-");
		for(int i : p)
			System.out.print(Math.abs(i));
	}

	static Integer[] getNextPalindrome(Integer num) {
		int numLen = (int)Math.log10(Math.abs(num))+1;
		Integer[] nArr = new Integer[numLen];
		int temp = Math.abs(num);
		int k = nArr.length-1;
		// create an integre array out the number
		while(temp > 0 && k >=0) {
			if(num <0)
				nArr[k] = - (temp%10);
			else
				nArr[k] = temp%10;
			temp /= 10;
			k--;
		}
		if(isAllNines(nArr)) {
			Integer[] nxtPalin = new Integer[numLen+1];
			nxtPalin[0] = 1;
			for(int i = 1; i< nxtPalin.length -1; i++)
				nxtPalin[i] = 0;
			nxtPalin[nxtPalin.length-1] = 1;
			return nxtPalin;
		} else {
			// check if the length of num is odd or even and assign i and j positions
			int mid = numLen /2;
			int i = mid -1;
			int j = numLen%2 == 0 ? mid : mid+1;
			// iterate till positions where values at i and j are not same
			int Itemp  = i, Jtemp = j;
			while(Itemp >=0 && Jtemp< numLen && nArr[Itemp] == nArr[Jtemp]) {
				Itemp--;
				Jtemp++;
			}
			
			// check if the digit at ith position is smaller than digit at jth position, if smaller we need to add i to digit in ith position and
			//Propagate the carry toward left
			boolean isLeftSmall = false;
			if(Itemp < 0 || nArr[Itemp] < nArr[Jtemp])
				isLeftSmall = true;
			
			Itemp  = i; Jtemp = j;
			// mirror the left part of the array to right part
			while(Itemp >=0 && Jtemp< numLen) {
				nArr[Jtemp] = nArr[Itemp];
				--Itemp;
				++Jtemp;
			}
			// if left side is smaller or the number is already plaindrome
			if(isLeftSmall) {
				// if number has odd length add 1 to mid postion
				Itemp  = i; Jtemp = j;
				int carry = 1;
				if(numLen % 2 == 1) {
					nArr[mid]+= 1;
					carry = nArr[mid]/10;
					nArr[mid] %= 10;
				}
				if(carry >0)
					while(Itemp >=0 && Jtemp < numLen) {
						int t = nArr[Itemp] + carry;
						nArr[Itemp] = t%10;
						carry = t/10;
						nArr[Jtemp] = nArr[Itemp];
						Jtemp++;
						Itemp--;
					}
			}
			return nArr;
		}
		
	}

	private static boolean isAllNines(Integer[] nArr) {
		for(int i= 0; i< nArr.length; i++) {
			if(nArr[i] != 9)
				return false;
		}
		return true;
	}

}
