package com.dp;

public class LIS {
	
	public static void lis(int [] arr) {
		int i, j;
		int n;
		int max;
		int dp[];
		int prev[];
		
		n = arr.length;
		dp = new int[n];
		prev = new int[n];
		
		dp[0] = 1;
		prev[0] = 0;
		
		for (i = 1; i < n; i++) {
			max = 1;
			prev[i] = i;
			for (j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (dp[j] + 1 > max) {
						prev[i] = j;
						max = dp[j] + 1;
					}
//					max = Math.max(max, dp[j] + 1);
				}
			}
			
			dp[i] = max;
			
		}
		
		System.out.println("Max lis length: " + dp[n - 1]);
		
		printLIS(prev, arr, n - 1);
		
	}
	
	public static void printLIS(int [] prev, int [] arr, int i) {
		
		if (i == prev[i]) {
			System.out.print(arr[i] + " ");
			return;
		}
		
		printLIS(prev, arr, prev[i]);
		
		System.out.print(arr[i] + " ");
		
	}
	
	public static void main(String [] args) {
		int arr[] = new int [] {2, 4, 3, 5, 1, 7, 6, 9, 8};
		
		lis(arr);
	}

}
