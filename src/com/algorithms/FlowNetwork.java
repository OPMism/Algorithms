package com.algorithms;

public class FlowNetwork {

	private int V;
	private int E;
	private Bag<FlowEdge> adj[];

	public FlowNetwork(int V) {
		int i;
		adj = (Bag<FlowEdge>[]) new Bag[V];

		this.V = V;
		E = 0;

		for (i = 0; i < V; i++) {
			adj[i] = new Bag<FlowEdge>();
		}
	}

	public void addEdge(FlowEdge e) {
		int v;
		int w;
		v = e.from();
		w = e.to();

		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public Iterable<FlowEdge> adj(int v) {
		return adj[v];
	}

	// Each edge is returned twice
	public Iterable<FlowEdge> edges() {
		int v;
		Bag<FlowEdge> edges = new Bag<FlowEdge>();

		for (v = 0; v < V; v++) {
			for (FlowEdge e : adj(v))
				edges.add(e);
		}

		return edges;
	}

}
