package practice.algo;

import practice.algo.Graph.Vertex;

public class TestGraph {
	public static void main(String[] args) {
		// undirected cyclic graph
	/*	Graph g = new Graph();
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
		
	//	System.out.println(g);
		
		/*
		 * for(Vertex v : g.allVrtx.keySet()) System.out.println(v.label+" "+v.edges);
		 */
		//g.doDFSTraverse("England");
		//g.BFSIter(g.vrtxMap.get("Mumbai"), g.vrtxMap.get("NewYork"));
		//g.getShortestPath("Falaknuma", "Mumbai");
		
		
		// Directed AcyclicGraph
		/*
		Graph dag = new Graph();
		dag.addEdge("A", "D", true);
		dag.addEdge("B", "D", true);
		dag.addEdge("C", "E", true);
		dag.addEdge("D", "F", true);
		dag.addEdge("D", "E", true);
		dag.addEdge("F", "G", true);
		dag.addEdge("E", "G", true);
		dag.addEdge("E", "I", true);
		dag.addEdge("G", "H", true);
		dag.addEdge("G", "J", true);
		
		// add a cycle
	//	dag.addEdge("E", "B", true);
		
		System.out.println(dag);
		
		  for(Vertex v: dag.getVertexAllVertices().keySet()) {
		  System.out.println(v.getLabel()+" "+v.getEdgeSet()); }
		 
		
		dag.doTopologicOrdering(null); */
		// DAG with weights
		Graph dwg = new Graph();
		/* dwg.addEdge("A", "B", true, 3);
		dwg.addEdge("A", "C", true, 6);
		dwg.addEdge("B", "C", true, 4);
		dwg.addEdge("B", "D", true, 4);
		dwg.addEdge("B", "E", true, 11);
		dwg.addEdge("C", "D", true, 8);
		dwg.addEdge("C", "G", true, 11);
		dwg.addEdge("D", "E", true, -4);
		dwg.addEdge("D", "F", true, 5);
		dwg.addEdge("D", "G", true, 2);
		dwg.addEdge("E", "H", true, 9);
		dwg.addEdge("F", "H", true, 1);
		dwg.addEdge("G", "H", true, 2); */
		
		dwg.addEdge("A", "B", true, 4);
		dwg.addEdge("A", "C", true, 1);
		dwg.addEdge("C", "B", true, 2);
		dwg.addEdge("C", "D", true, 5);
		dwg.addEdge("B", "D", true, 1);
		dwg.addEdge("B", "E", true, 6);
		dwg.addEdge("D", "E", true, 3);
		
		for(Vertex v: dwg.getVertexAllVertices().keySet()) {
			  System.out.println(v.getLabel()+" "+v.getEdgeSet()); }
		dwg.dijkstrasShortestPaths("A", null);
		dwg.dijkstrasShortestPathsEagerImpl("A", "E");
		dwg.getShortestPath("A", "E");
		//dwg.getShortestPaths("A");
		
	}

}
