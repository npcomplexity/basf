package com.booteak.basf.advmat.common;

public class FractureToughness {

	public static final String FRACTURETOUGHNESS = "Fracture Toughness";
	public static final String FRACTURETOUGHNESS_FACET = "fractureToughness";
	public static final String MIN = "fractureToughness.min";
	public static final String MAX = "fractureToughness.max";

	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public static double[][] getFractureToughnessValues() {
		return FRACTURE_TOUGHNESS_VALUES;
	}

	double min;
	double max;
	String desc;
	static final double[][] FRACTURE_TOUGHNESS_VALUES = {
		{1.0, 1.1}, {2.0, 2.8}, {3.0, 3.7}, {4.0, 5.1}
	};
	
	FractureToughness() {
		
	}
	
	FractureToughness(double[] arr){
		min = arr[0];
		max = arr[1];
	}

}