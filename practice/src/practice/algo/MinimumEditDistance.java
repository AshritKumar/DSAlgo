package practice.algo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumEditDistance {
	
	//static final String[] dictWords  = {"ashrit", "ransom", "gas", "mass", "national", "nation", "java", "python", "international", "calender",
										//"mini", "venom", "stubborn", "vehicle", "google", "emotion", "to", "too", "ash", "as", "frustration", "spring", "kumar"};
	static List<String> dictWords;
	
	public static List<String> getFileWords() {
		if(dictWords == null) {
			try {
				dictWords = Files.lines(Paths.get(System.getProperty("user.dir")+"\\src\\wordlist2.txt")).collect(Collectors.toList());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dictWords;
	}
	
	public static int minimumEditDistanceRecursive(String s1, String s2, int s1len, int s2len) {
		if(s1len == 0)
			return s2len;
		if(s2len == 0)
			return s1len;	
		int diff = s1.charAt(s1len-1) == s2.charAt(s2len -1) ? 0 : 1;
		int distPrefix = minimumEditDistanceRecursive(s1, s2, s1len-1, s2len-1) + diff;
		int distAtoBSuf = minimumEditDistanceRecursive(s1, s2, s1len, s2len-1) +1;
		int distAsufToB = minimumEditDistanceRecursive(s1, s2, s1len-1, s2len) +1;
		return min(distPrefix, distAtoBSuf, distAsufToB);
	}

	private static int min(int distPrefix, int distAtoBSuf, int distAsufToB) {
		return Math.min(distPrefix, Math.min(distAtoBSuf, distAsufToB));
	}
	
	public static int minumEditDistanceDynamic(String s1, String s2) {
		// create a array of [s1+1][s2+1] extra row column for empty string comparisions as well
		int distArr[][] = new int[s1.length()+1][s2.length()+1];
		// first row with 0, 1, 2...
		for(int i=0; i<s2.length()+1; i++)
			distArr[0][i] = i;
		// first column with 0, 1, 2...
		for(int i=0; i<s1.length()+1; i++)
			distArr[i][0] = i;
		
		// for each row
		for(int i=1; i<s1.length()+1; i++) {
			//each column
			for(int j=1; j< s2.length()+1; j++) {
				// compute distance with left cell
				int hlDist = distArr[i][j-1] + 1;
				
				// compute distance top cell
				int vtDist = distArr[i-1][j] + 1;
				
				// compute diagonal dist
				int diagDist = distArr[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1);
				
				distArr[i][j] = min(hlDist, vtDist, diagDist);
			}
		}
		
		//printArray(distArr);
		//printEditSteps(distArr, s1, s2);
		return distArr[s1.length()][s2.length()];
	}
	
	private static void printEditSteps(int[][] distArr, String s1, String s2) {
		int i=distArr.length-1, j=distArr[0].length-1;
		char s1c[] = s1.toCharArray();
		StringBuffer sb = new StringBuffer(new String(s1c));
		while(true) {
			
			if(i == 0 || j == 0)
				break;
			
			if(s1.charAt(i-1) == s2.charAt(j-1)) {
				i--;j--;
			}
			// transformation in s1
			else if(distArr[i-1][j-1]+1 == distArr[i][j]) {
				System.out.print("Transform "+s1.charAt(i-1)+" in "+sb+" to "+s2.charAt(j-1)+" --> ");
				sb.replace(i-1, i, String.valueOf(s2.charAt(j-1)));
				System.out.println(sb);
				i--;j--;
			}
			// deletion in s1
			else if(distArr[i-1][j]+1 == distArr[i][j]) {
				System.out.print("Delete "+s1.charAt(i-1)+" in "+sb+" --> ");
				sb.deleteCharAt(i-1);
				System.out.println(sb);
				i--;
			}
			// addtion in s1
			else if(distArr[i][j-1]+1 == distArr[i][j]) {
				System.out.print("Add "+s2.charAt(j-1)+" to "+s1+" --> ");
				sb.insert(i, s2.charAt(j-1));
				System.out.println(sb);
				j--;
			}
		}
	}

	private static void printArray(int[][] distArr) {
		for(int i=0; i<distArr.length; i++) {
			for(int j=0; j<distArr[i].length; j++)
				System.out.print(distArr[i][j]+"  ");
			System.out.println();
		}
		
	}

	public static int findEditDistRecursive(String s1, String s2) {
		return minimumEditDistanceRecursive(s1, s2, s1.length(), s2.length());
	}
	
	public static List<String> getNearestWords_1(String w, int minDist) {
		//HashMap<Integer, List<String>> nearstMap = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		for(String s : getFileWords()) {
			int dist = minumEditDistanceDynamic(w, s);
			if(dist <= minDist)
				sb.append(s+",");
			/*
			 * List<String> wrdsWithDist = nearstMap.get(dist); if(wrdsWithDist == null)
			 * wrdsWithDist = new ArrayList<String>(); wrdsWithDist.add(s);
			 * nearstMap.put(dist, wrdsWithDist);
			 */
		}
		String[] arr = sb.toString().split(",");
		return Arrays.stream(arr, 0, arr.length)
				.collect(Collectors.toList());
	}
	
	public static List<String> getNearestWords(String w, int minDist) {
		return getFileWords().parallelStream()
						.filter(s -> minumEditDistanceDynamic(s, w) <= minDist)
						.collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		//System.out.println(minumEditDistanceDynamic("GTATGXYKJAHYTSYSYJSYSKJSYSUJSIUS","GTATGXYKHYTSYSKJSYSKJSYSUJSIKS"));
		
		  long s = System.currentTimeMillis();
		  System.out.println(getNearestWords("kran", 1)); long e =
		  System.currentTimeMillis(); System.out.println(((double) e-s));
		  System.out.println("-----------"); s = System.currentTimeMillis();
		  System.out.println(getNearestWords("ant", 1)); e =
		  System.currentTimeMillis(); System.out.println(((double) e-s));
		  
		  System.out.println("-----------"); s = System.currentTimeMillis();
		  System.out.println(getNearestWords("duc", 1)); e =
		  System.currentTimeMillis(); System.out.println(((double) e-s));
		 
        
	}

}
