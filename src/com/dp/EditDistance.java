package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {

	public static void main(String[] args) throws IOException {
		int dist;
		int m;
		int n;
		int dp[][];
		String strA;
		String strB;

		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Enter first string: ");
		strA = bReader.readLine();
		System.out.println("Enter second string: ");
		strB = bReader.readLine();

		m = strA.length();
		n = strB.length();

		dp = new int[m + 1][n + 1];

		dist = findEditDistanceRecursion(strA, strB, m, n);

		System.out.println("Minimum edit distance recursion: " + dist);

		findEditDistanceDP(dp, strA, strB, m, n);
		System.out.println("Minimum edit distance DP: " + dp[m][n]);

	}

	public static int findEditDistanceRecursion(String strA, String strB,
			int i, int j) {

		int min;

		if (i == 0 && j == 0) {
			// if (strA.charAt(i) == strB.charAt(j))
			// return Math.abs(i - j);
			return 0;

		}
		if (i == 0)
			return j;
		if (j == 0)
			return i;

		min = findEditDistanceRecursion(strA, strB, i - 1, j - 1);
		if (strA.charAt(i - 1) != strB.charAt(j - 1))
			min += 1;
		min = Math
				.min(min, findEditDistanceRecursion(strA, strB, i - 1, j) + 1);
		min = Math
				.min(min, findEditDistanceRecursion(strA, strB, i, j - 1) + 1);

		return min;
	}

	public static void findEditDistanceDP(int[][] dp, String strA, String strB,
			int M, int N) {

		int i;
		int j;
		int left;
		int right;
		int corner;
		int min;

//		if (strA.charAt(0) != strB.charAt(0))
//			dp[0][0] = 1;

		for (i = 1; i <= M; i++) {
//			if (dp[i - 1][0] != i)
//				dp[i][0] = dp[i - 1][0] + 1;
//			else if (strA.charAt(i) == strB.charAt(0) && dp[i - 1][0] == i)
//				dp[i][0] = dp[i - 1][0];
			dp[i][0] = i;
		}
		for (j = 1; j <= N; j++) {
//			if (dp[0][j - 1] != j)
//				dp[0][j] = dp[0][j - 1] + 1;
//			else if (strA.charAt(0) == strB.charAt(j) && dp[0][j - 1] == j)
//				dp[0][j] = dp[0][j - 1];
			dp[0][j] = j;
		}

		for (i = 1; i <= M; i++) {
			for (j = 1; j <= N; j++) {

				left = dp[i - 1][j] + 1;
				right = dp[i][j - 1] + 1;

				corner = dp[i - 1][j - 1];

				if (strA.charAt(i- 1) != strB.charAt(j - 1))
					corner += 1;

				min = Math.min(left, right);
				min = Math.min(min, corner);

				dp[i][j] = min;

			}
		}
		return;
	}

}
