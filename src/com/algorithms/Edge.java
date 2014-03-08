package com.algorithms;

public class Edge implements Comparable<Edge> {

	private int v;
	private int w;
	private double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int either() {
		return v;
	}

	public int other(int x) {
		if (x == v)
			return w;
		else
			return v;
	}
	
	public double weight() {
		return weight;
	}

	@Override
	public int compareTo(Edge arg0) {
		// TODO Auto-generated method stub
		if (this.weight < arg0.weight)
			return -1;
		else if (this.weight > arg0.weight)
			return 1;
		return 0;
	}

	public static void main(String[] args) {

	}

}