package com.algorithms;

public class KnuthMorrisPratt {

	private int R;
	private int M;
	private int mDFA[][];

	/**
	 * 
	 * @param R
	 *            Radix of the string pattern.
	 * @param pattern
	 */
	public KnuthMorrisPratt(int R, String pattern) {
		int len;
		int X;
		int i, j;

		this.R = R;
		len = pattern.length();
		M = len;

		mDFA = new int[R][len + 1];

		mDFA[pattern.charAt(0)][0] = 1;

		for (i = 1, X = 0; i < len; i++) {

			for (j = 0; j < R; j++) {
				mDFA[j][i] = mDFA[j][X];
			}

			mDFA[pattern.charAt(i)][i] = i + 1;
			X = mDFA[pattern.charAt(i)][X];
		}

		for (j = 0; j < R; j++) {
			mDFA[j][i] = mDFA[j][X];
		}

	}

	public int search(String text) {

		int i, j;
		int N;

		N = text.length();
		j = 0;

		for (i = 0; j < M && i < N; i++) {
			// System.out.println("i = " + i + "; " + text.charAt(i) + "; j = "
			// + j);
			j = mDFA[text.charAt(i)][j];
		}

		if (j == M)
			return i - M;

		return N;

	}

	public int searchAll(String text) {

		int i, j;
		int N;

		N = text.length();
		j = 0;

		for (i = 0; i < N; i++) {
			// System.out.println("i = " + i + "; " + text.charAt(i) + "; j = "
			// + j);
			j = mDFA[text.charAt(i)][j];
			if (j == M) {
				System.out.println("Begin Index: " + (i - M + 1));
			}
		}

		if (j == M)
			return i - M;

		return N;

	}

	public static void main(String[] args) {
		KnuthMorrisPratt kmp;
		String text;
		String pattern;
		
		pattern = "tuxedo";
		pattern = "abaacaa";

		kmp = new KnuthMorrisPratt(256, pattern);
		text = "astuxedldghsldjfkjtkhltalslzxlsdkeoetuxedoasldutaljhhlsdnmvlajldkjftuxedoaslfhsglasfwtpihglzjkcvnxm";

		text = "aafdlhsldkfhsbsldffylkfjlasfabaacaabaacaausaflkhabaacaa";
		int index = kmp.search(text);

		System.out.println("Begin index: " + index);
		System.out.println("Pattern: " + pattern);

		index = kmp.searchAll(text);
		
		for (int i = 0; i < text.length(); i++)
			System.out.println(i + ": " + text.charAt(i));

	}

}
