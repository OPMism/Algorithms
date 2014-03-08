package com.algorithms;

// Topological Sort

public class DepthFirstOrder {
	private boolean[] marked;
	private Stack<Integer> reversePost; // Note: Using the internal stack class
										// which we wrote

	public DepthFirstOrder(DirectedGraph dGraph) {
		marked = new boolean[dGraph.V()];
		reversePost = new Stack<Integer>();

		for (int v = 0; v < dGraph.V(); v++) {
			if (marked[v])
				continue;
			dfs(dGraph, v);
		}

	}

	public void dfs(DirectedGraph G, int s) {

		marked[s] = true;

		for (int v : G.adj(s)) {
			if (marked[v])
				continue;
			dfs(G, v);
		}
		reversePost.push(s);
	}

	public Stack<Integer> reversePostOrder() {
		return reversePost;
	}

	public static void main(String[] args) {
		DirectedGraph dGraph = new DirectedGraph(7);
		DepthFirstOrder dFO;

		dGraph.addEdge(0, 2);
		dGraph.addEdge(0, 5);
		dGraph.addEdge(0, 1);

		dGraph.addEdge(1, 4);

		dGraph.addEdge(3, 2);
		dGraph.addEdge(3, 4);
		dGraph.addEdge(3, 5);
		dGraph.addEdge(3, 6);

		dGraph.addEdge(5, 2);

		dGraph.addEdge(6, 0);
		dGraph.addEdge(6, 4);

		dFO = new DepthFirstOrder(dGraph);

		System.out.println("V = " + dGraph.V());

		System.out.println("\nPostorder of the Graph: ");

		for (int v : dFO.reversePostOrder()) {
			System.out.println(v);
		}

		// System.out.println("\nTopological Sort of the Graph: ");
		// while (!dFO.reversePost.empty()) {
		// System.out.println(dFO.reversePost.pop());
		// }

	}

}
