package com.random;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	private int totalCost;
	private int numEdges;
	private boolean marked[];

	public Solution(int V) {
		totalCost = Integer.MAX_VALUE;
		numEdges = 1;
		marked = new boolean[V];
	}

	public static void main(String[] args) throws IOException {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		int i, j;
		int V;
		int weight;
		StringTokenizer st;
		EdgeWeightedDigraph G;
		Solution Gsol;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		V = Integer.parseInt(line);
		G = new EdgeWeightedDigraph(V);
		Gsol = new Solution(V);

		for (i = 0; i < V; i++) {
			j = 0;
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				weight = Integer.parseInt(st.nextToken());
				if (i != j)
					G.addEdge(new DirectedEdge(i, j, weight));
				j++;
			}
		}

		for (i = 0; i < V; i++) {
			Gsol.visit(G, i, i, 0, 0, true);
		}

		int gcd = GCD(Gsol.totalCost, Gsol.numEdges);

		System.out
				.println((Gsol.totalCost / gcd) + "/" + (Gsol.numEdges / gcd));

	}

	public static int GCD(int a, int b) {
		if (b == 0)
			return a;
		return GCD(b, a % b);
	}

	public void visit(EdgeWeightedDigraph G, int source, int v, int cost,
			int edges, boolean first) {

		if (marked[v]) {
			if (source == v) {
				double ratio = (1.0 * cost / (1.0 * edges));
				double lastRatio = (1.0 * totalCost / (1.0 * numEdges));
				if (ratio < lastRatio) {
					totalCost = cost;
					numEdges = edges;
				}
			}
			return;
		}
		marked[v] = true;

		for (DirectedEdge dEdge : G.adj(v)) {
			int w = dEdge.to();
			visit(G, source, w, cost + dEdge.weight(), edges + 1, false);
		}
		
		marked[v] = false;
	}
}

class Bag<Item> implements Iterable<Item> {

	private Node first;
	private int N;

	private class Node {
		private Item item;
		private Node next;
	}

	public Bag() {
		first = null;
		N = 0;
	}

	public void add(Item w) {
		Node oldfirst = first;
		first = new Node();
		first.item = w;
		first.next = oldfirst;
		N++;
	}

	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}
}

class DirectedEdge {
	private final int v;
	private final int w;
	private final int weight;

	public DirectedEdge(int v, int w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int weight() {
		return weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}
}

class EdgeWeightedDigraph {
	private final int V;
	private int E;
	private Bag<DirectedEdge>[] adj;

	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<DirectedEdge>();
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	public Iterable<DirectedEdge> edges() {
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				bag.add(e);
		return bag;
	}
}