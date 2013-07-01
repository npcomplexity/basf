package com.booteak.basf.advmat.common;

public class GelTime {
	
	public static final String GELTIME = "Gel Time";
	public static final String GELTIME_FACET = "gelTime";
	public static final String MIN = "gelTime.min";
	public static final String MAX = "gelTime.max";

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public String getDesc() {
		return desc;
	}

	public static int[][] getGelTimeValues() {
		return GEL_TIME_VALUES;
	}
	int min;
	int max;
	String desc;
	static final int[][] GEL_TIME_VALUES = {
		{7,10}, {15, 25}, {30,40}, {45, 55},
	};
	
	GelTime() {
		
	}
	
	GelTime(int[] arr){
		min = arr[0];
		max = arr[1];
		desc = min + "-" + max;
	}
	public String toString() {
		return desc;
	}
}