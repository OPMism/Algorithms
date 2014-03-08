package com.algorithms;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Trie<Value> {

	private Node root = null;
	private static int R = 256;

	private static class Node {

		private Node next[] = new Node[R];
		private Object value;

		// public Node() {
		// this.next = (Node []) new Object[256];
		// }
		//
		// public Node(Object item) {
		// this.item = item;
		// this.next = (Node []) new Object[256];
		// }
	}

	public void put(String str, Value val) {
		root = put(root, str, 0, val);
	}

	public Node put(Node node, String str, int index, Value val) {

		if (node == null) {
			node = new Node();
		}

		if (index == str.length()) {
			node.value = val;
			return node;
		}
		char c = str.charAt(index);

		node.next[c] = put(node.next[c], str, index + 1, val);

		return node;

	}

	public Value get(String str) {
		Node node;
		node = get(root, str, 0);
		if (node == null)
			return null;
		return (Value) node.value;
	}

	public Node get(Node node, String str, int index) {

		if (node == null)
			return null;

		if (index == str.length()) {
			return node;
		}

		char c = str.charAt(index);

		node = get(node.next[c], str, index + 1);

		return node;

	}

	public void delete(String str) {
		Node node;
		node = delete(root, str, 0);
	}

	public Node delete(Node node, String str, int index) {

		if (node == null)
			return null;

		if (index == str.length() && node.value != null) {
			node.value = null;
			return null;
		}

		char c = str.charAt(index);

		node.next[c] = delete(node.next[c], str, index + 1);

		if (node.next[c] == null && node.value == null)
			return null;

		return node;
	}

	public Iterator<String> collect() {
		Queue<String> queue = new PriorityQueue<String>();
		collect(root, "", queue);
		return queue.iterator();
	}

	private void collect(Node node, String str, Queue<String> queue) {
		if (node == null)
			return;
		if (node.value != null)
			queue.add(str);
		for (char c = 0; c < R; c++) {
			collect(node.next[c], str + c, queue);
		}
	}

	public Iterator<String> keysWithPrefix(String str) {
		Queue<String> queue = new PriorityQueue<String>();
		Node node;
		node = get(root, str, 0);
		collect(node, str, queue);
		return queue.iterator();
	}

	public static void main(String[] args) {

		Trie<Integer> mTrie = new Trie<Integer>();
		Iterator<String> mTrieIterator;
		Iterator<String> mTriePrefixIterator;

		mTrie.put("Fla", 100);
		mTrie.put("Flab", 200);
		mTrie.put("Flabbergasted", 1000);

		System.out.println("Value of \"Flab\": " + mTrie.get("Flab"));
		System.out.println("Value of \"Fla\": " + mTrie.get("Fla"));
		System.out.println("Value of \"Flabbergasted\": "
				+ mTrie.get("Flabbergasted"));
		mTrie.delete("Flabbergasted");
		System.out.println("Value of \"Flabbergasted\": "
				+ mTrie.get("Flabbergasted"));
		System.out.println("Value of \"Fla\": " + mTrie.get("Fla"));

		mTrie.put("Flabbergasted he was entirely", 120);
		mTrie.put("It's very surprising", 21);
		
		mTrieIterator = mTrie.collect();

		while (mTrieIterator.hasNext())
			System.out.println(mTrieIterator.next());

		mTriePrefixIterator = mTrie.keysWithPrefix("I");
		
		System.out.println("\nPrinting all prefixes: ");
		
		while (mTriePrefixIterator.hasNext())
			System.out.println(mTriePrefixIterator.next());

	}

}