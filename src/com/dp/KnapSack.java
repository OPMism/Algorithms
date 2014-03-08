package com.dp;

public class KnapSack {

	public static void main(String[] args) {
		int val[] = new int[] { 60, 100, 120 };
		int weight[] = new int[] { 10, 20, 30 };
		int W = 40;
		int maxVal;

		maxVal = findKnapSackRecursion(val, val.length, weight, weight.length, W);

		System.out.println("Maximum value: " + maxVal);

	}

	public static int findKnapSackRecursion(int[] val, int nV, int[] weight, int nW,
			int W) {

//		int i;
//		int j;
		int max;

		// if (W < 0)
		// return Integer.MIN_VALUE;
		
		if (nV == 0 || nW == 0)
			return 0;

		if (W == 0)
			return 0;
		
		if (W < weight[nW - 1])
			return findKnapSackRecursion(val, nV - 1, weight, nW - 1, W);

		max = Math.max(
				val[nV - 1]
						+ findKnapSackRecursion(val, nV - 1, weight, nW - 1, W
								- weight[nW - 1]),
				findKnapSackRecursion(val, nV - 1, weight, nW - 1, W));

		return max;

	}

}
