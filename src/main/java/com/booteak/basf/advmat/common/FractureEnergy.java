package com.booteak.basf.advmat.common;

public class FractureEnergy {

	public static final String FRACTUREENERGY = "Fracture Energy";
	public static final String FRACTUREENERGY_FACET = "fractureEnergy";
	public static final String MIN = "fractureEnergy.min";
	public static final String MAX = "fractureEnergy.max";

	int min;
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public static int[][] getFractureEnergy() {
		return FRACTURE_ENERGY;
	}

	int max;
	static final int[][] FRACTURE_ENERGY = {
		{180, 200}, {200, 220}, {220, 240}, {240, 260},
	};
	
	
	FractureEnergy() {
		
	}
	
	FractureEnergy(int[] arr){
		min = arr[0];
		max = arr[1];
	}

}