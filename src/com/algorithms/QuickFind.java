package com.algorithms;

public class QuickFind {

	private int N;
	private int id[];

	public QuickFind(int N) {
		this.N = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	public void connect(int p, int q) {
		if (connected(p, q))
			return;
		for (int i = 0; i < N; i++) {
			if (id[i] == id[p])
				id[i] = id[q];
		}
	}
	
	public static void main (String [] args) {
		QuickFind qF = new QuickFind(10);
		
		qF.connect(0, 1);
		qF.connect(1, 5);
		qF.connect(9, 0);
		
		System.out.println("Is 9 connected to 1? " + qF.connected(9, 0));
	}

}
