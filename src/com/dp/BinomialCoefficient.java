package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinomialCoefficient {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		int N;
		int r;
		int[][] dp;

		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Calculates nCr");
		System.out.println("Enter n: ");
		N = Integer.parseInt(bR.readLine());
		System.out.println("Enter r: ");
		r = Integer.parseInt(bR.readLine());

		if (r > N) {
			System.out.println("Invalid input");
			return;
		}

		dp = new int[N + 1][N + 1];
		
		findBinomialCoefficient(dp, N);

		System.out.println(N + "C" + r + " = " + dp[r][N]);

	}

	public static void findBinomialCoefficient(int[][] dp, int n) {

		int i;
		int j;
		int k;

		for (i = 0; i <= n; i++) {
			dp[0][i] = 1;
			dp[i][i] = 1;
		}

		for (i = 1; i < n; i++) {
			for (j = i + 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
			}
		}

	}

}
