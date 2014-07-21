package com.algorithms;

public class BITRangeUpdateAndQuery {

	private long tree1[];
	private long tree2[];
	private int MAXN;

	public BITRangeUpdateAndQuery(int N) {
		MAXN = N + 1;
		tree1 = new long[MAXN];
		tree2 = new long[MAXN];
	}

	private void update(long[] tree, int idx, long val) {

		while (idx <= MAXN) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}

	private long read(long[] tree, int idx) {
		long sum = 0;

		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}

		return sum;
	}

	public long getRangeSum(int a, int b) {
		return read(b) - read(a - 1);
	}

	private long read(int p) {
		return read(tree1, p) * p - read(tree2, p);
	}

	public void update(int a, int b, long val) {
		update(tree1, a, val);
		update(tree1, b + 1, -val);
		update(tree2, a, val * (a - 1));
		update(tree2, b + 1, -val * b);
	}

	public static void main(String[] args) {
		BITRangeUpdateAndQuery mBit = new BITRangeUpdateAndQuery(10);

		mBit.update(1, 4, 5);
		mBit.update(2, 6, 3);

		System.out.println(mBit.getRangeSum(3, 4));

	}

}
