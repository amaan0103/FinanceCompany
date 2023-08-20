package com.java.entities;

public class AuthResponse {
	private long customerId;
	private int role;
	public AuthResponse(long customerId, int role) {
		super();
		this.customerId = customerId;
		this.role = role;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
}
