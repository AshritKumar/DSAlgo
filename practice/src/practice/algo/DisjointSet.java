package practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisjointSet<T> {
	private Map<T,Node> nodesMap = new HashMap<>();
	
	class Node{
		T data;
		Node parent;
		int rank;
		
		Node(T data){
			this.data = data;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.data+" - "+this.rank;
		}
	}
	
	// create a set with only one element
	public void makeSet(T data) {
		if(nodesMap.get(data) != null)
			return;
		Node n = new Node(data);
		n.parent = n;
		n.rank = 0;
		nodesMap.put(data, n);
	}
	
	// each set has a parent/representative. When unioning 2 sets we combine them and make parent which has highest rank as the new sets represenative 
	public void union(T d1, T d2) {
		Node n1 = nodesMap.get(d1);
		Node n2 = nodesMap.get(d2);
		// find the parents for two nodes
		Node p1 = findSet(n1);
		Node p2 = findSet(n2);
		//if both are already in same set
		if(p1 == p2)
			return;
		// parent nodes which has higher rank becomes new parent
		if(p1.rank >= p2.rank) {
			// we increase the rank only if both parent nodes have same rank, else the rank will be the highest rank
			p1.rank = p1.rank == p2.rank ? p1.rank + 1: p1.rank;
			//make p1 as parent of p1
			p2.parent = p1;
		} else {
			p1.parent = p2;
		}
	}
	
	public DisjointSet<T>.Node findSet(DisjointSet<T>.Node n1) {
		if(n1 == n1.parent)
			return n1;
		// while returning from the call stack, do a path compression. Assign the returned parent to all the intermediate nodes
		n1.parent = findSet(n1.parent);
		return n1.parent;
	}
	
	public DisjointSet<T>.Node findSet(T d1){
		return findSet(nodesMap.get(d1));
	}
	
	public void printSet() {
		Map<T,List<Node>> sm = new HashMap<>();
		for(T d : nodesMap.keySet()) {
			Node n = nodesMap.get(d);
			Node p = findSet(n);
			List<Node> nl = sm.get(p.data);
			if(nl == null) {
				nl = new ArrayList<>();
				nl.add(n);
				sm.put(p.data, nl);
			} else {
				nl.add(n);
				sm.put(p.data,nl);
			}
		}
		System.out.println(sm);
	}
	
	
	public static void main(String[] args) {
		DisjointSet<String> ds = new DisjointSet<>();
		ds.makeSet("A");
		ds.makeSet("B");
		ds.makeSet("C");
		ds.makeSet("D");
		ds.union("C", "D");
		ds.union("D", "A");
		
		ds.printSet();
		System.out.println(ds.findSet("A"));
	}
}
