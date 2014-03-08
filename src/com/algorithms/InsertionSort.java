package com.algorithms;

import java.util.Random;

public class InsertionSort {

	public static void sort(Comparable[] a) {
		int N;
		int j;
		N = a.length;

		for (int i = 0; i < N - 1; i++) {
			j = i + 1;
			while (j > 0 && less(a[j] , a[j - 1])) {
				exch(a, j, j - 1);
				j--;
			}
		}
	}

	public static boolean less(Comparable v, Comparable w) {
		if (v.compareTo(w) < 0)
			return true;
		return false;
	}

	public static void exch(Comparable[] a, int i, int j) {
		Comparable temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int N = 1000;
		Integer arr[] = new Integer[N];
		Random rand = new Random(47);
		long startTime;
		long endTime;
		long time;

		System.out.println("Unsorted Array: ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(N);
			System.out.print(arr[i]);
			if (i != N - 1)
				System.out.print(", ");
		}
		System.out.println();

		System.out.println("Sorted Array: ");
		startTime = System.nanoTime();
		InsertionSort.sort(arr);
		endTime = System.nanoTime();
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i]);
			if (i != N - 1)
				System.out.print(", ");
		}
		time = endTime - startTime;
		time /= 1000000000;
		System.out.println();
		System.out.println("Total time taken to sort: " + time + " seconds");
	}
}
