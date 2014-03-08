package com.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HeapSort {

	public static void sort(Comparable[] array) {
		int N = array.length;
		int k;
		for (int i = N / 2; i >= 1; i--) {
			sink(array, i, N);
		}
		k = N;
		while (k > 1) {
			exch(array, 1, k);
			sink(array, 1, --k);
		}

	}

	public static void sink(Comparable[] array, int k, int N) {
		int j;
		while (2 * k <= N) {
			j = 2 * k;
//			x = j - 1;
			if (j < N && less(array, j, j + 1)) {
				j++;
			}
			if (!less(array, k, j))
				break;
			exch(array, k, j);
			k = j;
		}
	}

//	public static void delMax(Comparable[] array, int N) {
//		exch(array, 0, N - 1);
//		// del(N--);
//		sink(array, 1, N - 1);
//		// return key;
//	}

	public static boolean less(Comparable [] array, int i, int j) {
		if (array[i - 1].compareTo(array[j - 1]) < 0)
			return true;
		return false;
	}

	public static void exch(Comparable[] a, int i, int j) {
		Comparable temp;
		i--;
		j--;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int N = 10000000;
		Integer arr[] = new Integer[N];
		Random rand = new Random(534566);
		BufferedWriter bw = null;
		long startTime;
		long endTime;
		long time;
		File file;
		String data;

		file = new File("/home/doom/topcoder/heapsort.txt");

		System.out.println("Unsorted Array: ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(N);
			// System.out.print(arr[i]);
			// if (i != N - 1)
			// System.out.print(", ");
		}
		System.out.println();

		System.out.println("Sorted Array: ");
		startTime = System.nanoTime();
		HeapSort.sort(arr);
		endTime = System.nanoTime();
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		for (int i = 0; i < N; i++) {
//			try {
//				data = String.valueOf(arr[i]);
//				if (i != N - 1)
//					data += ", ";
//				if (bw != null)
//					bw.append(data);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// System.out.print(arr[i]);
//			// if (i != N - 1)
//			// System.out.print(", ");
//		}
		time = endTime - startTime;
		time /= 1000000000;
		System.out.println("\nTotal time taken to sort: " + time + " seconds");
		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
