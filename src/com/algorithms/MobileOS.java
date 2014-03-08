package com.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class MobileOS implements Comparable<MobileOS> {
	
	private int mMarket;
	private String mOs;
	
	MobileOS(int market, String os) {
		mMarket = market;
		mOs = os;		
	}
	
	@Override
	public int compareTo(MobileOS mobileOS) {
		// TODO Auto-generated method stub
		if (mMarket > mobileOS.mMarket)
			return 1;
		else if (mMarket < mobileOS.mMarket)
			return -1;
		return 0;
	}
	
	static final Comparator<MobileOS> MOBILE_ORDER = new Comparator<MobileOS>() {

		@Override
		public int compare(MobileOS m0, MobileOS m1) {
			// TODO Auto-generated method stub
			int x = m0.mOs.compareTo(m1.mOs);
			if (x != 0)
				return x;
			
			return (m0.mMarket > m1.mMarket ? 1 : (m0.mMarket == m1.mMarket ? 0 : -1));
		}
		
	};
	
	public static void main(String args[]) {

		int i;
		MobileOS mArray[] = new MobileOS[5];
		
		mArray[0] = new MobileOS(20, "iOS");
		mArray[1] = new MobileOS(10, "Symbian");
		mArray[2] = new MobileOS(15, "Windows");
		mArray[3] = new MobileOS(5, "Bada");
		mArray[4] = new MobileOS(50, "Android");
		
		Arrays.sort(mArray);
		
		for (i = 0; i < mArray.length; i++) {
			System.out.println("OS Name: " + mArray[i].mOs + "; Market share: " + mArray[i].mMarket);
		}
		
		Arrays.sort(mArray, MOBILE_ORDER);
		
		System.out.println("----------------------");
		
		for (i = 0; i < mArray.length; i++) {
			System.out.println("OS Name: " + mArray[i].mOs + "; Market share: " + mArray[i].mMarket);
		}
		
	}
		
	
}
