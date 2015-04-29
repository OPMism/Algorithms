package com.algorithms;
public class SeparateChainingHashST<Key, Value> {

	private int M; // Number of indexes in the hash map
	private int N;
	private static final int MIN_CAPACITY = 4;
	private SequentialSearchST<Key, Value>[] st;

	public SeparateChainingHashST() {
		this(MIN_CAPACITY);
	}

	public SeparateChainingHashST(int capacity) {
		M = capacity;
		st = new SequentialSearchST[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<>();
		N = 0;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void resize(int capacity) {
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(
				capacity);
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		st = temp.st;
		N = temp.N;
		M = capacity;
	}

	public void put(Key key, Value val) {
		int i = hash(key);
		if (N * 10 >= M)
			resize(2 * M);
		st[i].put(key, val);
		N++;
	}

	public Value get(Key key) {
		int i = hash(key);
		return st[i].get(key);
	}

	public void delete(Key key) {
		int i = hash(key);
		st[i].delete(key);
		N--;

		if (M > MIN_CAPACITY && N <= 2 * M)
			resize(M / 2);
	}

	public static void main(String[] args) {
		SeparateChainingHashST<Integer, Integer> map = new SeparateChainingHashST<>(
				5);

		for (int i = 0; i < 10; i++) {
			int key = (int) (Math.random() * 100);
			int val = (int) (Math.random() * 1000);
			map.put(key, val);
		}
		map.put(1, 20);
		map.put(6, 35);

		assert map.get(1) == 20;
		assert map.get(6) == 35;

	}

}
