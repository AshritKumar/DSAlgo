package practice.algo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public static void main(String[] args) {
		Set<String> dict = new HashSet<>();

		dict.add("pois");
		dict.add("soap");
		dict.add("pose");
		dict.add("soon");
		dict.add("noon");
		dict.add("soop");
		dict.add("poop");
		dict.add("poie");
		dict.add("poip");

		/*
		 * dict.add("hot"); dict.add("dot"); dict.add("dog"); dict.add("lot");
		 * dict.add("log"); dict.add("cog");
		 */

		int len = shortestChainLen("soap", "pose", dict);

		System.out.println("\n len = " + len);

	}

	private static int shortestChainLen(String source, String dest, Set<String> dict) {
		if (source.equals(dest))
			return 0;
		boolean pathFound = false;
		Queue<String> wq = new LinkedList<>();
		// push source first
		int len = 1;
		wq.add(source);
		dict.remove(source);
		System.out.print(source);
		while (!wq.isEmpty()) {
			String cur = wq.poll();
			Iterator<String> itr = dict.iterator();
			while (itr.hasNext()) {
				String w = itr.next();
				if (isAdjcent(cur, w)) {
					// if words are adjcent add it to queue and remove it from set, incerement count
					++len;
					wq.add(w);
					System.out.print(" -> " + w);
					if (dest.equals(w)) {
						pathFound = true;
						return len;
					}
					itr.remove();
					break;
				}
			}
		}
		return 0;
	}

	private static boolean isAdjcent(String cur, String w) {
		char[] w1 = cur.toCharArray();
		char[] w2 = w.toCharArray();
		int diffCount = 0;
		for (int i = 0; i < w1.length; i++) {
			if (w1[i] != w2[i])
				diffCount++;
		}
		if (diffCount == 1)
			return true;
		return false;
	}

}
