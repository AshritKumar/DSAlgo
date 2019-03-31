package practice.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// Undirected graph and Directed graph
public class Graph {
	
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
		if(allVrtx.containsKey(v))
			return;
		else {
			allVrtx.put(v, new HashSet<>());
		}
	}
	
	public void addEdge(String s1, String s2, boolean isDirected) {
		Vertex v1 = new Vertex(s1);
		Vertex v2 = new Vertex(s2);
		addEdge(v1, v2, isDirected);
	}
	
	public void addEdge(Vertex v1, Vertex v2, boolean isDirected) {
		if(! allVrtx.containsKey(v1))
			allVrtx.put(v1, new HashSet<>());
		if(! allVrtx.containsKey(v2))
			allVrtx.put(v2, new HashSet<>());
		
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
		Vertex v = new Vertex(label);
		removeVertex(v);
	}

	public void removeVertex(Vertex v) {
		// remove vertex and its edges (adj vertx)
		if(allVrtx.containsKey(v)) {
			allVrtx.remove(v);
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
	
	private void removeEdge(String s1, String s2, boolean isDirected) {
		Vertex v1 = new Vertex(s1);
		Vertex v2 = new Vertex(s2);
		removeEdge(v1, v2, isDirected);
		
	}
	
	public void removeEdge(Vertex src,  Vertex dest, boolean isDirected) {
		allVrtx.get(src).remove(dest);
		Edge e = new Edge(src, dest, isDirected);
		src.edges.remove(e);
		if(! isDirected) {
			allVrtx.get(dest).remove(src);
			dest.edges.remove(e);
		}
		allEdges.remove(e);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Vertex v: allVrtx.keySet())
			sb.append(v).append(" => ").append(allVrtx.get(v)).append("\n");
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addEdge("Mumbai", "Pune", true);
		g.addEdge("Hyderabad", "Mumbai", true);
		g.addVertex("England");
		g.addEdge("Hyderabad", "England", false);
		g.addEdge("England", "Hyderabad", false);
		
		System.out.println(g);
		System.out.println(g.allEdges+"\n");
		
		g.removeEdge("Hyderabad", "England", false);
		
		System.out.println(g);
		System.out.println(g.allEdges);
		/*
		 * for(Vertex v : g.allVrtx.keySet()) { System.out.println(v.edges); }
		 */
		
	}

	

}
