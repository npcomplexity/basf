package com.booteak.basf.advmat.common;


public class ProductFilterOptions {
	private String facetName;
	private String facetId;
	private double min;
	private double max;


	public String getFacetName() {
		return facetName;
	}

	public String getFacetId() {
		return facetId;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	ProductFilterOptions() {
		
	}

	public ProductFilterOptions(String facetName, String facetId, double min, double max) {
		this.facetName = facetName;
		this.facetId = facetId;
		this.min = min;
		this.max = max;
	}

	
}