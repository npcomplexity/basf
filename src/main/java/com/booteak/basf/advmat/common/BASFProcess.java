package com.booteak.basf.advmat.common;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class BASFProcess {
	
	private static final String FAST_COMPRESSION_WINDING = "Fast compression winding";
	private static final String PULTRUSION = "Pultrusion";
	private static final String FILAMENT_WINDING = "Filament winding";
	private static final String INFUSION = "Infusion";
	private static final String HIGH_PRESSURE_RTM = "High pressure RTM";
	private static final String STANDARD_RTM = "Standard RTM";
	private static final String WET_LAY_UP = "Wet lay-up";
	
	private static final Map<String, BASFProcess> NAME_TO_PROCESS_MAP = 
			ImmutableMap.<String, BASFProcess>builder()
			.put(WET_LAY_UP, new BASFProcess(0, WET_LAY_UP))
			.put(STANDARD_RTM, new BASFProcess(1,STANDARD_RTM))
			.put(HIGH_PRESSURE_RTM, new BASFProcess(2, HIGH_PRESSURE_RTM))
			.put(INFUSION, new BASFProcess(3, INFUSION))
			.put(FILAMENT_WINDING, new BASFProcess(4, FILAMENT_WINDING))
			.put(PULTRUSION, new BASFProcess(5, PULTRUSION))
			.put(FAST_COMPRESSION_WINDING, new BASFProcess(6, FAST_COMPRESSION_WINDING))
			.build();

	private static final Map<Integer, BASFProcess> ID_TO_PROCESS_MAP = 
			ImmutableMap.<Integer, BASFProcess>builder()
			.put(0, new BASFProcess(0, WET_LAY_UP))
			.put(1, new BASFProcess(1,STANDARD_RTM))
			.put(2, new BASFProcess(2, HIGH_PRESSURE_RTM))
			.put(3, new BASFProcess(3, INFUSION))
			.put(4, new BASFProcess(4, FILAMENT_WINDING))
			.put(5, new BASFProcess(5, PULTRUSION))
			.put(6, new BASFProcess(6, FAST_COMPRESSION_WINDING))
			.build();

	private static final List<BASFProcess> PROCESS_LIST = 
				new ImmutableList.Builder<BASFProcess>().addAll(NAME_TO_PROCESS_MAP.values()).build();
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static List<BASFProcess> getProcesses() {
		return PROCESS_LIST;
	}
	
	public static Map<String, BASFProcess> getNameToProcessMap() {
		return NAME_TO_PROCESS_MAP;
	}

	public static Map<Integer, BASFProcess> getIdToProcessMap() {
		return ID_TO_PROCESS_MAP;
	}

	int id;
	String name;
	int count;
	
	BASFProcess() {
		
	}

	
	BASFProcess(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	BASFProcess(int id, String name, int count) {
		this.id = id;
		this.name = name;
		this.count = count;
	}
	
}