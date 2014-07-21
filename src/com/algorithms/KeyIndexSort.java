package com.algorithms;

public class KeyIndexSort {

	private static final char[] sStringDb = "abcdefghijklmnopqrstuvwxyz"
			.toCharArray();
	private static final int sLen = sStringDb.length;
	private static final int sMAX = 100;

	public KeyIndexSort(char[] array) {

		int i;
		int N;
		int alpha[];
		char aux[];

		N = array.length;
		aux = new char[N];
		alpha = new int[27];

		// Calculate frequency of occurrence of each alphabet
		for (i = 0; i < N; i++) {
			alpha[array[i] - 'a' + 1]++;
		}

		// Calculate cumulative frequencies
		for (i = 1; i < 27; i++) {
			alpha[i] = alpha[i - 1] + alpha[i];
		}

		for (i = 0; i < N; i++) {
			aux[alpha[array[i] - 'a']++] = array[i];
		}

		for (i = 0; i < N; i++) {
			array[i] = aux[i];
		}

	}

	public static void main(String[] args) {
		int i, j, len;
		int index;
		int N;
		String strArray[];
		char element[];
		FastScannerSlow fastScanner;
		fastScanner = new FastScannerSlow();
		KeyIndexSort keyIndexSort;

		System.out
				.println("Enter number of strings in the randomly generated string array:");
		N = fastScanner.nextInt();

		strArray = new String[N];
		len = 0;
		element = null;

		System.out.println("Unsorted string array: ");

		for (i = 0; i < N; i++) {
			// len = (int) (Math.random() * sMAX + 1);
			len = 200;
			element = new char[len];

			for (j = 0; j < len; j++) {
				index = (int) (Math.random() * sLen);
				element[j] = sStringDb[index];
			}
			strArray[i] = new String(element);
			System.out.println(strArray[i]);
		}

		System.out.println("Unsorted char array: ");

		for (i = 0; i < len; i++) {
			System.out.print(element[i] + " ");
		}

		keyIndexSort = new KeyIndexSort(element);
		
		System.out.println("\nSorted char array: ");

		for (i = 0; i < len; i++) {
			System.out.print(element[i] + " ");
		}

		// System.out.println(sStringDb.toString());
		// System.out.println(sStringDb.length);

	}

}
