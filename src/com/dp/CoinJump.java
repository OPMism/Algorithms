package com.dp;

/**
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.

 For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CoinJump {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		int N;
		int[] dp;
		int coins[];
		int count;

		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Enter the coin value: ");
		N = Integer.parseInt(bReader.readLine());
		dp = new int[N + 1];
		coins = new int[] { 3, 5, 4, 1, 2 };

		Arrays.sort(coins);
		count = findWaysRecursion(coins, coins.length, N);
		System.out.print("Number of possible ways: " + count);
		// findWays(coins, dp, N);
	}

	public static void findWays(int coins[], int dp[], int N) {

		int i;
		int j;

		Arrays.sort(coins);

		for (i = 1; i <= N; i++) {

			for (j = 0; j < coins.length; j++) {

			}

		}

	}

	public static int findWaysRecursion(int coins[], int n, int N) {

		int i;
		int j;
		int count = 0;

		if (N == 0) {
			return 1;
		}
		if (n == 0 || N < 0)
			return 0;

		// for (i = 0; i < coins.length; i++) {

		// if (N - coins[i] >= 0) {
		count = findWaysRecursion(coins, n, N - coins[n - 1])
				+ findWaysRecursion(coins, n - 1, N);

		return count;
	}

}
