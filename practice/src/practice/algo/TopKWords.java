package practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class TopKWords {
	public static void main(String[] args) {
		int i = (-3/2);
		System.out.println(i);
		TopKWords tw = new TopKWords();
		List<String> tkw = tw.topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 1);
		System.out.println(tkw);
	}
	public List<String> topKFrequent(String[] words, int k) {
		List<String> tkw = new ArrayList<String>();
        HashMap<String,Integer> wrdMap = new HashMap<>();
        for(String w : words){
            Integer freq = wrdMap.get(w);
            if(freq != null) {
            	wrdMap.put(w, ++freq);
            } else {
            	wrdMap.put(w, 1);
            }
        }
        
        MinHeap mh = new MinHeap(k);
        for(String wrd : wrdMap.keySet()) {
        	mh.add(wrd, wrdMap.get(wrd));
        }
        Arrays.sort(mh.nodes, (n1, n2) -> {
        	if (((HeapNode)n1).freq > ((HeapNode)n2).freq)
        		return -1;
        	else if (((HeapNode)n1).freq < ((HeapNode)n2).freq)
        		return 1;
        	return ((HeapNode)n1).word.compareTo((((HeapNode)n2).word));
        });
        for(HeapNode node : mh.nodes)
        	tkw.add(node.word);
       // tkw.sort((s1, s2)-> s1.compareTo(s2));
        return tkw;
	}
class HeapNode{
		String word;
		int freq;
		HeapNode(String word, int freq){
			this.word = word;
			this.freq = freq;
		}

		@Override
		public String toString() {
			return this.word+" - "+this.freq;
		}
	}
	
class MinHeap{
	int size = -1;
	int maxCap;
	HeapNode[] nodes;
	public MinHeap(int maxCap) {
		this.maxCap = maxCap;
		nodes = new HeapNode[maxCap];
	}
	
	public void add(String word, int freq) {
		if(size+1 < maxCap) {
			HeapNode node = new HeapNode(word,freq);
			HeapNode root = getRoot();
			nodes[++size] = node;
			int parent = (size-1)/2;
			int curr = size;
			while(parent >=0 && nodes[parent].freq > nodes[curr].freq ) {
				swap(parent, curr);
				curr = parent;
				parent = (parent-1)/2;
				if(parent == 0) parent--;
			}
		} else if(getRoot().freq < freq || (getRoot().freq == freq && word.compareTo(getRoot().word)<0)){
			deleteRoot();
			add(word, freq);
		} // if same frequency words keep word with alphabetical order
	//	else if()
		
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
		//nodes[i].freq > rc.freq
		int lc = (2*i)+1;
		int rc = (2*i)+2;
		int currPrnt = i;
		//HeapNode lc = nodes[(2*i)+1];
		//HeapNode rc = nodes[(2*i)+2];
		if(lc < size && nodes[currPrnt].freq > nodes[lc].freq) {
			currPrnt = lc;
		}
		
		if(rc < size && nodes[currPrnt].freq > nodes[rc].freq) {
			currPrnt = rc;
		}
		
		if( currPrnt != i) {
			swap(currPrnt, i);
			heapify(currPrnt, size);
		}
		
	}

	private void swap(int parent, int curr) {
		HeapNode temp = nodes[curr];
		nodes[curr] = nodes[parent];
		nodes[parent] = temp;
		
	}
	
}
}
