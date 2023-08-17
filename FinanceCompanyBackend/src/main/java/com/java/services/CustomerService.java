package com.java.services;

import java.util.List;

import com.java.businesslayer.implementations.CustomerComponent;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.contracts.LoanContract;
import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.dataaccess.implementations.LoanDataAccess;
import com.java.entities.Documents;
import com.java.entities.Loan;
import com.java.entities.LoanApplication;
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
public class CustomerService {

	public CustomerService() {}
	
	@POST
	@Path("/submitApplication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<LoanApplication> submitApplication(LoanApplication app) throws Exception{
		try {
		CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
		cc.addApplication(app);
		return new ServiceResponse<LoanApplication>("added",200,app);
		}catch(Exception e) {
			return new ServiceResponse<LoanApplication>(e.getMessage(),400,null);
		}
	}
	
	@POST
	@Path("/submitDocuments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Documents> submitDocuments(Documents docs) throws Exception{
		try {
			CustomerComponent<DocumentContract,DocumentDataAccess> cc = new CustomerComponent<>(new DocumentDataAccess());
			cc.uploadDocuments(docs);
			return new ServiceResponse<Documents>("added",200,docs);
			}catch(Exception e) {
				return new ServiceResponse<Documents>(e.getMessage(),400,null);
			}
	}
	
	@GET
	@Path("/getAllLoans")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Loan>> getAllLoans(){
		try {
		CustomerComponent<LoanContract,LoanDataAccess> cc = new CustomerComponent<>( new LoanDataAccess());
		List<Loan> list = cc.getLoanDetails();
		return new ServiceResponse<List<Loan>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<Loan>>(e.getMessage(), 400, null);
		}
	}
	
	@DELETE
	@Path("/deleteApplication/{application_number}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<LoanApplication> deleteApplication(@PathParam("appplication_number") int application_number){
		try {
			CustomerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new CustomerComponent<>(new LoanApplicationDataAccess());
			cc.cancelLoan(application_number);
			CustomerComponent<DocumentContract,DocumentDataAccess> dd = new CustomerComponent<>(new DocumentDataAccess());
			dd.deleteDocuments(application_number);
			return new ServiceResponse<LoanApplication>("added",200,null);
			}catch(Exception e) {
				return new ServiceResponse<LoanApplication>(e.getMessage(),400,null);
			}
	}
	
}
