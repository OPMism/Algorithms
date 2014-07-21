package com.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MaximumProductCutting {

	public static void maxProductCut(int n) {
		int i, j;
		int [] dp;
		
		dp = new int[n + 1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		for (i = 2; i <= n; i++) {
			for (j = 0; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[j] * (i - j));
			}
		}
		
		for (i = 0; i <= n; i++)
			System.out.print(dp[i] + " ");
		
	}
	
	public static void main(String [] args) throws IOException {
		int i;
		int n;
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
		
		out.println("Enter the length of the rope: ");
		out.flush();
		n = Integer.parseInt(bR.readLine());
		
		maxProductCut(n);
		out.println();
		out.flush();
		out.close();
		
	}
}
