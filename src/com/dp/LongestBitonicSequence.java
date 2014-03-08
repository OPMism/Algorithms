package com.dp;

public class LongestBitonicSequence {

	public static void main(String[] args) {

//		int arr[] = new int[] { 3, 5, 2, 6, 8, 10, 2, 1 };
		int arr[] = new int[] {1, 11, 2, 10, 4, 5, 2, 1};
		int dpIncr[];
		int dpDecr[];
		int dp[];
		int maxIncr;

		dpIncr = new int[arr.length];
		dpDecr = new int[arr.length];
		dp = new int[arr.length];
		// dpIncr[0] = 1;

		// maxIncr = findLongestIncreasingSequence(arr, arr.length - 1, dpIncr);
		// findLongestDecreasingSequence(arr, 0, dpDecr);
		findLongestBitonicSequence(arr, dpIncr, dpDecr, dp);
		return;

	}

	public static int findLongestBitonicSequence(int arr[], int dpIncr[],
			int dpDecr[], int dp[]) {

		int j;
		int left;
		int right;
		int max = 1;

		findLongestIncreasingSequence(arr, arr.length - 1, dpIncr);
		findLongestDecreasingSequence(arr, 0, dpDecr);

		for (j = 0; j < arr.length; j++) {
			max = dpIncr[j] + dpDecr[j] - 1;
			dp[j] = max;

		}

		return max;

	}

	public static int findLongestIncreasingSequence(int arr[], int i,
			int dpIncr[]) {

		int j;
		// int k;
		int max = 1;
		int val;
		// int val2;

		if (i < 0)
			return 0;

		// if (i == 0)
		// return 1;

		for (j = 0; j < i; j++) {
			// max = 1;
			val = findLongestIncreasingSequence(arr, j, dpIncr) + 1;

			if (arr[i] > arr[j]) {
				max = Math.max(val, max);
			}
		}
		dpIncr[i] = max;

		return max;
	}

	public static int findLongestDecreasingSequence(int arr[], int i,
			int dpDecr[]) {

		int j;
		int max = 1;
		int val;

		for (j = i + 1; j < arr.length; j++) {
			// max = 1;
			val = findLongestDecreasingSequence(arr, j, dpDecr) + 1;

			if (arr[i] > arr[j]) {
				max = Math.max(val, max);
			}
		}
		dpDecr[i] = max;

		return max;
	}

}
