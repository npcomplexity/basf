package com.booteak.basf.advmat.common;

public class ProductCompact {
	String name;
	String sku;
	int id;
	
	public ProductCompact() {
		
	}
	
	public ProductCompact(String name, String sku, int id) {
		super();
		this.name = name;
		this.sku = sku;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "name : " + name + ", sku = " + sku + ", id = " + id;
	}
}