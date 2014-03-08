package com.algorithms;

import java.util.Random;

public class BinaryHeap<Key extends Comparable<Key>> {

	private Key[] pq;
	private int N;

	public BinaryHeap(int capacity) {
		N = 0;
		pq = (Key[]) new Comparable[capacity + 1];
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	public void insert(Key key) {
		pq[++N] = key;
		swim(N);
	}

	public Key delMax() {
		Key key;
		key = pq[1];
		exch(1, N);
		del(N--);
		sink(1);
		return key;
	}

	public void del(int k) {
		pq[k] = null;
	}

	public void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k /= 2;
		}
	}

	public void sink(int k) {
		int j;
		while (2 * k <= N) {
			j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	public boolean less(int a, int b) {
		if (pq[a].compareTo(pq[b]) < 0)
			return true;
		return false;
	}

	public void exch(int i, int j) {
		Key temp;
		temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public static void main(String[] args) {
		BinaryHeap<Integer> bHeap = new BinaryHeap<>(20);
		Random rand = new Random();
		int key;
		
		for (int i = 0; i < 20; i++) {
			bHeap.insert(rand.nextInt(1000));
		}
		key = bHeap.delMax();
		System.out.println(key);
	}

}
