package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestPalindromicSequence {

	public static void main(String[] args) throws IOException {

		int i;
		String str;

		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));

		System.out
				.println("Please enter a string whose longest palindrome sequence you want: ");
		str = bReader.readLine();

		findLongestPalindromicSubSequence(str);

	}

	public static void findLongestPalindromicSubSequence(String str) {
		int i;
		int j;
		int k;
		int l;
		int N;
		int[][] dp;
		int pal[][];

		N = str.length();

		dp = new int[N][N];
		pal = new int[N][N];

		for (i = 0; i < N; i++) {
			dp[i][i] = 1;
		}

		for (l = 2; l <= N; l++) {

			for (i = 0; i < N - l + 1; i++) {

				j = i + l - 1;

				if (l == 2 && str.charAt(i) == str.charAt(j)) {
					pal[i][j] = 0; // 0 means that i, j are equal and the next
									// longest palindrome is b/w (i - 1, j - 1)
					dp[i][j] = 2;
				}

				else if (str.charAt(i) == str.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
					pal[i][j] = 0;
				}

				else {
					if (dp[i][j - 1] >= dp[i + 1][j]) {
						dp[i][j] = dp[i][j - 1];
						pal[i][j] = -1; // -1 means the longest palindrome lies
										// on the left side i.e. (i , j - 1)
					} else {
						dp[i][j] = dp[i + 1][j];
						pal[i][j] = 1; // 1 means the longest palindrome lies on
										// the right side i.e.e (i + 1, j)
					}
				}

			}

		}

		System.out
				.println("The length of the longest palindromic subsequence is: "
						+ dp[0][N - 1]);

		printLongestPalindrome(str, pal, 0, N - 1, N);
	}

	public static void printLongestPalindrome(String str, int[][] pal, int i,
			int j, int N) {

		if (i > j)
			return;

		if (i == j) {
			System.out.print(str.charAt(i));
			return;
		}

		if (pal[i][j] == 0) {
			System.out.print(str.charAt(i));
			printLongestPalindrome(str, pal, i + 1, j - 1, N);
		}

		else if (pal[i][j] == -1)
			printLongestPalindrome(str, pal, i, j - 1, N);

		else
			printLongestPalindrome(str, pal, i + 1, j, N);

		if (pal[i][j] == 0)
			System.out.print(str.charAt(j));

	}

}
