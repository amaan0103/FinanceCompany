package com.java.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.java.businesslayer.contracts.ClerkBusinessContract;
import com.java.businesslayer.implementations.ClerkComponent;
import com.java.container.Injector;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.Customer;
import com.java.entities.LoanApplication;
import com.java.entities.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clerk")
public class ClerkService {
	
//	Injector injector;
//	CustomerContract dataAccessObject;
//	ClerkBusinessContract businessObject;
	
	public ClerkService() throws Exception {
//		injector = Injector.getInjector();
//		try {
//			dataAccessObject = injector.createInstance(CustomerContract.class);
//			businessObject = injector.createInstance(ClerkComponent.class, businessObject, CustomerContract.class);
//			
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
//	
	
	@POST
	@Path("/addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Customer> addCustomer(Customer c) throws Exception{
		ClerkComponent<CustomerContract,CustomerDataAccess> cc = new ClerkComponent<>( new CustomerDataAccess());
		boolean flag = cc.addCustomer(c);
		if(flag)	return new ServiceResponse<Customer>("added",200,c);
		
		return new ServiceResponse<Customer>("Failed",400,c);
	}
	
	@GET
	@Path("/getcustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Customer>> getCustomers(){
		try {
		ClerkComponent<CustomerContract,CustomerDataAccess> cc = new ClerkComponent<>( new CustomerDataAccess());
		List<Customer> list = cc.getAllCustomers();
		return new ServiceResponse<List<Customer>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<Customer>>(e.getMessage(), 400, null);
		}
	}
	
//	@POST
//	@Path("/addCustomer")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ServiceResponse<LoanApplication> addApplication(LoanApplication app){
//		return null;
//	}
}
