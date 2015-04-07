package com.etouch.taf.tools.defect;

public enum DefectTools {
	RALLY("Rally"),
	JIRA("Jira");
	
	private String tool;
	
	public String getTool() {
		return tool;
	}

	DefectTools(String tool){
		this.tool = tool;
	}
	
	public static boolean isSupported(String toolName){
		DefectTools[] dtList = DefectTools.values();
		for(DefectTools dt : dtList){
			if(dt.getTool().equalsIgnoreCase(toolName)){
				return true;
			}
		}
		return false;
	}
}
