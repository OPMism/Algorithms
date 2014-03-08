package com.algorithms;

public class EdgeWeightedGraph {

	private int V;
	private int E;
	private Bag<Edge> adj[];

	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Edge>();
		}
	}

	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}

	public void addEdge(Edge e) {
		int v, w;
		v = e.either();
		w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	public Iterable<Edge> edges() {
		int v = 0;
		Bag<Edge> edges = new Bag<Edge>();

		for (v = 0; v < V; v++) {
			for (Edge edge : adj[v]) {
				edges.add(edge);
			}
		}
		return edges;
	}

	public static void main(String[] args) {
		int i;
		EdgeWeightedGraph eGraph = new EdgeWeightedGraph(7);

	}

}
