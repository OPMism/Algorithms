package com.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

	private Node first;
	private int N;

	private class Node {
		private Node next;
		private Item item;
	}

	public Stack() {
		first = null;
		N = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		if (N == Integer.MAX_VALUE)
			throw new RuntimeException("Stack Overflow Exception!");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() {
		if (isEmpty())
			throw new RuntimeException("Stack Underflow Exception!");
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	public Item peek() {
		if (isEmpty())
			throw new RuntimeException("Stack Underflow Exception!");
		return first.item;
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (hasNext()) {
				Item item = current.item;
				current = current.next;
				return item;
			}
			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

	}
	
	public static void main (String [] args) {
		Stack<Integer> stack = new Stack<>();
		
		stack.push(4);
		stack.push(10);
		
		for (int v : stack)
			System.out.println(v);
	}

}
