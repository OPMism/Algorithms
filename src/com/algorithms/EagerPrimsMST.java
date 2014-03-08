package com.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EagerPrimsMST {

	private boolean[] marked;
	private int V;
	private PriorityQueue<Edge> pq;
	private PriorityQueue<Edge> mst;
	private Edge edgeTo[];
	private int distTo[];

	public EagerPrimsMST(EdgeWeightedGraph G) {
		pq = new PriorityQueue<>(G.V(), new PriorityQueueOrder());
		mst = new PriorityQueue<Edge>(G.V(), new PriorityQueueOrder());

		marked = new boolean[G.V()];
		edgeTo = new Edge[G.V()];
		distTo = new int[G.V()];

	}

	private class PriorityQueueOrder implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			if (o1.weight() < o2.weight())
				return -1;
			else if (o1.weight() > o2.weight())
				return 1;
			// TODO Auto-generated method stub
			return 0;
		}

	}
}
