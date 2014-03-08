package com.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraShortestPath {

	private DirectedEdge edgeTo[];
	private double distTo[];
	private boolean marked[];
	public Queue<Node> pq;
	public int V;

	public DijkstraShortestPath(EdgeWeightedDiGraph G, int source) {
		int i;
		int v;
		DirectedEdge edge;
		Node node;
		V = G.V();
		marked = new boolean[V];
		pq = new PriorityQueue<Node>(G.V(), new PriorityQueueComparator());

		distTo = new double[V];
		edgeTo = new DirectedEdge[V];
		for (i = 0; i < V; i++)
			distTo[i] = Double.POSITIVE_INFINITY;

		distTo[source] = 0;
		edgeTo[source] = null;

		node = new Node(source, 0.0);
		// marked[source] = true;
		pq.add(node);

		while (!pq.isEmpty()) {
			node = pq.poll();
			v = node.vertex();
			if (marked[v])
				continue;
			for (DirectedEdge e : G.adj(v)) {
				relax(e);
			}
			marked[v] = true;
		}

		System.out.println("Source vertex: " + source);
		for (i = 0; i < V; i++) {
			System.out.println("distTo[" + i + "] = " + distTo[i]);
		}

		for (i = 0; i < V; i++) {
			edge = edgeTo[i];
			if (edge != null) {
				System.out.println(edge.from() + " -> " + edge.to()
						+ "; distance: " + distTo[i]);
			}
		}

	}

	public void relax(DirectedEdge edge) {
		int v;
		int w;
		Node node;

		v = edge.from();
		w = edge.to();
		
		if (edge.weight() + distTo[v] < distTo[w]) {
			distTo[w] = distTo[v] + edge.weight();
			edgeTo[w] = edge;
			node = new Node(w, distTo[w]);
			pq.add(node);
		}

	}

	private class Node {
		private int v;
		private double weight;

		public Node(int v, double weight) {
			this.v = v;
			this.weight = weight;
		}

		public double weight() {
			return weight;
		}

		public int vertex() {
			return v;
		}
	}

	private class PriorityQueueComparator implements Comparator<Node> {

		@Override
		public int compare(Node arg0, Node arg1) {
			// TODO Auto-generated method stub
			if (arg0.weight() < arg1.weight())
				return -1;
			else if (arg0.weight() > arg1.weight())
				return 1;

			return 0;
		}

	}

	public static void main(String[] args) {
		EdgeWeightedDiGraph G = new EdgeWeightedDiGraph(8);
		DirectedEdge edge;

		G.addEdge(new DirectedEdge(0, 1, 5));
		G.addEdge(new DirectedEdge(0, 4, 9));
		G.addEdge(new DirectedEdge(0, 7, 8));
		G.addEdge(new DirectedEdge(1, 2, 12));
		G.addEdge(new DirectedEdge(1, 3, 15));
		G.addEdge(new DirectedEdge(1, 7, 4));
		G.addEdge(new DirectedEdge(2, 3, 3));
		G.addEdge(new DirectedEdge(2, 6, 11));
		G.addEdge(new DirectedEdge(3, 6, 9));
		G.addEdge(new DirectedEdge(4, 5, 4));
		G.addEdge(new DirectedEdge(4, 6, 20));
		G.addEdge(new DirectedEdge(4, 7, 5));
		G.addEdge(new DirectedEdge(5, 2, 1));
		G.addEdge(new DirectedEdge(5, 6, 13));
		G.addEdge(new DirectedEdge(7, 5, 6));
		G.addEdge(new DirectedEdge(7, 2, 7));

		DijkstraShortestPath dSP = new DijkstraShortestPath(G, 1);

	}

}
