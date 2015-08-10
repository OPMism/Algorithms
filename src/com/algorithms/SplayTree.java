package com.algorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 * Bottom up splay tree
 * 
 * @author sultan.of.swing
 * 
 * @param <K>
 */

public class SplayTree<K extends Comparable<K>> {

	private class Node {
		Node left;
		Node right;
		Node parent;
		K key;

		public Node(Node left, Node right, Node parent, K key) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.key = key;
		}

		public Node(K key) {
			this(null, null, null, key);
		}
	}

	Node root;

	public SplayTree() {
		root = null;
	}

	public void add(K key) {
		if (root == null) {
			root = new Node(key);
		} else {
			Node node = root;
			Node parent = null;
			while (node != null) {
				parent = node;
				if (key.compareTo(node.key) < 0) {
					node = node.left;
				} else if (key.compareTo(node.key) > 0) {
					node = node.right;
				} else {
					break;
				}
			}

			if (node == null) {
				node = new Node(key);
				node.parent = parent;
			}

			if (key.compareTo(parent.key) < 0)
				parent.left = node;
			else if (key.compareTo(parent.key) > 0)
				parent.right = node;

			splay(node);
		}
	}

	public boolean remove(K key) {
		if (!contains(key))
			return false;

		Node node = root;

		if (node.right == null) {
			root = node.left;
			node.left = null;
		} else if (node.left == null) {
			root = node.right;
			node.right = null;
		} else {
			Node min = min(node.right);
			node.right = delMin(node.right);
			min.left = node.left;
			min.right = node.right;
			if (min.left != null)
				min.left.parent = min;
			if (min.right != null)
				min.right.parent = min;
			root = min;
			node.left = node.right = node.parent = null;
		}

		if (root != null)
			root.parent = null;

		return true;
	}

	private Node delMin(Node node) {

		if (node == null)
			return null;

		if (node.left == null)
			return node.right;

		node.left = delMin(node.left);

		if (node.left != null)
			node.left.parent = node;

		return node;
	}

	private Node min(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	public boolean contains(K key) {
		Node node = find(key);
		return node != null;
	}

	private Node find(K key) {

		if (root == null)
			return null;

		Node node = root;
		Node parent = null;

		while (node != null) {
			parent = node;
			if (key.compareTo(node.key) < 0) {
				node = node.left;
			} else if (key.compareTo(node.key) > 0) {
				node = node.right;
			} else {
				break;
			}
		}

		// Parent will be the same as node if key is found. Else splay around
		// the last key found in the search path.
		splay(parent);

		return node;
	}

	public void splay(Node node) {

		if (node == null)
			return;

		while (node.parent != null) {
			if (node.parent.parent == null) {
				// Grandparent is null
				if (node.parent.left == node)
					rotateRight(node.parent);
				else
					rotateLeft(node.parent);
			} else if (node.parent.left == node
					&& node.parent.parent.left == node.parent) {
				// Zig Zig - Left left (Rotate grandparent first)
				rotateRight(node.parent.parent);
				rotateRight(node.parent);
			} else if (node.parent.right == node
					&& node.parent.parent.right == node.parent) {
				// Zig Zig - Right Right
				rotateLeft(node.parent.parent);
				rotateLeft(node.parent);
			} else if (node.parent.left == node
					&& node.parent.parent.right == node.parent) {
				// Zig Zag - Right Left
				rotateRight(node.parent);
				rotateLeft(node.parent);
			} else {
				// Zig Zag - Left Right
				rotateLeft(node.parent);
				rotateRight(node.parent);
			}
		}

		root = node;
	}

	public void rotateRight(Node parent) {
		Node left = parent.left;
		Node leftRightChild = left.right;

		parent.left = leftRightChild;

		if (leftRightChild != null)
			leftRightChild.parent = parent;

		left.parent = parent.parent;
		left.right = parent;
		parent.parent = left;

		if (left.parent != null) {
			if (left.parent.left == parent) {
				left.parent.left = left;
			} else {
				left.parent.right = left;
			}
		}
	}

	public void rotateLeft(Node parent) {
		Node right = parent.right;
		Node rightLeftChild = right.left;

		parent.right = rightLeftChild;

		if (rightLeftChild != null)
			rightLeftChild.parent = parent;

		right.parent = parent.parent;
		right.left = parent;
		parent.parent = right;

		Node grandParent = right.parent;

		if (grandParent != null) {
			if (grandParent.left == parent)
				grandParent.left = right;
			else
				grandParent.right = right;
		}

	}

	public static void main(String[] args) {
		int N = 100;
		int MAX = 100;
		int tests = 1000;
		ArrayList<Integer> addedKeys = new ArrayList<>();

		for (long seed = 0; seed < tests; seed++) {
			Random random = new Random(seed);

			System.out.println("\nSeed value: " + seed);

			SplayTree<Integer> splayTree = new SplayTree<>();
			addedKeys.clear();

			for (int i = 0; i < N; i++) {
				int key = random.nextInt(MAX);
				System.out.println("[Operation #" + (i + 1) + "] Added Key: "
						+ key);
				splayTree.add(key);
				addedKeys.add(key);
			}

			System.out.println("\nContains Operations");

			for (int i = 0; i < addedKeys.size(); i++) {
				int key = addedKeys.get(i);
				System.out.println("Key " + key);
				assert splayTree.contains(key) == true;
			}

			System.out.println("\nDelete operations");

			for (int i = 0; i < N; i++) {
				int key = addedKeys.get(i);
				System.out.println("[Operation #" + (i + 1) + "] Key: " + key);
				splayTree.remove(key);
				assert splayTree.contains(key) == false;
			}
		}
	}
}
