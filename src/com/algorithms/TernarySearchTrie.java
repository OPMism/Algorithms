package com.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class TernarySearchTrie<Value> {

	private Node root = null;

	private class Node {
		private Value val;
		private char c;
		private Node left;
		private Node mid;
		private Node right;

		public Node() {
			val = null;
			left = null;
			mid = null;
			right = null;
		}
	}

	public void put(String str, Value val) {

		root = put(root, str, 0, val);

	}

	public Node put(Node node, String str, int index, Value val) {

		char c;
		c = str.charAt(index);

		if (node == null) {
			node = new Node();
			node.c = c;
		}

		if (c < node.c)
			node.left = put(node.left, str, index, val);
		else if (c > node.c)
			node.right = put(node.right, str, index, val);
		else if (index == str.length() - 1)
			node.val = val;
		else
			node.mid = put(node.mid, str, index + 1, val);

		return node;

	}

	public Value get(String str) {
		Node node;

		node = get(root, str, 0);

		if (node == null)
			return null;

		return node.val;
	}

	public Node get(Node node, String str, int index) {

		if (node == null)
			return null;

		char c = str.charAt(index);

		if (c < node.c)
			node = get(node.left, str, index);
		else if (c > node.c)
			node = get(node.right, str, index);
		else if (index == str.length() - 1)
			return node;
		else
			node = get(node.mid, str, index + 1);

		return node;

	}

	// Returns longest prefix of the string ONLY if the prefix has a value
	// associated with it
	public String longestPrefixOfIterative(String key) {
		Node node = root;
		int index = 0;
		int length = 0;
		char c;

		while (node != null && index < key.length()) {
			c = key.charAt(index);

			if (c < node.c)
				node = node.left;
			else if (c > node.c)
				node = node.right;
			else {
				index++;
				if (node.val != null)
					length = index;
				node = node.mid;
			}
		}

		return key.substring(0, length);
	}

	// Returns longest prefix of the string even if the prefix has no value
	// associated with it
	public String longestPrefixOf(String str) {
		return longestPrefixOf(root, str, 0);
	}

	private String longestPrefixOf(Node node, String key, int index) {

		if (node == null || key.length() == index)
			return "";

		char c = key.charAt(index);

		if (c < node.c)
			return longestPrefixOf(node.left, key, index);
		else if (c > node.c)
			return longestPrefixOf(node.right, key, index);
		else if (index < key.length())
			return c + longestPrefixOf(node.mid, key, index + 1);
		else
			return String.valueOf(c);

	}

	// Returns all keys with prefix 'prefix'. The returned keys all have a value
	// associated with them.
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> queue = new LinkedList<String>();
		Node node;

		node = get(root, prefix, 0);

		if (node == null)
			return queue;
		if (node.val != null)
			queue.add(prefix);

		collect(new StringBuilder(prefix), node.mid, queue);
		return queue;
	}

	private void collect(StringBuilder prefix, Node node, Queue<String> queue) {
		if (node == null)
			return;
		collect(prefix, node.left, queue);
		if (node.val != null)
			queue.add(prefix.toString() + node.c);
		collect(prefix.append(node.c), node.mid, queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(prefix, node.right, queue);
	}

	// Returns keys that match the particular String pattern, both in length and
	// the characters. '.' matches on wildcard.
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> queue = new LinkedList<>();
		collect(new StringBuilder(), root, 0, pattern, queue);
		return queue;
	}

	private void collect(StringBuilder prefix, Node node, int i,
			String pattern, Queue<String> queue) {

		if (node == null)
			return;

		if (i < pattern.length()) {
			char c = pattern.charAt(i);

			if (c == '.' || c < node.c) {
				collect(prefix, node.left, i, pattern, queue);
			}
			if (c == '.' || c > node.c) {
				collect(prefix, node.right, i, pattern, queue);
			}
			if (c == '.' || c == node.c) {
				if (i == pattern.length() - 1) {
					if (node.val != null)
						queue.add(prefix.toString() + node.c);
				} else {
					// i < pattern.length() - 1
					collect(prefix.append(node.c), node.mid, i + 1, pattern,
							queue);
					prefix.deleteCharAt(prefix.length() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {

		// Test 1
		TernarySearchTrie<Integer> mTST = new TernarySearchTrie<Integer>();

		mTST.put("abc", 100);
		mTST.put("abcd", 20);
		mTST.put("abd", 30);

		System.out.println("Value: " + mTST.get("abcd"));
		System.out.println("Value: " + mTST.get("abd"));
		System.out.println("Value: " + mTST.get("abc"));
		System.out.println("Value: " + mTST.get("xbcde"));

		// Test 2
		TernarySearchTrie<Integer> trie = new TernarySearchTrie<>();
		String key;
		int val;

		key = "atif";
		trie.put(key, 100);
		val = trie.get(key);
		assert val == 100;
		key = "ty";
		assert trie.get(key) == null;
		System.out.println(val);
		trie.put(key, 55);

		key = "atifs";
		trie.put(key, 1999);
		assert trie.get(key) == 1999;

		key = "atikeyafdsadf";
		trie.put(key, 2);

		key = "atikey";
		String prefix = trie.longestPrefixOf(key);
		System.out.println("\nLongest prefix of " + key);
		System.out.println(prefix);

		key = "atikeyafdsadf";
		prefix = trie.longestPrefixOfIterative(key);
		System.out.println("\nLongest prefix of " + key);
		System.out.println(prefix);

		key = "atif";
		System.out.println("\nKeys with prefix: " + key);
		for (String prefixKey : trie.keysWithPrefix(key)) {
			System.out.println(prefixKey);
		}

		// Matching keys
		key = "....";
		System.out.println("\nKeys that match: " + key);
		for (String matchKey : trie.keysThatMatch(key)) {
			System.out.println(matchKey);
		}

	}

}