package com.java.entities;

public class Customer {
	private int customerId;
	private String customerName;
	private String gender;
	private String contact;
	
	public Customer() {}

	public Customer(int customerId, String customerName, String gender, String contact) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.contact = contact;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
