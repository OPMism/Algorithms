package com.algorithms;
import java.util.LinkedList;
import java.util.Queue;

public class SequentialSearchST<Key, Value> {

	private int N; // Number of key value pairs
	Node first;

	private class Node {
		Key key;
		Value value;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.value = val;
			this.next = next;
		}
	}

	public SequentialSearchST() {
		first = null;
		N = 0;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}

		for (Node n = first; n != null; n = n.next) {
			if (key.equals(n.key)) {
				n.value = val;
				return;
			}
		}

		first = new Node(key, val, first);
		N++;
	}

	public Value get(Key key) {
		Node node = first;
		while (node != null) {
			if (key.equals(node.key))
				return node.value;
			node = node.next;
		}
		return null;
	}

	public void delete(Key key) {
		first = delete(first, key);
	}

	private Node delete(Node node, Key key) {
		if (node == null)
			return null;
		if (key.equals(node.key)) {
			N--;
			return node.next;
		}
		node.next = delete(node.next, key);
		return node;
	}

	public boolean contains(Key key) {
		for (Node n = first; n != null; n = n.next) {
			if (key.equals(n.key))
				return true;
		}
		return false;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>();
		Node node = first;
		while (node != null) {
			queue.add(node.key);
			node = node.next;
		}
		return queue;
	}

}
