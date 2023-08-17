package com.java.businesslayer.implementations;

import java.util.Collections;
import java.util.Comparator;
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

	@Override
	public List<FullApplication> getAllApplications(int sort) throws Exception {
		// TODO Auto-generated method stub
		List<FullApplication> list = ((LoanApplicationContract) dao).getAllApplications();
		Collections.sort(list, new Comparator<FullApplication>() {

			@Override
			public int compare(FullApplication o1, FullApplication o2) {
				// TODO Auto-generated method stub
				if(sort==1)	return o1.getLoanId()-o2.getLoanId();
				else if(sort==2)	return o1.getApplyDate().compareTo(o2.getApplyDate());
				else if(sort==3)	return o1.getLoanTenure()-o2.getLoanTenure();
				else	return (int) (o1.getLoanAmount()-o2.getLoanAmount());
			}
			
		});
		return list;
	}

}
