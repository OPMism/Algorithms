package com.algorithms;

import java.util.Iterator;

public class GraphObject {

	private final int V;
	private Bag<Integer>[] adj;
	
	public GraphObject(int v) {
		this.V = v;
		adj = (Bag<Integer>[]) new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public int V() {
		return V;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	
	public static void main(String [] args) {
		GraphObject G = new GraphObject(10);
		
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 10; j++)
				G.addEdge(i, j);
		}
	}

}