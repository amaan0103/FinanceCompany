package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.ManagerBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public class ManagerComponent<TContract,TImplementation> implements ManagerBusinessContract {
	TContract dao;
	
	
	public ManagerComponent(TContract dao) {
		this.dao = dao;
	}

	@Override
	public List<FullApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getAllApplications();
	}

	@Override
	public boolean updataStatus(int application_number, String status) throws Exception {
		// TODO Auto-generated method stub
		if(status.toLowerCase().equals("approved"))
			return ((LoanApplicationContract) dao).approve(application_number);
		else
			return ((LoanApplicationContract) dao).reject(application_number);
	}

}
