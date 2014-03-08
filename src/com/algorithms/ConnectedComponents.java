package com.algorithms;

public class ConnectedComponents {

	// private final int V;

	private int count;
	private int connected[];
	private boolean marked[];

	public ConnectedComponents(Graph G, int v) {
		connected = new int[G.V()];
		marked = new boolean[G.V()];
		count = 0;

		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
				count++;
			}
		}
	}

	public void dfs(Graph G, int v) {
		marked[v] = true;
		connected[v] = count;

		for (int i : G.adj(v)) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}

	}

}