package com.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

	private Node first;
	private int N;

	private class Node {
		private Item item;
		private Node next;
	}

	public Bag() {
		first = null;
		N = 0;
	}

	public void add(Item w) {
		Node oldfirst = first;
		first = new Node();
		first.item = w;
		first.next = oldfirst;
		N++;
	}

	public boolean isEmpty() {
		return first == null;
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
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String [] args) {
		Bag<Integer> mBag = new Bag<>();
		int num;
		Iterator<Integer> iterator;
		
		for (int i = 0; i < 10; i++) {
			num = (int) (1000 * Math.random());
			System.out.println(num);
			mBag.add(num);
		}

		System.out.println("-------------------------------");
		iterator = mBag.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
