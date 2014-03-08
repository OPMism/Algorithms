package com.algorithms;

public class DirectedGraph {
	private Bag<Integer>[] adj;
	private final int V;

	public DirectedGraph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public int V() {
		return V;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public DirectedGraph reverse() {
		DirectedGraph Greverse;

		Greverse = new DirectedGraph(V);

		for (int v = 0; v < this.V; v++) {
			for (int w : adj[v]) {
				Greverse.addEdge(w, v);
			}
		}

		return Greverse;
	}

	public static void main(String[] args) {
		DirectedGraph dGraph = new DirectedGraph(10);

		dGraph.addEdge(0, 9);
		dGraph.addEdge(1, 8);

		return;

	}

}
