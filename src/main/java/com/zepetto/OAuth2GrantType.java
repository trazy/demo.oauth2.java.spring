package com.zepetto;

public enum OAuth2GrantType {
	client_credentials("client_credentials"),
	password("password"),
	authorization_code("authorization_code"),
	implicit("implicit"),
	refresh_token("refresh_token");
	
	private String value;
	
	OAuth2GrantType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
