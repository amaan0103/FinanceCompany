package com.java.businesslayer.contracts;

import java.util.List;

import com.java.entities.Customer;

public interface ClerkBusinessContract {
	boolean addCustomer(Customer customer) throws Exception;
	List<Customer> getAllCustomers() throws Exception;
}
