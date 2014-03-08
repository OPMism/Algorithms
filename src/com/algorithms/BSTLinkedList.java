package com.algorithms;

import java.io.*;
import java.util.*;

//import com.linkedlist.LinkedList;

public class BSTLinkedList<Key extends Comparable<Key>, Value> {

	private Node root = null;

	private class Node {

		public Key key;
		public Value val;
		public Node left;
		public Node right;
		public int count = 1;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
			left = null;
			right = null;
		}

	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		return node.count;
	}

	public Value get(Key key) {
		Node x;

		x = get(root, key);

		if (x == null)
			return null;
		else
			return x.val;
	}

	public Node get(Node node, Key key) {

		if (node == null)
			return null;

		if (key.compareTo(node.key) < 0)
			node = get(node.left, key);
		else if (key.compareTo(node.key) > 0)
			node = get(node.right, key);

		return node;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	public Node put(Node node, Key key, Value val) {

		if (node == null)
			return new Node(key, val);

		if (key.compareTo(node.key) < 0)
			node.left = put(node.left, key, val);
		else if (key.compareTo(node.key) > 0)
			node.right = put(node.right, key, val);
		else
			node.val = val;

		node.count = 1 + size(node.left) + size(node.right);

		return node;
	}

	public Key floor(Key key) {
		Node node;
		node = floor(root, key);
		if (node == null)
			return null;
		return node.key;
	}

	public Node floor(Node node, Key key) {

		if (node == null)
			return null;

		if (key.compareTo(node.key) == 0)
			return node;
		else if (key.compareTo(node.key) < 0) {
			node = floor(node.left, key);
		} else {
			Node x = floor(node.right, key);
			if (x != null)
				node = x;
		}

		return node;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	public int rank(Node node, Key key) {
		if (node == null)
			return 0;

		if (key.compareTo(node.key) == 0)
			return size(node.left);
		else if (key.compareTo(node.key) < 0)
			return rank(node.left, key);
		else
			return 1 + size(node.left) + rank(node.right, key);
	}

	public int balancedTree() {
		int val;
		val = balancedTree(root);
		return val;
	}

	public int balancedTree(Node node) {

		int lCount;
		int rCount;

		if (node == null)
			return 0;

		lCount = balancedTree(node.left);
		rCount = balancedTree(node.right);

		if (lCount == -1)
			return -1;

		if (rCount == -1)
			return -1;

		if (Math.abs(lCount - rCount) > 1)
			return -1;

		if (lCount > rCount)
			return lCount + 1;

		return rCount + 1;

	}

	public int leftHeight() {
		int count;
		if (root == null)
			return -1;
		count = height(root.left);
		return count;
	}

	public int rightHeight() {
		int count;
		if (root == null)
			return -1;
		count = height(root.right);
		return count;
	}

	public int height(Node node) {

		int lCount;
		int rCount;

		if (node == null)
			return -1;

		lCount = 1 + height(node.left);
		rCount = 1 + height(node.right);

		if (lCount > rCount)
			return lCount;
		else
			return rCount;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	public Node deleteMin(Node node) {

		if (node == null)
			return null;

		if (node.left == null)
			return node.right;

		node.left = deleteMin(node.left);
		node.count = 1 + size(node.left) + size(node.right);

		return node;

	}

	public Node min(Node node) {
		if (node == null)
			return null;

		if (node.left == null)
			return node;

		node = min(node.left);
		return node;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	// Hibbard Deletion
	public Node delete(Node node, Key key) {

		Node x;

		if (node == null)
			return null;

		if (key.compareTo(node.key) < 0)
			node.left = delete(node.left, key);
		else if (key.compareTo(node.key) > 0)
			node.right = delete(node.right, key);
		else {

			if (node.left == null && node.right == null) {
				return null;
			} else if (node.right == null) {
				return node.left;
			} else if (node.left == null) {
				return node.right;
			} else {
				x = node;
				node = min(x.right);
				node.right = deleteMin(x.right);
				node.left = x.left;
			}
		}

		node.count = 1 + size(node.left) + size(node.right);
		return node;

	}

	public Iterator<Key> preOrderTraversal() {
		Queue<Key> queue = new java.util.LinkedList<Key>();
		preOrderTraversal(root, queue);
		return queue.iterator();
	}

	public void preOrderTraversal(Node node, Queue<Key> queue) {

		if (node == null)
			return;

		queue.add(node.key);
		preOrderTraversal(node.left, queue);
		preOrderTraversal(node.right, queue);

	}

	public Iterator<Key> inOrderTraversal() {
		Queue<Key> queue = new java.util.LinkedList<Key>();
		inOrderTraversal(root, queue);
		return queue.iterator();
	}

	public void inOrderTraversal(Node node, Queue<Key> queue) {

		if (node == null)
			return;

		inOrderTraversal(node.left, queue);
		queue.add(node.key);
		inOrderTraversal(node.right, queue);

	}

	public Iterator<Key> postOrderTraversal() {
		Queue<Key> queue = new java.util.LinkedList<Key>();
		postOrderTraversal(root, queue);
		return queue.iterator();
	}

	public void postOrderTraversal(Node node, Queue<Key> queue) {

		if (node == null)
			return;

		postOrderTraversal(node.left, queue);
		postOrderTraversal(node.right, queue);
		queue.add(node.key);
	}

	public void createLinkedListLevelOrder() {

		Node node;
		Queue<Node> queue = new java.util.LinkedList<Node>();
		Queue<Node> nextLevelQueue = new java.util.LinkedList<Node>();
		ArrayList<LinkedList<Node>> mLinkedList = new ArrayList<LinkedList<Node>>();
		LinkedList<Node> mLL = new LinkedList<Node>();
		// LinkedList

		queue.add(root);

		while ((node = queue.poll()) != null) {

			if (node.left != null)
				nextLevelQueue.add(node.left);
			if (node.right != null)
				nextLevelQueue.add(node.right);

			mLL.add(node);

			if (queue.peek() == null) {

				queue = nextLevelQueue;
				nextLevelQueue = new java.util.LinkedList<Node>();
				mLinkedList.add(mLL);
				mLL = new LinkedList<Node>();
			}

		}
		Iterator<Node> it;
		for (int i = 0; i < mLinkedList.size(); i++) {
			System.out.println();
			mLL = mLinkedList.get(i);
			it = mLL.iterator();
			
			while (it.hasNext())
				System.out.print(it.next().key + " ");
		}

		return;

	}

	public static void main(String[] args) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		BSTLinkedList<Character, Integer> mBSTLinkedList = new BSTLinkedList<Character, Integer>();
		// ArrayList<LinkedList<Node>> mLinkedList = new
		// ArrayList<LinkedList<Node>>();
		Iterator<Character> mIter;
		char floorChar;
		char floor;

		mBSTLinkedList.put('S', 3);
		mBSTLinkedList.put('E', 5);
		mBSTLinkedList.put('A', 9);
		mBSTLinkedList.put('R', 13);
		mBSTLinkedList.put('C', 43);
		mBSTLinkedList.put('H', 98);
		mBSTLinkedList.put('M', 29);
		mBSTLinkedList.put('X', 92);
		mBSTLinkedList.put('M', 88);

		try {
			System.out
					.println("Enter a character whose floor you want to find: ");
			floorChar = bReader.readLine().charAt(0);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Get M: " + mBSTLinkedList.get('M'));
		System.out.println("Get S: " + mBSTLinkedList.get('S'));
		System.out.println("Get S: " + mBSTLinkedList.get('S')
				+ "\tGet count: " + mBSTLinkedList.size());
		floor = mBSTLinkedList.floor(floorChar);
		System.out.println("Floor " + floorChar + ": " + floor);
		mBSTLinkedList.deleteMin();
		System.out.println("Get A: " + mBSTLinkedList.get('A')
				+ "\tGet count: " + mBSTLinkedList.size());
		mBSTLinkedList.delete(floorChar);
		System.out.println("Get " + floorChar + ": "
				+ mBSTLinkedList.get(floorChar) + "\tGet count: "
				+ mBSTLinkedList.size());
		System.out.println("Rank of X: " + mBSTLinkedList.rank('X'));

		mBSTLinkedList.createLinkedListLevelOrder();

	}

}