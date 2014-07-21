package com.algorithms;

public class MSDStringSort {
	private static final char[] sStringDb = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST0123456789"
			.toCharArray();
	private static final int sLen = sStringDb.length;
	private static final int sMAX = 100;
	private static final int R = 256;

	public MSDStringSort(String[] array, int L) {
		int N;
		String aux[];

		N = array.length;
		aux = new String[N];

		sort(array, aux, 0, N - 1, 0, L);
	}

	private void sort(String[] array, String[] aux, int lo, int hi, int d, int L) {
		int i;
		int N;
		int count[];

		if (hi <= lo)
			return;

		N = array.length;
		count = new int[R + 2];

		for (i = lo; i <= hi; i++) {
			count[charAt(array[i], d) + 2]++;
		}
		for (i = 0; i < R + 1; i++) {
			count[i + 1] += count[i];
		}
		for (i = lo; i <= hi; i++) {
			aux[count[charAt(array[i], d) + 1]++] = array[i];
		}
		for (i = lo; i <= hi; i++) {
			array[i] = aux[i - lo];
		}

		for (int r = 0; r < R; r++) {
			sort(array, aux, lo + count[r], lo + count[r + 1] - 1, d + 1, L);
		}

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
		MSDStringSort msdStringSort;

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

		msdStringSort = new MSDStringSort(strArray, len);

		System.out.println("\nSorted string array: ");

		for (i = 0; i < N; i++) {
			System.out.println(strArray[i]);
		}

	}

}
