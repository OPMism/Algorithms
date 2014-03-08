package com.algorithms;

public class BellmanFord {

	private double distTo[];
	private DirectedEdge edgeTo[];
	private int V;

	public BellmanFord(EdgeWeightedDiGraph G, int source) {
		DirectedEdge edge;
		
		V = G.V();

		distTo = new double[V];
		edgeTo = new DirectedEdge[V];

		for (int i = 0; i < V; i++)
			distTo[i] = Double.POSITIVE_INFINITY;

		distTo[source] = 0;
		edgeTo[source] = null;

		for (int i = 0; i < V; i++) {
			for (int v = 0; v < V; v++) {
				for (DirectedEdge e : G.adj(v)) {
					relax(e);
				}
			}
		}
		
		System.out.println("Source vertex: " + source);
		for (int i = 0; i < V; i++) {
			System.out.println("distTo[" + i + "] = " + distTo[i]);
		}

		for (int i = 0; i < V; i++) {
			edge = edgeTo[i];
			if (edge != null) {
				System.out.println(edge.from() + " -> " + edge.to()
						+ "; distance: " + distTo[i]);
			}
		}

	}

	public void relax(DirectedEdge edge) {
		int v;
		int w;

		v = edge.from();
		w = edge.to();

		if (distTo[v] + edge.weight() < distTo[w]) {
			distTo[w] = distTo[v] + edge.weight();
			edgeTo[w] = edge;
		}
	}
	
	public static void main(String[] args) {
		EdgeWeightedDiGraph G = new EdgeWeightedDiGraph(8);
		DirectedEdge edge;

		G.addEdge(new DirectedEdge(0, 1, 5));
		G.addEdge(new DirectedEdge(0, 4, 9));
		G.addEdge(new DirectedEdge(0, 7, 8));
		G.addEdge(new DirectedEdge(1, 2, 12));
		G.addEdge(new DirectedEdge(1, 3, 15));
		G.addEdge(new DirectedEdge(1, 7, 4));
		G.addEdge(new DirectedEdge(2, 3, 3));
		G.addEdge(new DirectedEdge(2, 6, 11));
		G.addEdge(new DirectedEdge(3, 6, 9));
		G.addEdge(new DirectedEdge(4, 5, 4));
		G.addEdge(new DirectedEdge(4, 6, 20));
		G.addEdge(new DirectedEdge(4, 7, 5));
		G.addEdge(new DirectedEdge(5, 2, 1));
		G.addEdge(new DirectedEdge(5, 6, 13));
		G.addEdge(new DirectedEdge(7, 5, 6));
		G.addEdge(new DirectedEdge(7, 2, 7));

		BellmanFord bF = new BellmanFord(G, 1);

	}

}
