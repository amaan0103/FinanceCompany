package com.java.entities;

import java.sql.Blob;

public class Documents {
	private int applicationNumber;
	private Blob documents;
	
	public Documents() {}

	public Documents(int applicationNumber, Blob documents) {
		this.applicationNumber = applicationNumber;
		this.documents = documents;
	}

	public int getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Blob getDocuments() {
		return documents;
	}

	public void setDocuments(Blob documents) {
		this.documents = documents;
	}
}
