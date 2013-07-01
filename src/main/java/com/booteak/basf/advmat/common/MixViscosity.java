package com.booteak.basf.advmat.common;

public class MixViscosity {
	
	public static final String MIXVISCOSITY = "Mix Viscosity";
	public static final String MIXVISCOSITY_FACET = "mixViscosity";
	public static final String MIN = "mixViscosity.min";
	public static final String MAX = "mixViscosity.max";
	
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public static int[][] getMixViscosityValues() {
		return MIX_VISCOSITY_VALUES;
	}

	int min;
	int max;
	String desc;
	static final int[][] MIX_VISCOSITY_VALUES = {
		{100, 150}, {200, 300}, {350, 450}, {470, 530},
	};
	
	MixViscosity() {
		
	}
	
	MixViscosity(int[] arr){
		min = arr[0];
		max = arr[1];
	}

}