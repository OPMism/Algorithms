package com.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST {

	private Queue<Edge> mst;
	private PriorityQueue<Edge> pq;
	private boolean[] marked;

	public LazyPrimMST(EdgeWeightedGraph G) {
		int v, w;
		Edge e;

		marked = new boolean[G.V()];
		mst = new PriorityQueue<Edge>();
		pq = new PriorityQueue<Edge>(G.V(), new PriorityQueueComparator());

		visit(G, 0);

		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			e = pq.remove();
			v = e.either();
			w = e.other(v);
			if (marked[v] && marked[w])
				continue;
			mst.add(e);
			if (!marked[v])
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}

	}

	private void visit(EdgeWeightedGraph G, int v) {

		marked[v] = true;

		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)]) {
				pq.add(e);
			}
		}

	}

	private class PriorityQueueComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge arg0, Edge arg1) {
			// TODO Auto-generated method stub
			if (arg0.weight() - arg1.weight() < 0)
				return -1;
			else if (arg0.weight() - arg1.weight() > 0)
				return 1;
			return 0;
		}

	}
	
	public static void main(String [] args) {
		
		Edge edge;
		EdgeWeightedGraph G = new EdgeWeightedGraph(8);
		LazyPrimMST mLazyPrimMST;
		
		edge = new Edge(0, 7, 0.16);
		G.addEdge(edge);
		G.addEdge(new Edge(2, 3, 0.17));
		G.addEdge(new Edge(1, 7, 0.19));
		G.addEdge(new Edge(0, 2, 0.26));
		G.addEdge(new Edge(5, 7, 0.28));
		G.addEdge(new Edge(1, 3, 0.29));
		G.addEdge(new Edge(1, 5, 0.32));
		G.addEdge(new Edge(2, 7, 0.34));
		G.addEdge(new Edge(4, 5, 0.35));
		G.addEdge(new Edge(1, 2, 0.36));
		G.addEdge(new Edge(4, 7, 0.37));
		G.addEdge(new Edge(0, 4, 0.38));
		G.addEdge(new Edge(6, 2, 0.40));
		G.addEdge(new Edge(3, 6, 0.52));
		G.addEdge(new Edge(6, 0, 0.58));
		G.addEdge(new Edge(6, 4, 0.93));
		
		mLazyPrimMST = new LazyPrimMST(G);
		
		return;
		
	}

}
