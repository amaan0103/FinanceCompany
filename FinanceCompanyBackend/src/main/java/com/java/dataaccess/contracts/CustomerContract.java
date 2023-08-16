package com.java.dataaccess.contracts;

import java.util.List;

import com.java.entities.Customer;

public interface CustomerContract{
	boolean addCustomer(Customer customer) throws Exception ;
	List<Customer> getAllCustomers() throws Exception;
}
