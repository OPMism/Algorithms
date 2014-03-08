package com.algorithms;

public class IndexMinPQ<Key extends Comparable<Key>> {

	private int N;
	private int pq[];
	private int qp[];
	private Key keys[];

	public IndexMinPQ(int maxN) {
		N = 0;
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = (Key[]) new Comparable[maxN];
		for (int i = 0; i <= maxN; i++)
			qp[i] = -1;
	}

	public void insert(int k, Key key) {
		N++;
		pq[N] = k;
		qp[k] = N;
		keys[k] = key;
		swim(N);
	}

	public void deleteMin() {

	}

	public void delete(int k) {

	}

	public void change(int k, Key key) {

	}

	public void swim(int k) {
		int i;
		
		for (i = k / 2; i > 0; i /= 2) {
			if (less(i, k))
				break;
			exch(i, k);
			k = i;
		}

	}

	public void sink(int k) {
		int i;

		for (i = k * 2; i <= N; i *= 2) {
			if (less(i + 1, i))
				i++;
			if (less(k, i))
				break;
			exch(k, i);
			k = i;
		}

	}

	public boolean less(int i, int j) {

		int p, q;
		p = pq[i];
		q = pq[j];

		if (keys[p].compareTo(keys[q]) < 0)
			return true;

		return false;
	}

	public void exch(int i, int j) {
		int temp;
		temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		
//		temp = qp[i];
//		qp[i] = qp[j];
//		qp[j] = temp;
	}

	public static void main(String[] args) {
		IndexMinPQ<Integer> pq = new IndexMinPQ<>(10);
		
		pq.insert(0, 5);
		pq.insert(4, 3);
		pq.insert(2, 10);
		pq.insert(1, 2);
		
		pq.insert(6, 6);
	}

}
