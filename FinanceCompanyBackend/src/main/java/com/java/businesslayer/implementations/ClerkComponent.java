package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.ClerkBusinessContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.entities.Customer;

public class ClerkComponent implements ClerkBusinessContract {

	CustomerContract customerDAO;
	
	public ClerkComponent(CustomerContract customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public boolean addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return customerDAO.addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return customerDAO.getAllCustomers();
	}

}
