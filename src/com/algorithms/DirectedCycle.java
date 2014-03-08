package com.algorithms;

public class DirectedCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;

	public DirectedCycle(DirectedGraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		cycle = null;
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v);
	}

	private void dfs(DirectedGraph G, int v) {

		onStack[v] = true;
		marked[v] = true;

		for (int w : G.adj(v)) {
			if (this.hasCycle())
				return;
			else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
			else if (onStack[w]) {
				cycle = new Stack<Integer>();
				System.out.println("edgeTo[" + w +"]: " + edgeTo[w]);
				
				for (int i = v; i != w ; i = edgeTo[i]) {
					System.out.println("i = " + i);
					cycle.push(i);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		
		onStack[v] = false;

	}

	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle() {
		return cycle;
	}
	
	public static void main(String [] args) {
		DirectedGraph G;
		DirectedCycle dC;
		
		G = new DirectedGraph(5);
		
		G.addEdge(0, 1);
		G.addEdge(1, 2);
		G.addEdge(2, 3);
		G.addEdge(3, 4);
		G.addEdge(4, 0);
		
		dC = new DirectedCycle(G);
		
		System.out.println("Cycle present: " + dC.hasCycle());
		
		for (int i = 0; i < 5; i++)
			System.out.println("edgeTo[" + i + "]: " + dC.edgeTo[i]);
	
		System.out.println("Cycle: ");
		for (int v: dC.cycle())
			System.out.println(v);
		
	}

}
