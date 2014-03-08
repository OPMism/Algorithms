package com.algorithms;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int count = 1;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			if (key.compareTo(x.key) < 0)
				x = x.left;
			else if (key.compareTo(x.key) > 0)
				x = x.right;
			else
				return x.value;
		}
		return null;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	public Node put(Node x, Key key, Value val) {

		if (x == null)
			return new Node(key, val);

		if (key.compareTo(x.key) < 0)
			x.left = put(x.left, key, val);
		else if (key.compareTo(x.key) > 0)
			x.right = put(x.right, key, val);
		else {
			x.value = val;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	// TODO: Gives incorrect output. Correct this.
	public Key floor(Key key) {
		Node x = root;

		while (x != null) {
			if (key.compareTo(x.key) < 0)
				x = x.left;
			else if (key.compareTo(x.key) > 0) {
				if (x.right != null && key.compareTo(x.right.key) >= 0)
					x = x.right;
				else
					break;
			} else
				break;
		}
		if (x != null)
			return x.key;

		return null;
	}
	
	public int size() {
		return size(root);
	}
	
	public int size(Node x) {
		if (x == null)
			return 0;
		return x.count;
	}
	
	public int rank(Key key) {
		return rank(key, root); 
	}
	
	public int rank(Key key, Node x) {
		
		if(x == null)
			return 0;
		
		if (key.compareTo(x.key) < 0) 
			return rank(key, x.left);
		else if(key.compareTo(x.key) > 0)
			return 1 + size(x.left) + rank(key, x.right);
		return size(x.left);
	}

	public static void main(String[] args) {
		BST<Character, Integer> mBST = new BST<Character, Integer>();
		mBST.put('S', 3);
		mBST.put('E', 5);
		mBST.put('A', 9);
		mBST.put('R', 13);
		mBST.put('C', 43);
		mBST.put('H', 98);
		mBST.put('M', 29);
		mBST.put('X', 92);

		System.out.println("Get M: " + mBST.get('M'));
		System.out.println("Get S: " + mBST.get('S') + "\tGet count: " + mBST.size());
		System.out.println("Floor M: " + mBST.floor('M'));
	}

}
