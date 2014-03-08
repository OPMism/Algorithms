package com.random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Codeforces {
	
	public static void main (String [] args) throws IOException{
		
		String input;
		BufferedReader bReader;
		bReader = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Enter the string: ");
		input = bReader.readLine();
		
		int ans = solve(input);
		
		System.out.println(ans);
	}
	
	public static int solve(String input) {
		int N;
		int i, j;
		int count = 0;
		
		N = input.length();
		
		for (i = 0; i <= N - 3; i++) {
			for (j = i + 3; j < N; j++) {
				if (input.substring(i, j + 1).contains("bear"))
					count++;
			}
		}
		
		
		return count;
	}

}
