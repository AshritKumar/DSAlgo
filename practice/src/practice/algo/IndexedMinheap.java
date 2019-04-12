package practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.algo.TopKWords.MinHeap;

public class IndexedMinheap<T> {
	public IndexedMinheap() {
		allNodes = new ArrayList<>();
		posMap = new HashMap<>();
	}
	public IndexedMinheap(int size) {
		allNodes = new ArrayList<>(size);
		posMap = new HashMap<>();
	}
	private List<Node> allNodes ;
	private Map<T,Integer> posMap ;
	
	public void add(T data, int weight) {
		Node n = new Node(data, weight);
		allNodes.add(n);
		int curPos = allNodes.size() -1;
		int parent = (curPos - 1)/2;
		posMap.put(data, curPos);
		while(parent >= 0) {
			Node pNode = allNodes.get(parent);
			Node curNode = allNodes.get(curPos);
			if(pNode.weight > curNode.weight) {
				swap(parent,curPos);
				updatePosMap(curNode, pNode, curPos, parent);
				curPos = parent;
				parent = (parent - 1)/2;
				
			} else {
				break;
			}
		}
		
	}
	
	private void swap(int parent, int curPos) {
		Node temp = allNodes.get(curPos);
		allNodes.set(curPos, allNodes.get(parent));
		allNodes.set(parent, temp);
		
	}
	
	public void updatePosMap(Node curNode, Node pNode, int curPos, int parent) {
		posMap.put(pNode.key, curPos);
		posMap.put(curNode.key, parent);
	}
	
	public Node extractMin(){
		Node minNode = new Node(allNodes.get(0));
		swap(0, allNodes.size()-1);
		// remove the min from pos map and update the pos of last node(now the first node)
		posMap.remove(minNode.key);
		posMap.put(allNodes.get(0).key, 0);
		allNodes.remove(allNodes.size()-1);
		heapify(0);
		return minNode;
	}
	// decrease the weight of a given key
	public void decreaseKey(T key, int newWeight) {
		Integer nPos = posMap.get(key);
		if(nPos != null) {
			Node n = allNodes.get(nPos);
			n.weight = newWeight;
			// fix the heap up words
			int parent = (nPos - 1)/2;
			while(parent >=0) {
				if(allNodes.get(parent).weight > allNodes.get(nPos).weight) {
					updatePosMap(n, allNodes.get(parent), nPos, parent);
					swap(parent, nPos);
					nPos = parent;
					parent = (parent -1)/2;
				} else {
					break;
				}
			}
		}
		
	}

	private void heapify(int i) {
		int lc = 2*i + 1;
		int rc = 2*i + 2;
		int currentParent = i;
		if(lc < allNodes.size() && allNodes.get(currentParent).weight > allNodes.get(lc).weight) {
			currentParent = lc;
		} 
		if(rc < allNodes.size() && allNodes.get(currentParent).weight > allNodes.get(rc).weight) {
			currentParent = rc;
		}
		if(i != currentParent) {
			swap(currentParent, i);
			// update posMap
			updatePosMap(allNodes.get(currentParent), allNodes.get(i), i, currentParent);
			heapify(currentParent);
		}
		
	}
	
	public Node min() {
		return allNodes.get(0);
	}
	
	public Integer getWeight(T key) {
		Integer pos = posMap.get(key);
		if(pos == null)
			return null;
		return allNodes.get(pos).weight;
	}

	public class Node{
		T key;
		int weight;
		
		public Node(IndexedMinheap<T>.Node node) {
			this.key = node.key;
			this.weight = node.weight;
		}

		public Node(T key, int weight) {
			this.key = key;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return this.key+" - "+this.weight;
		}
	}
	
	public static void main(String[] args) {
		IndexedMinheap<String> mh = new IndexedMinheap<String>();
		mh.add("Ashrit" , 10);
		mh.add("Kumar" , 5);
		mh.add("Ram" , 21);
		mh.add("Kiran" , 7);
		mh.add("karan" , 6);
		mh.add("Vinay" , 16);
		
		//mh.extractMin();
		mh.decreaseKey("Ram", 1);
	}
	public boolean isEmpty() {
		return allNodes.isEmpty();
	}
	
	@Override
	public String toString() {
		return allNodes.toString();
	}

}
