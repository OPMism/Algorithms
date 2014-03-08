package com.algorithms;

public class BipartiteGraph {

	private boolean color[];
	private boolean marked[];
	private boolean bipartite;

	public BipartiteGraph(Graph G) {
		color = new boolean[G.V()];
		marked = new boolean[G.V()];
		bipartite = true;

		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				dfs(G, v);
		}
	}

	public void dfs(Graph G, int v) {
		marked[v] = true;

		for (int w : G.adj(v)) {
			if (!marked[w]) {
				color[w] = !color[v];
				dfs(G, w);
			} else if (color[w] == color[v]) {
				bipartite = false;
			}
		}
	}

	public boolean isBipartite() {
		return bipartite;
	}

	public static void main(String[] args) {
		Graph G = new Graph(9);
		BipartiteGraph bG;

		G.addEdge(0, 1);

		G.addEdge(1, 2);
		G.addEdge(1, 4);
		G.addEdge(1, 8);

		G.addEdge(2, 3);

		G.addEdge(3, 6);

		G.addEdge(4, 5);
		G.addEdge(4, 7);

		G.addEdge(7, 8);

		// G.addEdge(5, 2);

		// G.addEdge(6, 0);
		// G.addEdge(6, 4);

		// G.addEdge(6, 7);

		bG = new BipartiteGraph(G);

		System.out.println("Is the graph bipartite? " + bG.isBipartite());

	}

}
