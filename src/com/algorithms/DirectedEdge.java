package com.algorithms;

public class DirectedEdge implements Comparable<DirectedEdge> {

	private int v;
	private int w;
	private double weight;

	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}

	public double weight() {
		return weight;
	}

	@Override
	public int compareTo(DirectedEdge arg0) {
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