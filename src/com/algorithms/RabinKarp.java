package com.algorithms;

public class RabinKarp {

	public long RM;
	public int N;
	public int R;
	public int P;
	public long patHash;

	public RabinKarp(String pattern) {

		N = pattern.length();
		RM = 1;

		R = 1000000009;
		P = 10;

		for (int i = 0; i < N - 1; i++) {
			RM = (RM * P) % R;
		}

		patHash = hash(pattern, 0, N);
	}
	
	public boolean isPrime(int num) {
		int i;
		
		if (num <= 1)
			return false;
		if (num <= 3)
			return true;
		
		for (i = 3; i < (int) Math.sqrt(num) + 1; i++) {
			if (num % i == 0)
				return false;
		}
		
		return true;
	}

	public long hash(String str, int startIndex, int length) {

		long hash = 0;

		for (int i = startIndex; i < startIndex + length; i++) {
			hash = (hash * P + str.charAt(i)) % R;
		}

		return hash;

	}

	/**
	 * 
	 * @param text
	 * @return The start index of the pattern match. 0 otherwise.
	 */
	public int search(String text) {

		long hash = 0;
		int textLen;

		textLen = text.length();

		hash = hash(text, 0, N);

		if (hash == patHash)
			return 0;

		for (int i = N; i < textLen; i++) {
			hash = (hash - RM * text.charAt(i - N)) * P + text.charAt(i);
			if (hash == patHash)
				return i - N + 1;
		}

		return -1;
	}

	public static void main(String[] args) {
		int i;
		String pattern = "tuxedo";
		int N = pattern.length();
		String text;

		RabinKarp rKarp = new RabinKarp(pattern);
		text = "astuxedldghsldjfkjtkhltalslzxlsdkeoetuxedoasldutaljhhlsdnmvlajldkjftuxedoaslfhsglasfwtpihglzjkcvnxm";

		i = rKarp.search(text);
		
		System.out.println("Is " + rKarp.R + " prime: " + rKarp.isPrime(rKarp.R));

		if (i != -1)
			System.out.println("Begin index: " + i + "; Pattern: "
					+ text.substring(i, i + N));
		else
			System.out.println("Pattern not found!");

	}

}
