package com.dp;

public class SubsetSum {

	public static boolean subsetSum(int[] set, int sum) {
		int i, j, k;
		int n;
		boolean[][] dp;

		n = set.length;

		dp = new boolean[sum + 1][n + 1];

		for (i = 0; i <= n; i++) {
			dp[0][i] = true;
		}

		for (i = 1; i <= sum; i++) {
			dp[i][0] = false;
		}

		for (i = 1; i <= sum; i++) {

			for (j = 1; j <= n; j++) {
				dp[i][j] = dp[i][j - 1];

				if (i >= set[j - 1])
					dp[i][j] |= dp[i - set[j - 1]][j - 1];
			}
		}
		
		for (i = 0; i <= sum; i++) {
			for (j = 0; j <= n ;j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		return dp[sum][n];

	}

	public static void main(String[] args) {
		int set[] = { 3, 34, 4, 12, 5, 2 };
		boolean present;
		int sum = 9;

		present = subsetSum(set, sum);

		System.out.println("Sum Present: " + present);

	}

}
