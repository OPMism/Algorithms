package com.random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NumSteps {

	public static int N;
	public static long mem[];
	
	public static long totalSteps(int i) {
		
		long count = 0;
		
		if (i == N)
			return 1;
		if (i > N)
			return 0;
		
		if (mem[i] != -1)
			return mem[i];
		
		count = totalSteps(i + 2);
		count += totalSteps(i + 1);
		
		mem[i] = count;
		
		return count;
	}
	
	
	public static void main(String [] args) throws IOException {
//		int N;
		BufferedReader bR;
		long ans;
		
		bR = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the number of steps: ");
		N = Integer.parseInt(bR.readLine());
		
		mem = new long[N + 1];
		
		for (int i = 0; i < N + 1; i++)
			mem[i] = -1;
		
		ans = totalSteps(0);
		
		System.out.println("Total number of ways: " + ans);
	}
}
