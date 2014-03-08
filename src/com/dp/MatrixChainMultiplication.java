package com.dp;

public class MatrixChainMultiplication {

	public static void main(String[] args) {
		int[] dim;
		int n;

		n = 6;

		dim = new int[n + 1];

		dim[0] = 30;
		dim[1] = 35;
		dim[2] = 15;
		dim[3] = 5;
		dim[4] = 10;
		dim[5] = 20;
		dim[6] = 25;

		matrixChainMultiplication(dim, n);

	}

	public static void matrixChainMultiplication(int[] dim, int n) {

		int i;
		int j;
		int k;
		int l;
		int temp;
		int dp[][];
		int sol[][];

		dp = new int[n][n];
		sol = new int[n][n];

		for (i = 0; i < n; i++) {
			for (j = i; j < n; j++) {
				if (i == j)
					dp[i][j] = 0;
				else
					dp[i][j] = Integer.MAX_VALUE;
			}
		}

		// Iterate over length/number of matrices which are to be multiplied
		for (l = 2; l <= n; l++) {

			for (i = 0; i <= n - l; i++) {

				j = i + l - 1;

				for (k = i; k < j; k++) {

					temp = dp[i][k] + dp[k + 1][j] + dim[i] * dim[k + 1]
							* dim[j + 1];

					if (temp < dp[i][j]) {
						dp[i][j] = temp;
						sol[i][j] = k;
					}
				}
			}
		}

		printOptimalSol(sol, 0, n - 1);

		return;

	}

	public static void printOptimalSol(int[][] sol, int i, int j) {

		if (i == j)
			System.out.print("A" + i);
		else {
			System.out.print("(");
			printOptimalSol(sol, i, sol[i][j]);
			printOptimalSol(sol, sol[i][j] + 1, j);
			System.out.print(")");
		}

	}

}
