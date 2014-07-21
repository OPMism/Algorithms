package com.algorithms;

public class ThreeWayStringQuickSort {
	private static final char[] sStringDb = "abcdefghijklmnopqrstuvwxyz"
			.toCharArray();
	private static final int sLen = sStringDb.length;

	public ThreeWayStringQuickSort(String[] array) {
		int N;

		N = array.length;

		sort(array, 0, N - 1, 0);
	}

	/**
	 * Recursively sort the array using 3-way quick radix string sort
	 * 
	 * @param array
	 * @param lo
	 * @param hi
	 * @param d
	 */
	public void sort(String[] array, int lo, int hi, int d) {
		int lt, gt;
		int v;
		int c;
		int i;

		if (hi <= lo)
			return;

		lt = lo;
		gt = hi;
		v = charAt(array[lo], d);

		i = lt + 1;

		while (i <= gt) {
			c = charAt(array[i], d);

			if (c < v) {
				exch(array, i++, lt++);
			} else if (c > v) {
				exch(array, i, gt--);
			} else {
				i++;
			}
		}

		sort(array, lo, lt - 1, d);

		if (v >= 0)
			sort(array, lt, gt, d + 1);

		sort(array, gt + 1, hi, d);

	}

	public void exch(String[] array, int i, int j) {
		String temp;

		temp = array[i];
		array[i] = array[j];
		array[j] = temp;

	}

	public int charAt(String s, int d) {
		if (d >= s.length())
			return -1;
		return s.charAt(d);
	}

	/**
	 * Unit testing MSD Sort
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int i, j, len;
		int index;
		int N;
		String strArray[];
		char element[];
		FastScannerSlow fastScanner;
		fastScanner = new FastScannerSlow();
		ThreeWayStringQuickSort threeWayQuickSort;

		System.out
				.println("Enter number of strings in the randomly generated string array:");
		N = fastScanner.nextInt();

		System.out.println("Enter the uniform length of each string:");
		len = fastScanner.nextInt();

		strArray = new String[N];
		element = null;

		System.out.println("Unsorted string array: ");

		for (i = 0; i < N; i++) {
			element = new char[len];
			for (j = 0; j < len; j++) {
				index = (int) (Math.random() * sLen);
				element[j] = sStringDb[index];
			}
			strArray[i] = new String(element);
			System.out.println(strArray[i]);
		}

		threeWayQuickSort = new ThreeWayStringQuickSort(strArray);

		System.out.println("\nSorted string array: ");

		for (i = 0; i < N; i++) {
			System.out.println(strArray[i]);
		}

	}

}
