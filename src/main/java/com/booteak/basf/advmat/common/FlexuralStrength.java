package com.booteak.basf.advmat.common;

public class FlexuralStrength {
	
	public static final String FLEXURALSTRENGTH = "Flexural Strength";
	public static final String FLEXURALSTRENGTH_FACET = "flexuralStrength";
	public static final String MIN = "flexuralStrength.min";
	public static final String MAX = "flexuralStrength.max";

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	int min;
	int max;
	
	static final int[][] FLEXURAL_STRENGTH_VALUES = {
		{100, 115}, {110, 125}, {130, 145}, {150, 165}
	};
	
	FlexuralStrength() {
		
	}
	
	FlexuralStrength(int[] arr){
		min = arr[0];
		max = arr[1];
	}

}