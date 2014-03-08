package com.dp;

public class EggDropping {

	public static void main(String[] args) {
		int i;
		int j;
		int min;
		int dpRecurse[][];
		int dp[][];
		int floors[];
		int egg = 4;
		int floor = 100;
		dpRecurse = new int[egg + 1][floor + 1];
		dp = new int[egg + 1][floor + 1];
		floors = new int[floor + 1];
		for (i = 0; i <= egg; i++) {
			for (j = 0; j <= floor; j++) {
				dpRecurse[i][j] = Integer.MAX_VALUE;
			}
		}
		min = -1;
		// findEggDropTriesRecursion();
		// min = findEggDropTriesRecursion(egg, floor, dpRecurse);

		System.out.println("Minimum number of tries in worst case for " + egg
				+ " eggs and " + floor + " floors (recursion): " + min);

		findEggsDropTries(egg, floor, floors, dp);

		System.out.println("Minimum number of tries in worst case for " + egg
				+ " eggs and " + floor + " floors: " + dp[egg][floor]);

	}

	public static void findEggsDropTries(int eggs, int floor, int [] floors, int[][] dp) {
		int i;
		int j;
		int k;
		int min;
		int max;

		for (i = 0; i <= eggs; i++) {
			dp[i][1] = 1;
		}
		for (i = 0; i <= floor; i++) {
			dp[1][i] = i;
		}

		for (i = 2; i <= eggs; i++) {

			for (j = 2; j <= floor; j++) {

				min = Integer.MAX_VALUE;

				for (k = 0; k < j; k++) {

					max = Math.max(dp[i - 1][k], dp[i][j - k - 1]);
					
					if (max <= min) {
						min = max;
						floors[j] = k;
					}
//					min = Math.min(min, max);

				}

				dp[i][j] = min + 1;

			}

		}
		
		return;

	}

	public static int findEggDropTriesRecursion(int eggs, int floor, int[][] dp) {

		int i;
		int max;
		int ans;

		if (dp[eggs][floor] != Integer.MAX_VALUE)
			return dp[eggs][floor];

		if (floor == 1 || floor == 0)
			return floor;

		if (eggs == 1)
			return floor;

		ans = Integer.MAX_VALUE;

		for (i = 1; i <= floor; i++) {
			max = Math.max(findEggDropTriesRecursion(eggs - 1, i - 1, dp),
					findEggDropTriesRecursion(eggs, floor - i, dp));
			ans = Math.min(max, ans);
		}

		dp[eggs][floor] = ans + 1;

		return dp[eggs][floor];
	}

}
