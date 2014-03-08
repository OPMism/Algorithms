package com.random;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class NoRepeatPlaylist
{
	public static int sMod = 1000000007;
	
	public int numPlaylists(int N, int M, int P)
	{
		int i;
		int j;
		int mem[][];
		int ans;		
		
		mem = new int[N + 1][N + 1];
		
		for (i = 0; i < N + 1; i++) {
			for (j = 0; j < N + 1; j++) {
				mem[i][j] = -1;
			}
		}
		
		ans = numPossible(0, N, P, M);
		
		return ans;
		
	}
	
	
	public int numPossible(int X, int Y, int P, int M) {
	
		long count = 0;
		long temp;
		
		
		if (P == 0)
			return 1;
		
		if (Y > 0) {
			temp = ((1L * numPossible(X + 1, Y - 1, P - 1, M) * Y) % sMod);
			count += temp;
		}
		
		if (X > M) {		
			count += ((numPossible(X - 1, Y + 1, P - 1, M) * (X - M)) % sMod);
		}
		
		System.out.println("Returning count = " + (int) count);
		
		return (int) count;	
	
	}
	
	

}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!