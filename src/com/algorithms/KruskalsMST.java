package com.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalsMST {

	private int V;
	private int E;
	private PriorityQueue<Edge> pq;
	private Queue<Edge> mst;
	private QuickUnionPC qU;

	public KruskalsMST(EdgeWeightedGraph G) {
		Edge e;
		int v;
		int w;
		V = G.V();
		E = G.E();
		mst = new PriorityQueue<Edge>();
		pq = new PriorityQueue<Edge>(E, new PriorityQueueComparator());
		qU = new QuickUnionPC(V);

		populateQueue(G);

		while (!pq.isEmpty() && mst.size() < V - 1) {
			e = pq.remove();
			v = e.either();
			w = e.other(v);

			if (!qU.connected(v, w)) {
				qU.connect(v, w);
				mst.add(e);
			}

		}

	}

	public void populateQueue(EdgeWeightedGraph G) {
		Iterable<Edge> edge;

		edge = G.edges();
		for (Edge e : edge) {
			pq.add(e);
		}

	}

	private class PriorityQueueComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			// TODO Auto-generated method stub
			if (o1.weight() < o2.weight())
				return -1;
			else if (o1.weight() > o2.weight())
				return 1;
			return 0;
		}

	}
	
	public static void main(String [] args) {
		
		Edge edge;
		EdgeWeightedGraph G = new EdgeWeightedGraph(8);
		KruskalsMST kMST;
		
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
		
		kMST = new KruskalsMST(G);
		
		return;
		
	}

}
