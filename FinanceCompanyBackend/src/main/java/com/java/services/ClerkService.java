package com.java.services;

import java.util.List;

import com.java.businesslayer.implementations.CustomersComponent;
import com.java.businesslayer.implementations.LoanApplicationComponent;
import com.java.businesslayer.implementations.LoanComponent;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.dataaccess.implementations.LoanDataAccess;
import com.java.entities.Customer;
import com.java.entities.FullApplication;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;
import com.java.entities.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clerk")
public class ClerkService {
	
	public ClerkService() throws Exception {
	}
	
	@POST
	@Path("/addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Customer> addCustomer(Customer c) throws Exception{
//		ClerkComponent<CustomerContract,CustomerDataAccess> cc = new ClerkComponent<>( new CustomerDataAccess());
		CustomersComponent<CustomerContract,CustomerDataAccess> cc = new CustomersComponent<>( new CustomerDataAccess());
		boolean flag = cc.addCustomer(c);
		if(flag)	return new ServiceResponse<Customer>("added",200,c);
		
		return new ServiceResponse<Customer>("Failed",400,c);
	}
	
	@GET
	@Path("/getcustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Customer>> getCustomers(){
		try {
//		ClerkComponent<CustomerContract,CustomerDataAccess> cc = new ClerkComponent<>( new CustomerDataAccess());
		CustomersComponent<CustomerContract,CustomerDataAccess> cc = new CustomersComponent<>( new CustomerDataAccess());
		List<Customer> list = cc.getAllCustomers();
		return new ServiceResponse<List<Customer>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<Customer>>(e.getMessage(), 400, null);
		}
	}
	
	@POST
	@Path("/submitApplication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<FullApplication> submitApplication(FullApplication app) throws Exception{
		try {
//		ClerkComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ClerkComponent<>(new LoanApplicationDataAccess());
			System.out.println("here");
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
		boolean flag = cc.addApplication(app);
		return new ServiceResponse<FullApplication>("added",200,app);
		}catch(Exception e) {
			return new ServiceResponse<FullApplication>(e.getMessage(),400,null);
		}
	}
	
	@GET
	@Path("/getApplications")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplications(){
		try {
//			ClerkComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ClerkComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			List<FullApplication> list = cc.getAllApplications();
			return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
	@GET
	@Path("/getApplications/{sort}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplications(@PathParam("sort") int sort){
		//sort: 1. sort by loan type 2. sort by apply_date 3. sort by tenure 4. sort by loan amount
		try {
//			ClerkComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ClerkComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			List<FullApplication> list = cc.getAllApplicationsSorted(sort);
			return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
	
	@GET
	@Path("/getApplicationStatus/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplicationStatus(@PathParam("status") String status){
		//sort: 1. sort by loan type 2. sort by apply_date 3. sort by tenure 4. sort by loan amount
		try {
//			ClerkComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ClerkComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			List<FullApplication> list = cc.getStatus(status);
		return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
	
	@POST
	@Path("/addLoan")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Loan> addLoan(Loan loan) throws Exception{
		try {
//		ClerkComponent<LoanContract,LoanDataAccess> cc = new ClerkComponent<>(new LoanDataAccess());
			LoanComponent<LoanContract,LoanDataAccess> cc = new LoanComponent<>(new LoanDataAccess());
			boolean flag = cc.addLoan(loan);
		return new ServiceResponse<Loan>("added",200,loan);
		}catch(Exception e) {
			return new ServiceResponse<Loan>(e.getMessage(),400,null);
		}
	}
}
