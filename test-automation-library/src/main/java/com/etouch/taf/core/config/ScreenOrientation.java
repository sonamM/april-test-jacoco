package com.etouch.taf.core.config;

public enum ScreenOrientation {

	LANDSCAPE("Landscape"),PORTRAIT("Portrait");
	
	private String orientation;

	public String getOrientation() {
		return orientation;
	}

	private ScreenOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	
	
	
}
