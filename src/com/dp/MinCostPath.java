package com.dp;

public class MinCostPath {

	public static void main(String[] args) {
		int i;
		int j;
		int N;
		long start, end;
		int costMatrix[][];
		int[][] costRecurseDP;

		System.out.println("Cost matrix: ");

		N = 1000;
		costMatrix = new int[N][N];
		costRecurseDP = new int[N][N];
		// costMatrix[0][0] = 1;
		// costMatrix[0][1] = 2;
		// costMatrix[0][2] = 3;
		// costMatrix[1][0] = 4;
		// costMatrix[1][1] = 8;
		// costMatrix[1][2] = 2;
		// costMatrix[2][0] = 1;
		// costMatrix[2][1] = 5;
		// costMatrix[2][2] = 3;

		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				costMatrix[i][j] = (int) (100 * Math.random());
				costRecurseDP[i][j] = Integer.MIN_VALUE;
				System.out.print(costMatrix[i][j] + " ");
			}
			System.out.println();
		}
		start = System.currentTimeMillis();
		System.out.println("Min Cost to reach (N - 1, N - 1): "
				+ minCostPathRecurse(costRecurseDP, costMatrix, 0, 0, N));
		end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start) + " (ms)");
		System.out.println("Min cost path: "
				+ minCostPathBottomUp(costMatrix, N));
		start = System.currentTimeMillis();
		System.out.println("Time taken: " + (start - end) + " (ms)");

	}

	public static int minCostPathBottomUp(int cost[][], int N) {

		int i;
		int j;
		int dp[][];
		int min;

		dp = new int[N][N];

		dp[0][0] = cost[0][0];

		for (i = 1; i < N; i++) {
			dp[0][i] = dp[0][i - 1] + cost[0][i];
			dp[i][0] = dp[i - 1][0] + cost[i][0];
		}

		for (i = 1; i < N; i++) {
			for (j = 1; j < N; j++) {
				min = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
				min = Math.min(min, dp[i][j - 1]);
				dp[i][j] = min + cost[i][j];
			}
		}

		return dp[N - 1][N - 1];
	}

	public static int minCostPathRecurse(int costRecurseDP[][], int cost[][],
			int i, int j, int N) {

		int val;
		int valH;
		int valD;
		int valV;
		int min;

		if (i >= N || j >= N)
			return Integer.MAX_VALUE;

		if (costRecurseDP[i][j] != Integer.MIN_VALUE)
			return costRecurseDP[i][j];

		if (i == N - 1 && j == N - 1)
			return cost[i][j];

		val = cost[i][j];

		valH = minCostPathRecurse(costRecurseDP, cost, i, j + 1, N);
		valD = minCostPathRecurse(costRecurseDP, cost, i + 1, j + 1, N);
		valV = minCostPathRecurse(costRecurseDP, cost, i + 1, j, N);

		min = Math.min(valH, valD);
		min = Math.min(min, valV);

		val += min;
		costRecurseDP[i][j] = val;

		return val;

	}

}
