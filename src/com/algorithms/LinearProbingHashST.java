package com.algorithms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LinearProbingHashST<Key, Value> {

	private int M; // Capacity of the hash table
	private int N; // Number of entries in hash table
	Key[] keys;
	Value[] values;

	public LinearProbingHashST(int initialCapacity) {
		this.M = initialCapacity;
		this.N = 0;
		keys = (Key[]) (new Object[M]);
		values = (Value[]) (new Object[M]);
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

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public void put(Key key, Value val) {
		int i;

		if (val == null) {
			delete(key);
			return;
		}

		if (2 * N >= M) {
			resize(2 * M);
		}

		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				values[i] = val;
				return;
			}
		}

		keys[i] = key;
		values[i] = val;
		N++;
	}

	public Value get(Key key) {
		int i;
		Value val;

		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				val = values[i];
				return val;
			}
		}

		return null;
	}

	public void delete(Key key) {
		int i;

		if (!contains(key))
			return;

		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				keys[i] = null;
				values[i] = null;
				break;
			}
		}

		// Rehash all further keys
		i = (i + 1) % M;

		while (keys[i] != null) {
			Key rehashKey = keys[i];
			Value rehashValue = values[i];
			keys[i] = null;
			values[i] = null;
			N--;
			put(rehashKey, rehashValue);
			i = (i + 1) % M;
		}

		N--;

		if (N > 0 && N <= M / 8)
			resize(M / 2);
	}

	public void resize(int newCapacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(
				newCapacity);

		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], values[i]);
			}
		}

		// for (Key key : keys()) {
		// Value val = get(key);
		// temp.put(key, val);
		// }

		keys = temp.keys;
		values = temp.values;
		M = temp.M;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<Key>();
		for (int i = 0; i < M; i++) {
			if (keys[i] != null)
				queue.add(keys[i]);
		}
		return queue;
	}
	
	public static void main(String [] args) {
		LinearProbingHashST<Integer, Integer> map = new LinearProbingHashST<>(10);
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		int val;
		
		map.put(10, 1);
		map.put(20, 2);
		map.put(30, 3);
		map.put(40, 4);
		map.put(23, 2333);
		map.put(25, 300);
		
		assert map.get(11) == null;
		assert map.get(20) == 2;
		
		val = map.get(20);
		System.out.println(val);
		
//		hashMap.put(20, 30);
//		val = hashMap.get(30);
	}

}
