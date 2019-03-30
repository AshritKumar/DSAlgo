package practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class TopKWords {
	public static void main(String[] args) {
		int i = (-3 / 2);
		System.out.println(i);
		TopKWords tw = new TopKWords();
		List<String> tkw = tw.topKFrequent(new String[] { "rid","hfivrcye","ultm","rid","souxu","zzafskckbv","ultm","lbg","zzafskckbv","ultm","qgbvcr","tykxjmz","zzafskckbv","sym","ihuyibxse","eowbkcme","ultm","mgurdprp","ocltjplwzk","suqgmxaqga","hvyffp","suqgmxaqga","ihuyibxse","qgbvcr","mgurdprp","sym","arfhostizi","qgbvcr","hfivrcye","souxu","rid","uafvqaxh","tykxjmz","zzafskckbv","zzafskckbv","gitte","zzafskckbv","uafvqaxh","suqgmxaqga","qgbvcr","zzafskckbv","suqgmxaqga","souxu","ultm","eowbkcme","gitte","souxu","qgbvcr","hvyffp","souxu","ihuyibxse","suqgmxaqga","kvjrlz","tykxjmz","suqgmxaqga","zzafskckbv","gitte","suqgmxaqga","sym","rid","gitte","mgurdprp","rid","ihuyibxse","souxu","ihuyibxse","ocltjplwzk","gitte","uafvqaxh","qgbvcr","ocltjplwzk","ihuyibxse","wiflv","gitte","souxu","hvyffp","zzafskckbv","eowbkcme","mgurdprp","rid","ocltjplwzk","ocltjplwzk","sym","wiflv","tykxjmz","hvyffp","hfivrcye","kvjrlz","hvyffp","ultm","zzafskckbv","qgbvcr","gitte","gitte","tykxjmz","tykxjmz","tykxjmz","sym","ocltjplwzk","ultm","ultm","souxu" }, 3);
		System.out.println(tkw);
	}

	public List<String> topKFrequent(String[] words, int k) {
		List<String> tkw = new ArrayList<String>();
		HashMap<String, Integer> wrdMap = new HashMap<>();
		for (String w : words) {
			Integer freq = wrdMap.get(w);
			if (freq != null) {
				wrdMap.put(w, ++freq);
			} else {
				wrdMap.put(w, 1);
			}
		}

		MinHeap mh = new MinHeap(k);
		for (String wrd : wrdMap.keySet()) {
			mh.add(wrd, wrdMap.get(wrd));
		}

		
		  Arrays.sort(mh.nodes, (n1, n2) -> { 
			  if (((HeapNode)n1).freq >((HeapNode)n2).freq) return -1;
			  else if (((HeapNode)n1).freq < ((HeapNode)n2).freq) return 1; 
			  return ((HeapNode)n1).word.compareTo((((HeapNode)n2).word)); });
		  
		for (HeapNode node : mh.nodes)
			tkw.add(node.word);
	//	 Collections.reverse(tkw);
		// tkw.sort((s1, s2)-> s1.compareTo(s2));
		return tkw;
	}

	class HeapNode implements Comparable<HeapNode> {
		String word;
		int freq;

		HeapNode(String word, int freq) {
			this.word = word;
			this.freq = freq;
		}

		@Override
		public String toString() {
			return this.word + " - " + this.freq;
		}

		@Override
		public int compareTo(HeapNode n) {
			// here if frequencies are equal we write n.word.compareTo(this.word) instead of this.word.compareTo(n.word) because,
			// we wanted to keep the object 
			return this.freq == n.freq ? n.word.compareTo(this.word) : this.freq - n.freq;
		}

	}

	class MinHeap {
		int size = -1;
		int maxCap;
		HeapNode[] nodes;

		public MinHeap(int maxCap) {
			this.maxCap = maxCap;
			nodes = new HeapNode[maxCap];
		}

		public void add(String word, int freq) {
			HeapNode node = new HeapNode(word, freq);
			if (size + 1 < maxCap) {
				nodes[++size] = node;
				int parent = (size - 1) / 2;
				int curr = size;
				//System.out.println(nodes[parent]+" , "+nodes[curr]+ " - "+nodes[parent].compareTo(nodes[curr]));
				while (parent >= 0 && nodes[parent].compareTo(nodes[curr]) > 0) {
					swap(parent, curr);
					curr = parent;
					parent = (parent - 1) / 2;
					/*
					 * if (parent == 0) parent--;
					 */
				}
			} else if (getRoot().compareTo(node)< 0) {
				deleteRoot();
				add(word, freq);
			} // if same frequency words keep word with alphabetical order
			System.out.print(word+" => ");
			printNodes(nodes);

		}

		public void deleteRoot() {
			HeapNode root = nodes[0];
			nodes[0] = nodes[size];
			--size;
			heapify(0, size);
		}

		public HeapNode getRoot() {
			return nodes[0];
		}

		private void heapify(int i, int size) {
			// nodes[i].freq > rc.freq
			int lc = (2 * i) + 1;
			int rc = (2 * i) + 2;
			int currPrnt = i;
			// HeapNode lc = nodes[(2*i)+1];
			// HeapNode rc = nodes[(2*i)+2];
			if (lc <= size && nodes[currPrnt].compareTo(nodes[lc]) > 0) {
				currPrnt = lc;
			}

			if (rc <= size && nodes[currPrnt].compareTo(nodes[rc]) > 0) {
				currPrnt = rc;
			}

			if (currPrnt != i) {
				swap(currPrnt, i);
				heapify(currPrnt, size);
			}

		}

		private void swap(int parent, int curr) {
			HeapNode temp = nodes[curr];
			nodes[curr] = nodes[parent];
			nodes[parent] = temp;

		}
		
		private void printNodes(HeapNode[] nodes){
			System.out.print("["+nodes[0]);
			for(int i=1; i<nodes.length; i++)
				System.out.print(", "+nodes[i]);
			System.out.print("]\n");
		}

	}
}
