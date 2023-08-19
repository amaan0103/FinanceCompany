package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public interface ManagerBusinessContract {
	List<FullApplication> getAllApplications() throws Exception;
	List<FullApplication> getAllApplications(int sort) throws Exception;
	boolean updataStatus(long application_number, String status) throws Exception;
	
}
