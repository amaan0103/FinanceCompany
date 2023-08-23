package com.java.services;

import java.util.List;

import com.java.businesslayer.implementations.CustomersComponent;
import com.java.businesslayer.implementations.DocumentComponent;
import com.java.businesslayer.implementations.LoanApplicationComponent;
import com.java.businesslayer.implementations.LoanComponent;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.dataaccess.implementations.LoanDataAccess;
import com.java.entities.Customer;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;
import com.java.entities.Role;
import com.java.entities.Secured;
import com.java.entities.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/customer")
//@Secured(Role.CUSTOMER)
public class CustomerService {

	public CustomerService() {}
	
	@POST
	@Path("/submitApplication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<FullApplication> submitApplication(FullApplication app) throws Exception{
		try {
//		CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			cc.addApplication(app);
		return new ServiceResponse<FullApplication>("added",200,app);
		}catch(Exception e) {
			return new ServiceResponse<FullApplication>(e.getMessage(),400,null);
		}
	}
	
	@GET
	@Path("/getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Customer> getCustomers(@PathParam("customerId") long customerId){
		try {
//		ClerkComponent<CustomerContract,CustomerDataAccess> cc = new ClerkComponent<>( new CustomerDataAccess());
		CustomersComponent<CustomerContract,CustomerDataAccess> cc = new CustomersComponent<>( new CustomerDataAccess());
		List<Customer> list = cc.getAllCustomers();
		Customer c = null;
		for(Customer customer : list) {
			if(customer.getCustomerId()==customerId) {
				c = customer;
				break;
			}
		}
		return new ServiceResponse<Customer>("records found",200,c);
		} catch (Exception e) {
			return new ServiceResponse<Customer>(e.getMessage(), 400, null);
		}
	}
	
	@GET
	@Path("/getAllLoans")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Loan>> getAllLoans(){
		try {
//		CustomerComponent<LoanContract,LoanDataAccess> cc = new CustomerComponent<>( new LoanDataAccess());
			LoanComponent<LoanContract,LoanDataAccess> cc = new LoanComponent<>(new LoanDataAccess());
			List<Loan> list = cc.getLoanDetails();
		return new ServiceResponse<List<Loan>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<Loan>>(e.getMessage(), 400, null);
		}
	}
	
	@DELETE
	@Path("/deleteApplication/{applicationNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<LoanApplication> deleteApplication(@PathParam("applicationNumber") long applicationNumber){
		try {
//			CustomerComponent<DocumentContract,DocumentDataAccess> dd = new CustomerComponent<>(new DocumentDataAccess());
			DocumentComponent<DocumentContract,DocumentDataAccess> dd = new DocumentComponent<>(new DocumentDataAccess());
			dd.deleteDocuments(applicationNumber);
//			CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			cc.cancelLoan(applicationNumber);
			return new ServiceResponse<LoanApplication>("deleted",200,null);
			}catch(Exception e) {
				return new ServiceResponse<LoanApplication>(e.getMessage(),400,null);
			}
	}
	
	@GET
	@Path("/getApplicationsCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplications(@PathParam("customerId") long customerId){
		try {
//			CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			List<FullApplication> list = cc.getAllApplications(customerId);
		return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
	
	@GET
	@Path("/getApplications/{customerId}/{sort}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplications(@PathParam("customerId") long customerId, @PathParam("sort") int sort){
		//sort: 1. sort by loan type 2. sort by apply_date 3. sort by tenure 4. sort by loan amount
		try {
//			CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
		List<FullApplication> list = cc.getAllApplications(customerId,sort);
		return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
}
