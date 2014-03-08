package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KadaneAlgo {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader;
		String str;
		StringTokenizer st;
		List<Integer> aList = new ArrayList<>();
		int[] a;
		int i;
		int max;

		System.out
				.println("Enter the integer array separated by space whose largest contiguous sum you want: ");
		bReader = new BufferedReader(new InputStreamReader(System.in));

		str = bReader.readLine();
		st = new StringTokenizer(str);

		while (st.hasMoreTokens()) {
			aList.add(Integer.parseInt(st.nextToken()));
		}

		a = new int[aList.size()];

		for (i = 0; i < aList.size(); i++)
			a[i] = aList.get(i);

		max = Kadane(a);

		System.out.println("Maximum sum of subarray: " + max);
		System.out.println("Maximum sum of subarray: " + dp(a));

	}

	public static int Kadane(int[] val) {
		int i;
		int sum = val[0];
		int temp = 0;

		for (i = 1; i < val.length; i++) {
			temp = Math.max(val[i], temp + val[i]);
			sum = Math.max(temp, sum);
		}

		return sum;

	}

	public static int dp(int[] val) {
		int i;
		int j;
		// int sum;
		int max;
		int dp[] = new int[val.length];
		int index[] = new int[val.length];

		dp[0] = val[0];

		// Find max contiguous array ending at index i. Then find the max value
		// in the array dp to find the max contiguous array
		for (i = 1; i < val.length; i++) {
			// temp = Math.max(val[i], temp + val[i]);
			// dp[i] = Math.max(val[i], dp[i - 1] + val[i]);
			if (val[i] < dp[i - 1] + val[i]) {
				dp[i] = dp[i - 1] + val[i];
				index[i] = i - 1;
			} else {
				dp[i] = val[i];
				index[i] = i;
			}
		}

		max = dp[0];
		j = 0;
		for (i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
			if (i != 0) {
				if (dp[i] > max)
					j = i;
				max = Math.max(max, dp[i]);
			}
		}
		System.out.println();
		for (i = 0; i < dp.length; i++) {
			System.out.print(i + ": " + index[i] + ", ");
		}
		System.out.println();
		printSolution(index, j);
		System.out.println();
		return max;
	}

	public static void printSolution(int[] index, int i) {

		if (index[i] == i) {
			System.out.print(i + " ");
			return;
		}

		printSolution(index, index[i]);
		System.out.print(i + " ");

	}

}