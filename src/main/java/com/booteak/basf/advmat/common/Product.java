package com.booteak.basf.advmat.common;

import java.util.Random;


public class Product {
	public static final String ID = "id";
	public static final String SKU = "sku";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PROCESS = "process";
	public static final String POTLIFE = "potLife";
	public static final String GELTIME = "gelTime";
	public static final String MIXVISCOSITY = "mixViscosity";
	public static final String FLEXURALSTRENGTH = "flexuralStrength";
	public static final String FRACTUREENERGY = "fractureEnergy";
	public static final String FRACTURETOUGHNESS = "fractureToughness";
			
	
	private static final Random RN = new Random();
	
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] NUMBERS = "0123456789".toCharArray();
	
	private static int PRODUCT_ID_COUNTER = 1000;
	
	private static final String[] PRODUCT_NAME_PREFIXES = {
		"Araldite LY",
		"Araldite RU",
		"Araldite XB",
		"Resin XB",
		"Renlam LY",
		
	};
	
	private static final String[] DESCRIPTION_VALUES = 
		{
			"Extremely low-viscosity, anhydride-cured, reactive diluent free matrix system with a long pot life. Displays very good temperature resistance after post cure.",
			"Optimally filled epoxy casting resin resin usually cured at room temperature.",
			"Araldite 2012 is a rapid cure, multipurpose, two component, room temperature curing, high viscosity liquid adhesive of high strength and toughness.",
			"Low viscosity, unfilled epoxy casting resin system, curing at room temperature. High filler addition possibility",
		};
	
	public static int getPRODUCT_ID_COUNTER() {
		return PRODUCT_ID_COUNTER;
	}

	public static void setPRODUCT_ID_COUNTER(int pRODUCT_ID_COUNTER) {
		PRODUCT_ID_COUNTER = pRODUCT_ID_COUNTER;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public PotLife getPotLife() {
		return potLife;
	}

	public void setPotLife(PotLife potLife) {
		this.potLife = potLife;
	}

	public GelTime getGelTime() {
		return gelTime;
	}

	public void setGelTime(GelTime gelTime) {
		this.gelTime = gelTime;
	}

	public MixViscosity getMixViscosity() {
		return mixViscosity;
	}

	public void setMixViscosity(MixViscosity mixViscosity) {
		this.mixViscosity = mixViscosity;
	}

	public FlexuralStrength getFlexuralStrength() {
		return flexuralStrength;
	}

	public void setFlexuralStrength(FlexuralStrength flexuralStrength) {
		this.flexuralStrength = flexuralStrength;
	}

	public FractureToughness getFractureToughness() {
		return fractureToughness;
	}

	public void setFractureToughness(FractureToughness fractureToughness) {
		this.fractureToughness = fractureToughness;
	}

	public FractureEnergy getFractureEnergy() {
		return fractureEnergy;
	}

	public void setFractureEnergy(FractureEnergy fractureEnergy) {
		this.fractureEnergy = fractureEnergy;
	}

	public static Random getRn() {
		return RN;
	}

	public static char[] getAlphabet() {
		return ALPHABET;
	}

	public static char[] getNumbers() {
		return NUMBERS;
	}

	public static String[] getProductNamePrefixes() {
		return PRODUCT_NAME_PREFIXES;
	}

	public static String[] getDescriptionValues() {
		return DESCRIPTION_VALUES;
	}

	int id;
	String name;
	String sku;
	String description;
	String process;
	PotLife potLife;
	GelTime gelTime;
	MixViscosity mixViscosity;
	FlexuralStrength flexuralStrength;
	FractureToughness fractureToughness;
	FractureEnergy fractureEnergy;
	
	
	public static Product newInstance() {
		Product p = new Product();
		p.id = PRODUCT_ID_COUNTER++;
		p.name = PRODUCT_NAME_PREFIXES[RN.nextInt(PRODUCT_NAME_PREFIXES.length)] + " " + p.id;
		p.sku = new String(new char[]{
				ALPHABET[RN.nextInt(ALPHABET.length)],
				ALPHABET[RN.nextInt(ALPHABET.length)],
				ALPHABET[RN.nextInt(ALPHABET.length)],
				ALPHABET[RN.nextInt(ALPHABET.length)],
				NUMBERS[RN.nextInt(NUMBERS.length)],
				NUMBERS[RN.nextInt(NUMBERS.length)],
				NUMBERS[RN.nextInt(NUMBERS.length)],
				NUMBERS[RN.nextInt(NUMBERS.length)],
						});
		p.description = DESCRIPTION_VALUES[RN.nextInt(DESCRIPTION_VALUES.length)];
		p.process = BASFProcess.getProcesses().get(RN.nextInt(BASFProcess.getProcesses().size())).getName();
		p.potLife = new PotLife(PotLife.POT_LIFE_VALUES[RN.nextInt(PotLife.POT_LIFE_VALUES.length)]);
		p.gelTime = new GelTime(GelTime.GEL_TIME_VALUES[RN.nextInt(GelTime.GEL_TIME_VALUES.length)]);
		p.mixViscosity = 
				new MixViscosity(MixViscosity.MIX_VISCOSITY_VALUES[RN.nextInt(MixViscosity.MIX_VISCOSITY_VALUES.length)]);
		p.flexuralStrength = 
				new FlexuralStrength(FlexuralStrength.FLEXURAL_STRENGTH_VALUES[RN.nextInt(FlexuralStrength.FLEXURAL_STRENGTH_VALUES.length)]);
		p.fractureToughness = 
				new FractureToughness(FractureToughness.FRACTURE_TOUGHNESS_VALUES[RN.nextInt(FractureToughness.FRACTURE_TOUGHNESS_VALUES.length)]);
		p.fractureEnergy = 
				new FractureEnergy(FractureEnergy.FRACTURE_ENERGY[RN.nextInt(FractureEnergy.FRACTURE_ENERGY.length)]);
				
		return p;
	}
				
}