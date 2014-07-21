package com.algorithms;

public class LSDStringSort {

	private static final char[] sStringDb = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST0123456789"
			.toCharArray();
	private static final int sLen = sStringDb.length;
	private static final int sMAX = 100;

	/**
	 * Least Significant Digit First Sort
	 * 
	 * @param array
	 *            Array of strings
	 * @param d
	 *            Each string in array is of fixed length d
	 */
	public LSDStringSort(String[] array, int L) {
		int i;
		int N;
		int R = 256;
		String[] aux;
		int[] radix;
		int d;

		N = array.length;
		aux = new String[N];

		for (d = L - 1; d >= 0; d--) {
			radix = new int[R + 1];
			for (i = 0; i < N; i++) {
				radix[array[i].charAt(d) + 1]++;
			}

			for (i = 0; i < R - 1; i++) {
				radix[i + 1] += radix[i];
			}

			for (i = 0; i < N; i++) {
				aux[radix[array[i].charAt(d)]++] = array[i];
			}

			for (i = 0; i < N; i++) {
				array[i] = aux[i];
			}

		}

	}

	/**
	 * Unit testing LSD Sort
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
		LSDStringSort lsdStringSort;

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

		lsdStringSort = new LSDStringSort(strArray, len);

		System.out.println("\nSorted string array: ");
		
		for (i = 0; i < N; i++) {
			System.out.println(strArray[i]);
		}

	}

}
