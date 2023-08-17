package com.java.entities;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbDateFormat;

public class Customer {
	private int customerId;
	private String customerName;
	private String gender;
	private String email;
	private String mobile;
	
	public Customer(int customerId, String customerName, String gender, String email, String mobile) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
	}


	public Customer() {}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
}
