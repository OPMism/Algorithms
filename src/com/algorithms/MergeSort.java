package com.algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MergeSort {

	public static void sort(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		sort(a, aux, 0, N - 1);
	}

	public static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
		int mid;

		if (low >= high)
			return;

		mid = low + (high - low) / 2;
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);

	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo,
			int mid, int hi) {
		int i = 0;
		int j = 0;
//		int k = 0;
//		// int l = 0;
//		Comparable[] temp = new Comparable[hi - lo + 1];
//
//		for (; lo + i <= hi; i++) {
//			temp[i] = a[lo + i];
//		}
//		i = 0;
//		j = mid - lo + 1;
//		k = lo;
//		while (hi >= k) {
//			if (i == mid - lo + 1)
//				a[k++] = temp[j++];
//			else if (j == hi - lo + 1)
//				a[k++] = temp[i++];
//			else if (less(temp[j], temp[i]))
//				a[k++] = temp[j++];
//			else
//				a[k++] = temp[i++];
//		}
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		i = lo;
		j = mid + 1;
		
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
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
		int N = 10000000;
		Integer arr[] = new Integer[N];
		Random rand = new Random(534566);
		BufferedWriter bw = null;
		long startTime;
		long endTime;
		long time;
		File file;
		String data;

		file = new File("/home/doom/topcoder/mergesort.txt");

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
		MergeSort.sort(arr);
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
