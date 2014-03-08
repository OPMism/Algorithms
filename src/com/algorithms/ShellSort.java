package com.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ShellSort {

	public static void sort(Comparable[] a) {
		int N;
		int j;
		N = a.length;
		int k = 0;

		while (k < N/3) {
			k = 3 * k + 1;
		}

		while (k > 0) {
			for (int i = k; i < N; i += k) {
				j = i;
				while (j >= k && less(a[j], a[j - k])) {
					exch(a, j, j - k);
					j -= k;
				}
			}
			k /= 3;
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
		int N = 100000;
		Integer arr[] = new Integer[N];
		Random rand = new Random(47);
		BufferedWriter bw = null;
		long startTime;
		long endTime;
		long time;
		File file;
		String data;

		file = new File("/home/doom/topcoder/shellsort.txt");

		System.out.println("Unsorted Array: ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(N);
//			System.out.print(arr[i]);
//			if (i != N - 1)
//				System.out.print(", ");
		}
		System.out.println();

		System.out.println("Sorted Array: ");
		startTime = System.nanoTime();
		ShellSort.sort(arr);
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
		System.out.println();
		System.out.println("Total time taken to sort: " + time + " seconds");
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
