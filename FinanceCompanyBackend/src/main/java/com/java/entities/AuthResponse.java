package com.java.entities;

public class AuthResponse {
	private long customerId;
	private String  token;
	public AuthResponse(long customerId, String token) {
		super();
		this.customerId = customerId;
		this.token = token;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getToken() {
		return token;
	}
	public void setRole(String token) {
		this.token = token;
	}
}
