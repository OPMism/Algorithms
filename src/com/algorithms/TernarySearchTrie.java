package com.algorithms;

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

	public static void main(String[] args) {
		TernarySearchTrie<Integer> mTST = new TernarySearchTrie<Integer>();

		mTST.put("abc", 100);
		mTST.put("abcd", 20);
		mTST.put("abd", 30);
		
		System.out.println("Value: " + mTST.get("abcd"));
		System.out.println("Value: " + mTST.get("abd"));
		System.out.println("Value: " + mTST.get("abc"));
		System.out.println("Value: " + mTST.get("xbcde"));

	}

}