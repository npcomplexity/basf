package com.booteak.basf.advmat.common;

public class PotLife {
	
	public static final String POTLIFE = "Pot Life";
	public static final String POTLIFE_FACET = "potLife";
	public static final String MIN = "potLife.min";
	public static final String MAX = "potLife.max";
	
	int min;
	int max;

	
	PotLife() {
		
	}
	
	PotLife(int[] arr){
		min = arr[0];
		max = arr[1];
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public static int[][] getPotLifeValues() {
		return POT_LIFE_VALUES;
	}

	static final int[][] POT_LIFE_VALUES = {
		{14,16}, {78,86}, {84,88}, {870,1050}, {600, 720}, 
		
	};
	
	
}