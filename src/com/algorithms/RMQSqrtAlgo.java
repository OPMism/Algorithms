package com.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is the O(N) preprocessing and O(sqrt(N)) query algo for Range Minimum
 * Query
 */
public class RMQSqrtAlgo {

	public int mN;
	public int mSqrt;
	public int M[];
	public long A[];

	public RMQSqrtAlgo(long[] A) {
		int index;
		int minIndex;
		mN = A.length;
		mSqrt = (int) Math.sqrt(mN);

		this.A = A;

		// if (mSqrt * mSqrt != mN)
		// mSqrt += 1;

		M = new int[mN / mSqrt + 1];

		for (int i = 0; i <= mN / mSqrt; i++) {
			minIndex = i * mSqrt;
			for (int j = 0; j < mSqrt; j++) {
				index = i * mSqrt + j;
				if (index == mN)
					break;
				if (A[index] < A[minIndex])
					minIndex = index;
			}
			M[i] = minIndex;
		}

	}

	public void printArrays() {
		int i;

		System.out.println("Input array:");
		for (i = 0; i < mN; i++) {
			System.out.print(A[i] + " ");
		}

		System.out.println("\nRMQ array: Params (N = " + mN + ", sqrt = "
				+ mSqrt + ")");
		for (i = 0; i < M.length; i++) {
			System.out.print(M[i] + " ");
		}

		System.out.println();
	}

	public int getMin(int u, int v) {

		if (u < 0 || v < 0 || u >= mN || v >= mN)
			return -1;

		if (u > v)
			return -1;

		int i1, i2;
		int minIndex;

		i1 = (int) Math.sqrt(u);
		i2 = (int) Math.sqrt(v);

		if (i1 * i1 != u)
			i1 += 1;

		minIndex = u;

		for (int i = u; i < Math.min(v, i1 * i1); i++) {
			if (A[minIndex] > A[i])
				minIndex = i;
		}

		for (int i = i1; i <= i2; i++) {
			if (A[minIndex] > A[M[i]])
				minIndex = M[i];
		}

		for (int i = i2 * i2 + 1; i <= v; i++) {
			if (A[minIndex] > A[i])
				minIndex = i;
		}

		return minIndex;
	}

	public static void main(String[] args) throws IOException {
		RMQSqrtAlgo mRMQSqrtAlgo;
		long[] array;
		int u, v;
		int N;
		int minIndex;
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the length of the array: ");
		N = Integer.parseInt(bR.readLine());
		array = new long[N];

		for (int i = 0; i < N; i++) {
			array[i] = (long) (Math.random() * 100);
		}

		mRMQSqrtAlgo = new RMQSqrtAlgo(array);
		mRMQSqrtAlgo.printArrays();

		for (int i = 0; i < N; i++) {
			u = (int) (Math.random() * N);
			v = (int) (Math.random() * N);

			minIndex = mRMQSqrtAlgo.getMin(u, v);

			System.out.println("RMQ (" + u + "," + v + ") -> "
					+ (minIndex != -1 ? array[minIndex] : minIndex));

		}

	}

}
