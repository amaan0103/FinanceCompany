package com.java.entities;

import java.sql.Blob;

public class Documents {
	private long applicationNumber;
	private String documents;
	
	public Documents() {}

	public Documents(long applicationNumber, String documents) {
		this.applicationNumber = applicationNumber;
		this.documents = documents;
	}

	public long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}
}
