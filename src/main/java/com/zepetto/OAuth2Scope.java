package com.zepetto;

public enum OAuth2Scope {
	read("read"),
	write("write"),
	trusted("trusted");
	
	private String value;
	
	OAuth2Scope(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
