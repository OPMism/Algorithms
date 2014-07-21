package com.dp;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search
 * -tree/
 * 
 * @author atif
 * 
 */
public class OptimalBST {

	public static long dp[][];
	public static long sumFreq[][];

	public static long optBSTDP(int[] arr, int[] freq) {
		int n;
		int r;
		int i, j, l;
		long[][] dp;
		n = arr.length;
		dp = new long[n + 2][n + 2];
		long sum;

		for (i = 1; i <= n; i++) {
			dp[i][i] = freq[i - 1];
		}

		for (l = 3; l <= n + 1; l++) {

			for (i = 1; i <= n - l + 2; i++) {

				j = l + i - 2;

				sum = sumFreq[i - 1][j - 1];

				dp[i][j] = dp[i][j - 1] + sum;

				for (r = i; r <= j; r++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][r - 1] + dp[r + 1][j]
							+ sum);
				}

			}
		}

		/*
		 * for (i = 0; i < n + 2; i++) { for (j = 0; j < n + 2; j++) {
		 * System.out.print(dp[i][j] + " "); } System.out.println(); }
		 */

		return dp[1][n];
	}

	public static long optBST(int[] arr, int[] freq, int i, int j) {
		int r;
		long minSum;
		long sum;

		if (i > j)
			return 0;

		if (i == j)
			return freq[i];

		if (dp[i][j] != -1)
			return dp[i][j];

		minSum = Long.MAX_VALUE;

		for (r = i; r <= j; r++) {
			sum = optBST(arr, freq, i, r - 1) + optBST(arr, freq, r + 1, j);
			minSum = Math.min(minSum, sum);
		}

		return (dp[i][j] = minSum + sumFreq[i][j]);
	}

	public static void sumFreq(int[] freq) {
		int i, j;
		int n;

		n = freq.length;
		sumFreq = new long[n][n];

		for (i = 0; i < n; i++)
			sumFreq[i][i] = freq[i];

		for (int l = 2; l <= n; l++) {
			for (i = 0; i < n - l + 1; i++) {
				j = i + l - 1;
				sumFreq[i][j] = freq[j] + sumFreq[i][j - 1];
			}
		}

	}

	public static long sum(int[] freq, int i, int j) {

		long sum = 0;

		// System.out.println("i = " + i + "; j = " + j);

		while (i <= j) {
			sum += freq[i];
			i++;
		}

		return sum;
	}

	public static void main(String[] args) {
		// int keys[] = { 10, 12, 20 };
		// int freq[] = { 34, 8, 50 };
		int n;
		int i;
		int[] keys;
		int[] freq;
		long startTime;
		long endTime;

		n = 5000;
		keys = new int[n];
		freq = new int[n];

		for (i = 0; i < n; i++) {
			keys[i] = (int) (1000 * (Math.random()));
			freq[i] = (int) (1000 * (Math.random()));
		}

		// n = keys.length;

		dp = new long[n + 1][n + 1];
		sumFreq(freq);

		for (i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++)
				dp[i][j] = -1;

		startTime = System.currentTimeMillis();
		System.out.println("Recursive BST answer: " + optBST(keys, freq, 0, n - 1));
		endTime = System.currentTimeMillis();
		System.out.println("Recursive BST time taken: " + ((endTime - startTime) * 1.0 / 1000) + " seconds");

		startTime = System.currentTimeMillis();
		System.out.println("DP BST time taken: " + optBSTDP(keys, freq));
		endTime = System.currentTimeMillis();
		System.out.println("DP BST time taken: " + ((endTime - startTime) * 1.0 / 1000) + " seconds");
	}

}
