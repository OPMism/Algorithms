package com.algorithms;

// Quic Union with Path Compression
public class QuickUnionPC {
	private int N;
	private int[] id;
	private int[] size;

	public QuickUnionPC(int N) {
		this.N = N;
		id = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}

	public int root(int p) {
		while (p != id[p]) {
			id[p] = id[id[p]];
			p = id[p];
		}

		return p;
	}

	public void connect(int p, int q) {
		int i = root(p);
		int j = root(q);

		if (size[i] > size[j]) {
			id[j] = i;
			size[i] += size[j];
		} else {
			id[i] = j;
			size[j] += size[i];
		}

		// id[i] = j;

	}

	public boolean connected(int p, int q) {

		return root(p) == root(q);
	}

	public static void main(String[] args) {
		QuickUnionPC qUPC = new QuickUnionPC(10);

		qUPC.connect(0, 1);
		qUPC.connect(1, 5);
		qUPC.connect(5, 9);
		qUPC.connect(9, 8);

		System.out.println("Is 9 connected to 1? " + qUPC.connected(9, 0));
	}
}
