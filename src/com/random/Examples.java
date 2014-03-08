package com.random;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Examples {

	public static List<Integer> sList = new LinkedList<>();

	public static void main(String[] args) {
		
		int x[] = new int[] {1, 5, 10, 24, 50};
		
		int y[][] = new int[][] {{1,2}, {3, 4}};
		
		String xy[] = new String[] {"0", "10", "1000", "143"};
		
		modify(y[1]);
		
		Arrays.sort(xy);

		System.out.println(isD(10000, 40000000, 1000, 40000000));

	}

	public class Cell {
		Node root = null;

		private class Node {
			int nodeVal;
			Node next[];

			public Node(int val, int V) {
				nodeVal = val;
				next = new Node[V];
			}

		}
		
		

	}
	
	public static void modify(int [] x) {
//		n = x.length;
		
		for (int i = 0; i < x.length; i++)
			x[i] = 20 * i;
	}

	public int cellsLeft(int[] parent, int deletedCell) {

		// Node node = new Node();

		return 0;
	}

	public static String isD(int A, int B, int C, int D) {
		double f = Math.pow(A, B);
		double s = Math.pow(C, D);

		if (f % s == 0 || A == C)
			return "divisble";
		else
			return "not divisible";

	}

}
