package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubSequence {

	public static void main(String[] args) throws IOException {

		int M;
		int N;
		int[][] dp;
		String str1;
		String str2;
		BufferedReader bReader;

		bReader = new BufferedReader(new InputStreamReader(System.in));

		// System.out.println("Enter first string: ");
		// str1 = bReader.readLine();
		// System.out.println("Enter second string: ");
		// str2 = bReader.readLine();

		str1 = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
		str2 = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";
		
		str1 = "AGGTAB";
		str2 = "GXTXAYB";

		System.out.println("The two strings are:\n" + str1 + "\n" + str2);

		M = str1.length();
		N = str2.length();
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}

		System.out.println("Longest Common subsequence (recursion): "
				+ findLongestCommonSubsequenceRecursion(str1, str2, M - 1,
						N - 1, dp));

		findLongestCommonSubsequence(str1, str2);

	}

	public static void findLongestCommonSubsequence(String str1, String str2) {

		int i;
		int j;
		int M;
		int N;
		int dp[][];
		int seq[][];

		M = str1.length();
		N = str2.length();

		dp = new int[M][N];
		seq = new int[M][N];

		for (i = 0; i < N; i++) {
			if (str1.charAt(0) == str2.charAt(i))
				dp[0][i] = 1;
		}
		for (i = 0; i < M; i++) {
			if (str1.charAt(i) == str2.charAt(0))
				dp[i][0] = 1;
		}

		for (i = 1; i < M; i++) {
			for (j = 1; j < N; j++) {

				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					seq[i][j] = 0;
				} else {
					if (dp[i - 1][j] > dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						seq[i][j] = -1;
					} else {
						dp[i][j] = dp[i][j - 1];
						seq[i][j] = 1;
					}
				}

			}
		}

		System.out.println("Longest Common Subsequence: " + dp[M - 1][N - 1]);
		printLCS(str1, str2, M - 1, N - 1, seq);

	}

	public static int findLongestCommonSubsequenceRecursion(String str1,
			String str2, int i, int j, int[][] dp) {

		int ret;
		// System.out.println("i = " + i + ", j = " + j);
		if (i == -1 || j == -1)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j)) {
			ret = findLongestCommonSubsequenceRecursion(str1, str2, i - 1,
					j - 1, dp) + 1;
		} else {
			ret = Math.max(
					findLongestCommonSubsequenceRecursion(str1, str2, i - 1, j,
							dp),
					findLongestCommonSubsequenceRecursion(str1, str2, i, j - 1,
							dp));
		}

		dp[i][j] = ret;

		return ret;

	}

	public static void printLCS(String str1, String str2, int i, int j,
			int[][] sequence) {
		
		if (i < 0 || j < 0)
			return;
		
		if (sequence[i][j] == 0) {
			printLCS(str1, str2, i - 1, j - 1, sequence);
			System.out.print(str1.charAt(i));
		} else if (sequence[i][j] == -1) {
			printLCS(str1, str2, i - 1, j, sequence);
		} else {
			printLCS(str1, str2, i, j - 1, sequence);
		}
		
	}

}
