package com.algorithms;

public class DepthFirstPaths {

	private boolean[] marked;
	private int edgeTo[];

	public DepthFirstPaths(DirectedGraph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			edgeTo[i] = -1;
		
		marked[s] = true;

		dfs(G, s);

	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public void dfs(DirectedGraph G, int v) {

		marked[v] = true;

		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}

	}

	public static void main(String[] args) {
		DirectedGraph G = new DirectedGraph(7);
		DepthFirstPaths dFP;

		G.addEdge(0, 2);
		G.addEdge(0, 5);
		G.addEdge(0, 1);

		G.addEdge(1, 4);

		G.addEdge(3, 2);
		G.addEdge(3, 4);
		G.addEdge(3, 5);
		G.addEdge(3, 6);

		G.addEdge(5, 2);

		G.addEdge(6, 0);
		G.addEdge(6, 4);
		
//		G.addEdge(6, 7);
		
		dFP = new DepthFirstPaths(G, 6);

		for (int i = 0; i < G.V(); i++) {
			System.out.println("Has path to " + i + "? : " + dFP.hasPathTo(i));
		}
	}

}
