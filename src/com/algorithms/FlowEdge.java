package com.algorithms;

public class FlowEdge implements Comparable<FlowEdge> {

	private int v;
	private int w;
	private double flow;
	private double capacity;

	public FlowEdge(int v, int w, double flow, double capacity) {
		this.v = v;
		this.w = w;
		this.flow = flow;
		this.capacity = capacity;
	}
	
	public int from() {
		return v;
	}
	
	public int to() {
		return w;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if (v == vertex)
			return this.w;
		else
			return this.v;
	}

	public double weight() {
		return flow;
	}

	public double capacity() {
		return capacity;
	}

	public double residualCapacityTo(int vertex) {
		if (v == vertex)
			return flow;
		else
			return capacity - flow;
	}
	
	public void addResidualFlowTo(int vertex, double delta) {
		if (v == vertex)
			flow -= delta;
		else
			flow += delta;
	}

	@Override
	// Do not need a compareto function
	public int compareTo(FlowEdge arg0) {
		// TODO Auto-generated method stub
		if (this.flow < arg0.flow)
			return -1;
		else if (this.flow > arg0.flow)
			return 1;
		return 0;
	}

}