package com.algorithms;

public class BinaryIndexedTree {

	private int N;
	private long tree[];

	public BinaryIndexedTree(long[] freq) {
		int i;

		N = freq.length;
		tree = new long[N + 1];

		for (i = 1; i <= N; i++) {
			update(i, freq[i - 1]);
		}
	}

	// 1-based index i.e. first index starts from 1
	public long read(int idx) {

		long sum;

		sum = 0;

		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}

		return sum;
	}

	public long readSingle(int idx) {

		int z;
		long sum;

		sum = 0;

		if (idx > 0) {
			sum = tree[idx];
			z = idx - (idx & -idx);
			idx--;
			while (idx != z) {
				sum -= tree[idx];
				idx -= (idx & -idx);
			}
		}

		return sum;
	}

	public void update(int idx, long val) {

		while (idx <= N) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}

	/**
	 * For Range update and single query
	 * 
	 * @param beginIndex
	 *            Beginning index of the range update
	 * @param endIndex
	 *            Last index of the range update
	 * @param val
	 *            Value to update
	 */
	public void updateRange(int beginIndex, int endIndex, long val) {
		update(beginIndex, val);
		update(endIndex + 1, -val);
	}

	public void scaleTree(long c) {
		int i;

		for (i = 0; i <= N; i++) {
			tree[i] /= c;
		}
	}

	public int find(long cumFreq) {

		int idx = 0;
		int bitMask;

		// Get a nubmer with only the most significant bit in N set

		bitMask = N;

		bitMask |= (bitMask >> 1);
		bitMask |= (bitMask >> 2);
		bitMask |= (bitMask >> 4);
		bitMask |= (bitMask >> 8);
		bitMask |= (bitMask >> 16);

		bitMask = (bitMask + 1) >> 1;

		System.out.println("bitMask = " + bitMask);

		while ((bitMask != 0) && (idx < N)) {

			int tIdx = idx + bitMask;

			if (tIdx <= N && cumFreq >= tree[tIdx]) {
				idx = tIdx;
				cumFreq -= tree[tIdx];
			}

			bitMask >>= 1;

		}

		if (cumFreq != 0)
			return -1;
		else
			return idx;
	}

	public void printBIT() {
		int i;

		for (i = 0; i <= N; i++) {
			System.out.println(i + ": " + tree[i]);
		}
	}

	public static void main(String[] args) {
		long[] freq;

		freq = new long[18];

		freq[0] = 1;
		freq[1] = 0;
		freq[2] = 2;
		freq[3] = 1;
		freq[4] = 1;
		freq[5] = 3;
		freq[6] = 0;
		freq[7] = 4;
		freq[8] = 2;
		freq[9] = 5;
		freq[10] = 2;
		freq[11] = 2;
		freq[12] = 3;
		freq[13] = 1;
		freq[14] = 0;
		freq[15] = 2;
		freq[16] = 4;
		freq[17] = 2;

		BinaryIndexedTree BIT = new BinaryIndexedTree(freq);

		System.out.println("Binary Indexed Tree:");

		BIT.printBIT();

		System.out.println("\nRead Single:");

		for (int i = 0; i <= 18; i++) {
			System.out.println(i + ": " + BIT.readSingle(i));
		}

		System.out.println("\nCumulative Frequencies:");

		for (int i = 0; i <= 18; i++) {
			System.out.println(i + ": " + BIT.read(i));
		}

		int index = BIT.find(35);

		System.out.println("Index of 35 = " + index);

	}

}
