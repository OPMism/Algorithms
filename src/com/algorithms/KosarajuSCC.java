package com.algorithms;

public class KosarajuSCC {

	private DepthFirstOrder dFO;
	private boolean[] marked;
	private int[] scc;
	private int count;

	public KosarajuSCC(DirectedGraph G) {
		dFO = new DepthFirstOrder(G.reverse());

		marked = new boolean[G.V()];
		scc = new int[G.V()];
		count = 0;

		for (int v : dFO.reversePostOrder()) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}

	public void dfs(DirectedGraph G, int v) {
		marked[v] = true;
		scc[v] = count;

		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public static void main(String[] args) {
		DirectedGraph G = new DirectedGraph(7);
		KosarajuSCC kS;

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

		kS = new KosarajuSCC(G);
		
		System.out.println("V = " + G.V());

	}

}
