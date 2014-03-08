package com.algorithms;

import java.util.PriorityQueue;

public class BreadthFirstSearch {

	private boolean marked[];
	private int s;
	private int edgeTo[];
	private int distTo[];

	public BreadthFirstSearch(Graph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];

		bfs(G, s);
	}

	public void bfs(Graph G, int s) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		int v;

		queue.add(s);
		marked[s] = true;
		distTo[s] = 0;

		while (!queue.isEmpty()) {
			v = queue.poll();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					queue.add(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}

	}

}
