package com.dp;

public class LCS {

	public static int mem[][];

	public static int longestCommonSubSequence(String s1, String s2, int i,
			int j) {
		int max;
		int m;
		max = 0;

		if (i == -1 || j == -1)
			return 0;

		if (mem[i][j] != -1)
			return mem[i][j];

		if (s1.charAt(i) == s2.charAt(j)) {
			max = 1 + longestCommonSubSequence(s1, s2, i - 1, j - 1);
		}

		m = Math.max(longestCommonSubSequence(s1, s2, i - 1, j),
				longestCommonSubSequence(s1, s2, i, j - 1));

		max = Math.max(max, m);

		return (mem[i][j] = max);
	}

	public static void findLongestCommonSubSeqDP(String s1, String s2) {
		int i, j;
		int m, n;
		int max;
		int[][] dp;
		char[][] index;

		m = s1.length();
		n = s2.length();

		dp = new int[m + 1][n + 1];
		index = new char[m + 1][n + 1];

		for (i = 1; i <= m; i++) {
			for (j = 1; j <= n; j++) {
				max = 0;
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					max = 1 + dp[i - 1][j - 1];

				if (max > dp[i - 1][j] && max > dp[i][j - 1])
					index[i][j] = 'd';
				else if (dp[i - 1][j] > dp[i][j - 1])
					index[i][j] = 'u';
				else
					index[i][j] = 'l';
				dp[i][j] = Math.max(max, Math.max(dp[i - 1][j], dp[i][j - 1]));
			}
		}

		System.out.println("Max subsequence length: " + dp[m][n]);
		System.out.println("Subsequence: ");
		printSubSequence(s1, index, m, n);
	}

	public static void printSubSequence(String s1, char[][] index, int i, int j) {

		if (i == 0 || j == 0)
			return;
		
		if (index[i][j] == 'd') {
			printSubSequence(s1, index, i - 1, j - 1);
			System.out.print(s1.charAt(i - 1));
		} else if (index[i][j] == 'l') {
			printSubSequence(s1, index, i, j - 1);
		} else {
			printSubSequence(s1, index, i - 1, j);
		}

		// if (1 + dp[i - 1][j - 1] > dp[i - 1][j] && )

	}

	public static void main(String[] args) {
		int m, n, max;
		String s1, s2;
		FastScannerSlow fS = new FastScannerSlow();

		System.out.println("Enter first string: ");
		s1 = fS.nextLine();
		System.out.println("Enter second string: ");
		s2 = fS.nextLine();

		m = s1.length();
		n = s2.length();

		mem = new int[m][n];

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				mem[i][j] = -1;

		max = longestCommonSubSequence(s1, s2, m - 1, n - 1);

		System.out.println("Longest subsequence: " + max);

		findLongestCommonSubSeqDP(s1, s2);
	}
}
