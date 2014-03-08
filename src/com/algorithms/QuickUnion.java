package com.algorithms;

public class QuickUnion {
	private int N;
	private int[] id;
	private int[] size;

	public QuickUnion(int N) {
		this.N = N;
		id = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 0;
		}
	}

	public int root(int p) {
		while (p != id[p])
			p = id[p];

		return p;
	}

	public void connect(int p, int q) {
		p = root(p);
		q = root(q);

		id[p] = q;

	}

	public boolean connected(int p, int q) {

		p = root(p);
		q = root(q);

		return p == q;
	}
	
	public static void main (String [] args) {
		QuickUnion qU = new QuickUnion(10);
		
		qU.connect(0, 1);
		qU.connect(1, 5);
		qU.connect(5, 9);
		qU.connect(9, 8);
		
		System.out.println("Is 9 connected to 1? " + qU.connected(9, 0));
	}
}
