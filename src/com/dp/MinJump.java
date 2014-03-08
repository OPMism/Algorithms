package com.dp;

public class MinJump {

	public static void main(String[] args) {

		int arr[] = { 2, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };

		findMinJumps(arr);

	}

	public static void findMinJumps(int jumpCost[]) {

		int i;
		int j, k;
		int dp[];
		int N;
		int prev;
		int jumps = 0;
		int cur_max = Integer.MIN_VALUE;
		// int temp;
		int seq[];

		N = jumpCost.length;
		dp = new int[N];
		seq = new int[N];

//		dp[0] = jumpCost[0];
//
//		for (i = 1; i < N; i++) {
//			dp[i] = Integer.MAX_VALUE;
//			for (j = 0; j < i; j++) {
//
//				if (dp[j] + jumpCost[j] >= j) {
//					if (jumpCost[j] + dp[j] < dp[i]) {
//						dp[i] = jumpCost[j] + dp[j];
//						seq[i] = j;
//					}
//				}
//			}
//
//		}
		prev = jumpCost[0];
		dp[0] = 0;
		seq[0] = 0;
		jumps = 1;
		j = 0;
		k = 0;
		for (i = 1; i < N; i++) {
			if (jumpCost[i] == 0) {
				break;
			}
			if (prev == 0) {
				prev = cur_max;
				cur_max = Integer.MIN_VALUE;
				jumps++;
				k = j;
			}
//			cur_max = Math.max(jumpCost[i], cur_max);
			if(jumpCost[i] >= cur_max) {
				cur_max = jumpCost[i];
				j = i;
			}

			dp[i] = jumps;
			seq[i] = k;

			prev--;
		}

		printMinJumpsElement(seq, jumpCost, N - 1);

		return;
	}

	public static void printMinJumpsElement(int seq[], int jumpCost[], int i) {

		if (i == 0) {
			System.out.print("cost[" + i + "]: " + jumpCost[i]);
			return;
		}

		printMinJumpsElement(seq, jumpCost, seq[i]);
		System.out.print(" -> ");
		System.out.print("cost[" + i + "]: " + jumpCost[i]);

	}

}
