package com.algorithms;

import java.util.Stack;

public class DepthFirstSearch {
	private boolean marked[];
	private int edgeTo[];
	private int s;

	public DepthFirstSearch(Graph G, int s) {
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];

		dfs(G, s);
	}

	public void dfs(Graph G, int v) {
		marked[v] = true;

		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(Graph G, int w) {
		Stack<Integer> path;
		if (!hasPathTo(w))
			return null;
		path = new Stack<>();
		for (int i = w; i != s; i = edgeTo[i]) {
			path.push(i);
		}
		path.push(s);
		return path;
	}
}