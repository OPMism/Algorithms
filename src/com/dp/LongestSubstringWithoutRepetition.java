package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LongestSubstringWithoutRepetition {

	public static void main(String[] args) throws IOException {
		int[] dp;
		String strA;

		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Enter string: ");
		strA = bReader.readLine();

		dp = new int[strA.length()];

		findLongestSubStringDP(strA, dp);

		System.out.println("Length of the longest substring without repetition: "
				+ dp[strA.length() - 1]);
	}

	public static void findLongestSubStringDP(String str, int[] dp) {

		int i;
		int j;
		int max;
		int prevIndex;
		int curIndex;
		int len;

		// HashSet<Character> hashSet = new HashSet<>();
		// Queue<Character> queue = new ArrayDeque<>();

		HashMap<Character, Integer> hashMap = new HashMap<>();

		max = 0;
		prevIndex = 0;
		curIndex = 0;
		for (i = 0; i < str.length(); i++) {

			if (hashMap.containsKey(str.charAt(i))) {

				j = hashMap.get(str.charAt(i)) + 1;
				if (j > prevIndex)
					prevIndex = j;

			}
			hashMap.put(str.charAt(i), i);

			len = i - prevIndex + 1;
			max = Math.max(len, max);

		}

		dp[str.length() - 1] = max;

	}

	public static void findLongestSubstringWithoutRepetitionBruteForce(
			String str) {

	}

}
