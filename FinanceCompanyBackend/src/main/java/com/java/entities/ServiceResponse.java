package com.java.entities;



public class ServiceResponse<T> {
	private String message;
	private int statusCode;
	private T responseData;
	public ServiceResponse() {
		
	}
	public ServiceResponse(String mesage, int statusCode, T responseData) {
		this.message = mesage;
		this.statusCode = statusCode;
		this.responseData = responseData;
	}
	public String getMesage() {
		return message;
	}
	public void setMesage(String mesage) {
		this.message = mesage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public T getResponseData() {
		return responseData;
	}
	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
	
}
