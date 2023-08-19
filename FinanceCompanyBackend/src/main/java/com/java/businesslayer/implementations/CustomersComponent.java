package com.java.businesslayer.implementations;

import java.util.List;

import com.java.businesslayer.contracts.CustomersBusinessContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.entities.Customer;

public class CustomersComponent<TContract, TImplementation> implements CustomersBusinessContract {

	TContract dao;
	
	public CustomersComponent(TContract dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean addCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return ((CustomerContract) dao).addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return ((CustomerContract) dao).getAllCustomers();
	}

}
