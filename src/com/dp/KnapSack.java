package com.dp;

public class KnapSack {

	public static void main(String[] args) {
		int val[] = new int[] { 60, 100, 120, 45 };
		int weight[] = new int[] { 10, 20, 30, 5 };
		int W = 29;
		int maxVal;

		maxVal = findKnapSackRecursion(val, val.length, weight, weight.length,
				W);
		System.out.println("Maximum value: " + maxVal);

		maxVal = knapSackDP(val, weight, weight.length, W);
		System.out.println("Maximum value: " + maxVal);
	}

	public static int knapSackDP(int[] val, int[] wt, int n, int W) {
		int i, j;
		int[][] dp;

		dp = new int[W + 1][n + 1];

		for (i = 1; i <= W; i++) {
			for (j = 1; j <= n; j++) {
				dp[i][j] = dp[i][j - 1];
				if (i >= wt[j - 1])
					dp[i][j] = Math.max(dp[i][j], val[j - 1] + dp[i - wt[j - 1]][j - 1]);
			}
		}

		return dp[W][n];
	}

	public static int findKnapSackRecursion(int[] val, int nV, int[] weight,
			int nW, int W) {

		// int i;
		// int j;
		int max;

		// if (W < 0)
		// return Integer.MIN_VALUE;

		if (nV == 0 || nW == 0)
			return 0;

		if (W == 0)
			return 0;

		if (W < weight[nW - 1])
			return findKnapSackRecursion(val, nV - 1, weight, nW - 1, W);

		max = Math.max(
				val[nV - 1]
						+ findKnapSackRecursion(val, nV - 1, weight, nW - 1, W
								- weight[nW - 1]),
				findKnapSackRecursion(val, nV - 1, weight, nW - 1, W));

		return max;

	}

}
