package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

// Undirected graph and Directed graph
public class Graph {
	private Map<String,Vertex> vrtxMap = new HashMap<>();
	private Map<Vertex, Set<Vertex>> allVrtx = new HashMap<>();
	private Set<Edge> allEdges = new HashSet<>();
	
	class Vertex{
		private String label;
		private Set<Edge> edges = new HashSet<>();
		public Vertex(String label) {
			this.label = label;
		}
		
		public void addEdge(Vertex dest, boolean isDirected) {
			addEdge(dest, 0.0d, isDirected);
		}
		
		public void addEdge(Vertex dest, double weight, boolean isDircected) {
			Edge e = new Edge(this, dest, weight, isDircected);
			this.edges.add(e);
			Graph.this.allEdges.add(e); allEdges.contains(e);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(label);
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Vertex))
				return false;
			Vertex other = (Vertex) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(label, other.label);
		}
		
		@Override
		public String toString() {
			return this.label;
		}

		private Graph getEnclosingInstance() {
			return Graph.this;
		}
	}
	
	class Edge{
		Vertex v1;
		Vertex v2;
		double weight;
		boolean isDirected;
		public Edge(Vertex v1, Vertex v2, boolean isDirected) {
			this.v1 = v1;
			this.v2 = v2;
			this.isDirected = isDirected;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			if(! isDirected)
				result = prime * result + Objects.hash(isDirected, v1, v2, weight) + Objects.hash(isDirected, v2, v1, weight);
			else
				result = prime * result + Objects.hash(isDirected, v1, v2, weight);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Edge))
				return false;
			Edge other = (Edge) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if(! isDirected)
				return (Objects.equals(v1, other.v1) || Objects.equals(v1, other.v2)) && (Objects.equals(v2, other.v1) || Objects.equals(v2, other.v2))
						&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
			return isDirected == other.isDirected && Objects.equals(v1, other.v1) && Objects.equals(v2, other.v2)
					&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
		}

		public Edge(Vertex v1, Vertex v2, double weight, boolean isDirected) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
			this.isDirected = isDirected;
		}

		private Graph getEnclosingInstance() {
			return Graph.this;
		}
		
		@Override
		public String toString() {
			return v1.label+ (isDirected ? " ----> ": " ------ ")+ v2.label;
		}
		
	}
	
	public void addVertex(String label) {
		Vertex v = new Vertex(label);
		addVertex(v);
	}
	
	public void addVertex(Vertex v) {
		if(allVrtx.containsKey(v.label))
			return;
		else {
			vrtxMap.put(v.label, v);
			allVrtx.put(v, new HashSet<>());
		}
	}
	
	public void addEdge(String s1, String s2, boolean isDirected) {
		Vertex v1 = null;
		Vertex v2 = null;
		if(! vrtxMap.containsKey(s1)) {
			v1 = new Vertex(s1);
			allVrtx.put(v1, new HashSet<>());
			vrtxMap.put(v1.label, v1);
		} else {
			v1 = vrtxMap.get(s1);
		}
		if(! vrtxMap.containsKey(s2)) {
			v2 = new Vertex(s2);
			allVrtx.put(v2, new HashSet<>());
			vrtxMap.put(v2.label, v2);
		} else {
			v2 = vrtxMap.get(s2);
		}
		addEdge(v1, v2, isDirected);
	}
	
	public void addEdge(Vertex v1, Vertex v2, boolean isDirected) {
	
		// add v2 as v1 adj vrtx and edge
		allVrtx.get(v1).add(v2);
		v1.addEdge(v2, isDirected);
		if(! isDirected) {
			// add v1 as v2 adj vrtx and edge
			allVrtx.get(v2).add(v1);
			v2.addEdge(v1, isDirected);
		}
	}
	
	public void removeVertex(String label) {
		removeVertex(vrtxMap.get(label));
	}

	public void removeVertex(Vertex v) {
		// remove vertex and its edges (adj vertx)
		if(v!= null && vrtxMap.containsKey(v.label)) {
			allVrtx.remove(v);
			// remove from vertex map
			vrtxMap.remove(v.label);
			v.edges = null;
			
			// remove V from any of the other directed edges
			Iterator<Edge> edgeItr = allEdges.iterator();
			while(edgeItr.hasNext()) {
				boolean containsVrtx = false;
				Edge e = edgeItr.next();
				if(e.v1.equals(v)) {
					// remove edge V --> v2 from v2 edge set
					e.v2.edges.remove(e);
					// remove V from v2's adj vrtx set
					allVrtx.get(e.v2).remove(v);
					containsVrtx = true;
				}
				if(e.v2.equals(v)) {
					// remove edge V --> v1 from v1 edge set
					e.v1.edges.remove(e);
					// remove V from v1's adj vrtx set
					allVrtx.get(e.v1).remove(v);
					containsVrtx = true;
				}
				// if any of the edge has V remove that edge from 
				if(containsVrtx)
					edgeItr.remove();
			}
		}
		
	}
	
	public void removeEdge(String s1, String s2, boolean isDirected) {
		removeEdge(vrtxMap.get(s1), vrtxMap.get(s2), isDirected);
		
	}
	
	public void removeEdge(Vertex src,  Vertex dest, boolean isDirected) {
		Edge e = new Edge(src, dest, isDirected);
		if(allEdges.contains(e)) {
			allVrtx.get(src).remove(dest);
			src.edges.remove(e);
			if(! isDirected) {
				allVrtx.get(dest).remove(src);
				dest.edges.remove(e);
			}
			allEdges.remove(e);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Vertex v: allVrtx.keySet())
			sb.append(v.label).append(" => ").append(allVrtx.get(v)).append("\n");
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addEdge("Mumbai", "Pune", false);
		g.addEdge("Mumbai", "Hyderabad", false);
		
		g.addEdge("Hyderabad", "England", false);
		
		g.addEdge("England", "Pune", false);
		g.addEdge("England", "Falaknuma", false);
		
		g.addEdge("Pune", "NewYork", false);
		
		g.addEdge("NewYork", "Malakpet", false);
		
		g.addEdge("Malakpet", "Mumbai", false);
		g.addEdge("Malakpet", "Pune", false);
		g.addEdge("Malakpet", "Falaknuma", false);
		
		// disconnected parts
		
		g.addEdge("Mouto", "Kerguelen Islands", false);
		g.addEdge("Mouto", "Pitcairn Islands", false);
		
		g.addEdge("Yangon", "Port Blair", false);
		g.addEdge("Port Blair", "Interview Island", false);
		g.addEdge("Interview Island","Subashgram", false);
		
		System.out.println(g);
		
		/*
		 * for(Vertex v : g.allVrtx.keySet()) System.out.println(v.label+" "+v.edges);
		 */
		//g.doDFSTraverse("England");
		//g.BFSIter(g.vrtxMap.get("Mumbai"), g.vrtxMap.get("NewYork"));
		g.getShortestPath("Falaknuma", "Mumbai");
		
	}
	
	public void doDFSTraverse(String vertex) {
		//DFSRecursive(vrtxMap.get(vertex), new HashSet<String>());
		DFSIterative(vrtxMap.get(vertex));
		System.out.println();
	}
	public void DFSRecursive(Vertex v, Set<String> visited) {
		visited.add(v.label);
		System.out.print(v.label+" ---> ");
		for(Vertex av : allVrtx.get(v)) {
			if(! visited.contains(av.label)) {
				visited.add(av.label);
				DFSRecursive(av, visited);
			}
		}
	}
	
	public void DFSIterative(Vertex v) {
		Set<String> visited = new HashSet<String>();
		Stack<Vertex> stack = new Stack<>();
		stack.push(v);
		visited.add(v.label);
		System.out.print(v.label);
		while(! stack.isEmpty()) {
			Vertex vrt = stack.pop();
			// push all the adj nodes of v into stack
			for(Vertex avx : allVrtx.get(vrt)) {
				if(! visited.contains(avx.label)) {
					System.out.print(" ----> "+avx.label);
					stack.push(avx);
					visited.add(avx.label);
				}
			}
		}
	}
	
	// to traverse even disconnected components
	public void DeepDFS() {
		// to count no of disconnected components, works correctly only for undirected graph(if there are disconnected componnets)
		int count = 0;
		Set<String> visited = new HashSet<String>();
		// iterate through all vertices
		for(Vertex vr : allVrtx.keySet()) {
			if(! visited.contains(vr.label)) {
			//	visited.add(vr.label);
				DFSRecursive(vr, visited);
				System.out.println();
				++count;
			}
		}
		//System.out.println(count);
	}
	
	private List<Object[]> BFSIter(Vertex v, Vertex vd) {
		// list for shortest path
		List<Object[]> pathList = new ArrayList<>();
		// to count no. of levels
		int level = 0;
		LinkedList<Vertex> queue = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		queue.add(v);
		queue.add(null);
		visited.add(v.label);
		System.out.print(v.label+"("+level+")");
		pathList.add(new Object[] {v, level});
		++level;
		while(! queue.isEmpty()) {
			Vertex vr = queue.poll();
			//System.out.println(" -  "+level);
			if(vr != null) {
				for(Vertex avx : allVrtx.get(vr)) {
					if(! visited.contains(avx.label)) {
						queue.add(avx);
						visited.add(avx.label);
						System.out.print(" ----> "+avx.label+"("+level+")");
						// add to path list to construct the shortest path
						pathList.add(new Object[] {avx, level});
						if(avx.label.equals(vd != null ? vd.label : null))
							return pathList;
					}
				}
			} else if(! queue.isEmpty()){
				// if encountered a null it means one level is completed
				++level;
				// add null at end to mark for another level
				queue.add(null);
			}
		}
		return pathList;
	}
	
	public List<String> getShortestPath(String src, String dstn) {
		LinkedList<String> actualPath = new LinkedList<>();
		Vertex dstnVrtx = vrtxMap.get(dstn);
		Vertex srcVrtx = vrtxMap.get(src);
		//actualPath.add(src);
		if(! src.equals(dstn) && dstnVrtx != null && srcVrtx != null) {
			List<Object[]> pathList = BFSIter(srcVrtx, dstnVrtx);
			int prevLvl = (int) pathList.get(pathList.size()-1)[1];
			//System.out.print("\n"+pathList.get(pathList.size()-1)[0]);
			actualPath.push(pathList.get(pathList.size()-1)[0].toString());
			for(int i=pathList.size()-2; i>=0; i--) {
				Vertex v = (Vertex)pathList.get(i)[0];
				int lvl = (int) pathList.get(i)[1];
				if(allVrtx.get(dstnVrtx).contains(v) && lvl != prevLvl) {
					//System.out.print(" ---> "+v.label);
					actualPath.push(v.label);
					prevLvl = lvl;
					dstnVrtx = v;
				}
			}
			if(! dstnVrtx.equals(srcVrtx)) {
				System.err.println("\nNo Path Found");
				return null;
			}
			System.out.println("\n"+actualPath);
			return actualPath;
		}
		System.err.println("Invalid vertices given");
		return null;
	}
	
	public List<Object[]> doBfs(String src) {
		return BFSIter(vrtxMap.get(src), null);
	}

}