package practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import practice.algo.MinHeap.HeapNode;

public class TopKWords {
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
        
        for(HeapNode node : mh.nodes)
        	tkw.add(node.word);
        return tkw;
	}
}

class MinHeap{
	int size = -1;
	int maxCap;
	HeapNode[] nodes;
	class HeapNode{
		String word;
		int freq;
		HeapNode(String word, int freq){
			this.word = word;
			this.freq = freq;
		}
	}
	
	public MinHeap(int maxCap) {
		this.maxCap = maxCap;
		nodes = new HeapNode[maxCap];
	}
	
	public void add(String word, int freq) {
		if(size+1 < maxCap) {
			HeapNode node = new HeapNode(word,freq);
			nodes[++size] = node;
			int parent = (size-1)/2;
			int curr = size;
			while(parent >=0 && nodes[curr].freq > nodes[parent].freq) {
				swap(parent, curr);
				curr = parent;
				parent = (parent-1)/2;
			}
		} else if(getRoot().freq < freq){
			deleteRoot();
			add(word, freq);
		}
		
	}
	
	public void deleteRoot() {
		HeapNode root = nodes[0];
		nodes[size] = root;
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
		if(lc < size && nodes[i].freq > nodes[lc].freq) {
			currPrnt = lc;
		}
		
		if(rc < size && nodes[i].freq > nodes[rc].freq) {
			currPrnt = rc;
		}
		
		if( currPrnt != i)
			swap(currPrnt, i);
		heapify(currPrnt, size);
		
	}

	private void swap(int parent, int curr) {
		HeapNode temp = nodes[curr];
		nodes[curr] = nodes[parent];
		nodes[parent] = temp;
		
	}
	
	public static void main(String[] args) {
		TopKWords tw = new TopKWords();
		List<String> tkw = tw.topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2);
		System.out.println(tkw);
	}
}
