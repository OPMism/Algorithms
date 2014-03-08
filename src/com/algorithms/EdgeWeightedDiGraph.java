package com.algorithms;

public class EdgeWeightedDiGraph {

	private int V;
	private int E;
	private Bag<DirectedEdge> adj[];

	public EdgeWeightedDiGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<DirectedEdge>();
		}
	}

	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}

	public void addEdge(DirectedEdge e) {
		int v, w;
		v = e.from();
		w = e.to();
		adj[v].add(e);
//		adj[w].add(e);
		E++;
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	public Iterable<DirectedEdge> edges() {
		int v = 0;
		Bag<DirectedEdge> edges = new Bag<DirectedEdge>();

		for (v = 0; v < V; v++) {
			for (DirectedEdge edge : adj[v]) {
				edges.add(edge);
			}
		}
		return edges;
	}

	public static void main(String[] args) {
		int i;
		EdgeWeightedDiGraph eGraph = new EdgeWeightedDiGraph(7);

	}

}
