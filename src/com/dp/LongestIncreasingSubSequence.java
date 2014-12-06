package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LongestIncreasingSubSequence {

	public static void main(String[] args) throws IOException {
		BufferedReader bReader;
		String str;
		StringTokenizer st;
		List<Integer> aList = new ArrayList<>();
		int[] a;
		int i;
		int max;

		System.out
				.println("Enter the integer array separated by space whose LIS you need: ");
		bReader = new BufferedReader(new InputStreamReader(System.in));

		str = bReader.readLine();
		st = new StringTokenizer(str);

		while (st.hasMoreTokens()) {
			aList.add(Integer.parseInt(st.nextToken()));
		}

		a = new int[aList.size()];

		for (i = 0; i < aList.size(); i++)
			a[i] = aList.get(i);

		max = lis(a);

		System.out.println("Input list: ");
		for (i = 0; i < a.length; i++)
			System.out.print(i + ": " + a[i] + ", ");

		System.out.println();

		System.out.println("LIS of input sequence: " + max);
		max = lisNLogN(a);
		System.out.println("LIS of input sequence using NLogN method: " + max);
	}

	public static int lis(int[] val) {
		int i;
		int j;
		int[] indexes;
		int[] dp;
		int temp;
		int max;
		int min;
		int k;
		int count;

		if (val == null)
			return 0;

		dp = new int[val.length];
		indexes = new int[val.length];

		for (i = 0; i < val.length; i++) {
			dp[i] = 1;
			indexes[i] = i;
		}

		// Find and store the maximum LIS ending at index i

		for (i = 1; i < val.length; i++) {

			max = val[i];

			for (j = 0; j < i; j++) {
				if (val[i] > val[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					indexes[i] = j;
				}

			}
		}

		max = 0;
		j = 0;
		for (i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
			if (dp[i] > max)
				j = i;
			max = Math.max(max, dp[i]);
		}
		System.out.println();

		for (i = 0; i < indexes.length; i++) {
			System.out.print(i + ": " + indexes[i] + ", ");
		}
		// printSolution(indexes, j);
		System.out.println();

		return max;

	}

	// LIS O(NLogN) solution

	public static int lisNLogN(int[] array) {

		int N;
		int l;
		int max = 0;
		ArrayList<Long> ans;

		ans = new ArrayList<Long>();
		N = array.length;

		for (int i = 0; i < N; i++) {

			l = lowerBound(ans, 0, ans.size() - 1, array[i]);

			if (l == ans.size()) {
				ans.add(array[i] * 1L);
			} else {
				ans.set(l, array[i] * 1L);
			}
		}

		max = ans.size();
		return max;
	}

	public static int lowerBound(ArrayList<Long> array, int first, int last,
			long val) {

		int count, step, it;

		count = last - first + 1;

		while (count > 0) {
			it = first;
			step = count / 2;
			it += step;

			if (array.get(it) < val) {
				first = ++it;
				count -= step + 1;
			} else {
				count = step;
			}
		}

		return first;
	}

	public static void printSolution(int[] index, int i) {

		if (index[i] == 1) {
			System.out.print(i + " ");
			return;
		}

		printSolution(index, index[i]);
		System.out.print(i + " ");

	}

}
