package com.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

	private boolean marked[];
	private FlowEdge edgeTo[];
	private double value; // value of flow

	public FordFulkerson(FlowNetwork G, int s, int t) {

		double delta;

		value = 0;

		while (hasAugmentingPath(G, s, t)) {

			delta = Double.MAX_VALUE;

			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				delta = Math.min(delta, edgeTo[v].residualCapacityTo(v));
			}

			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, delta);
			}

			value += delta;
		}

	}

	public boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
		int v, w;
		int V;
		Queue<Integer> queue = new LinkedList<Integer>();
		V = G.V();

		marked = new boolean[V];
		edgeTo = new FlowEdge[V];

		queue.add(s);
		marked[s] = true;

		while (!queue.isEmpty()) {

			v = queue.poll();

			for (FlowEdge e : G.adj(v)) {
				w = e.other(v);

				if (e.residualCapacityTo(w) > 0 && !marked[w]) {
					marked[w] = true;
					edgeTo[w] = e;
					queue.add(w);
				}
			}

		}

		return marked[t];
	}

	public double value() {
		return value;
	}

	public boolean inCut(int v) {
		return marked[v];
	}

	public static void main(String[] args) {

		int V;
		FordFulkerson fordFulkerson;
		FlowEdge edge;
		FlowNetwork G;
		
		V = 6;
		
		G= new FlowNetwork(V);
		edge = new FlowEdge(0, 1, 0, 10);
		G.addEdge(edge);
		edge = new FlowEdge(0, 2, 0, 5);
		G.addEdge(edge);
		edge = new FlowEdge(1, 3, 0, 9);
		G.addEdge(edge);
		edge = new FlowEdge(1, 4, 0, 4);
		G.addEdge(edge);
		edge = new FlowEdge(2, 1, 0, 4);
		G.addEdge(edge);
		edge = new FlowEdge(2, 4, 0, 8);
		G.addEdge(edge);
		edge = new FlowEdge(3, 4, 0, 15);
		G.addEdge(edge);
		edge = new FlowEdge(3, 5, 0, 10);
		G.addEdge(edge);
		edge = new FlowEdge(4, 5, 0, 10);
		G.addEdge(edge);
		
		fordFulkerson = new FordFulkerson(G, 0, 5);
		
		System.out.println("Maximum flow in the network: " + fordFulkerson.value());
		
	}

}
