package com.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSort {

	public static void sort(Comparable[] a) {
		int j;
		Comparable tmp;

		// Random shuffling
		for (int i = 0; i < a.length; i++) {
			j = (int) (i * Math.random());
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}

		sort(a, 0, a.length - 1);
	}

	public static int partition(Comparable[] a, int low, int high) {
		int i;
		int j;
		int pivot;
		Comparable temp;

		pivot = low;
		i = low;
		j = high + 1;

		while (true) {

			while (less(a[++i], a[pivot]))
				if (i == high)
					break;
			while (less(a[pivot], a[--j]))
				if (j == low)
					break;

			if (i < j)
				exch(a, i, j);
			else
				break;
		}
		exch(a, pivot, j);
		return j;
	}

	public static void sort(Comparable[] a, int low, int high) {
		int pivot;
		if (high <= low)
			return;
		pivot = partition(a, low, high);
		sort(a, low, pivot - 1);
		sort(a, pivot + 1, high);

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
		int N = 20000000;
		Integer arr[] = new Integer[N];
//		Integer arr[] = {5, 4, 5, 2, 6, 7, 1, 5, 9, 3};
		Random rand = new Random(534566);
		BufferedWriter bw = null;
		long startTime;
		long endTime;
		long time;
		File file;
		String data;

		file = new File("/home/doom/topcoder/quicksort.txt");

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
		QuickSort.sort(arr);
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
