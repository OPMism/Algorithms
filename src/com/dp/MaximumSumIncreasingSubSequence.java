package com.dp;

public class MaximumSumIncreasingSubSequence {

	public static void main(String[] args) {

		int arr[] = new int[] { 2, 1, 101, 2, 3, 100, 4, 5, 102 };
		int ans;
		int dp[];
		
		dp = new int[arr.length];

		ans = findMaxSumIncreasingSubSequence(arr, arr.length - 1,
				Integer.MAX_VALUE, dp);

		System.out.println("Max sum of increasing subsequence: " + ans);

	}

	public static int findMaxSumIncreasingSubSequence(int arr[], int i, int max, int [] dp) {

		int j;
		int l1;
		int l2;
		// int max;

		if (i < 0)
			return 0;

		// max = Math.max(findMaxSumIncreasingSubSequence(arr, i))

		l1 = Integer.MIN_VALUE;

		if (arr[i] < max) {
			l1 = arr[i] + findMaxSumIncreasingSubSequence(arr, i - 1, arr[i], dp);
		}
		l2 = findMaxSumIncreasingSubSequence(arr, i - 1, max, dp);

		dp[i] =  Math.max(l1, l2);
		return dp[i];
		// return l1;
	}

}
