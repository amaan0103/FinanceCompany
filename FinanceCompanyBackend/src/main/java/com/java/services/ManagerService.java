package com.java.services;

import java.util.List;

import com.java.businesslayer.implementations.ClerkComponent;
import com.java.businesslayer.implementations.ManagerComponent;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;
import com.java.entities.ServiceResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/manager")
public class ManagerService {
	public ManagerService() {}
	
	@GET
	@Path("/getApplications")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<FullApplication>> getApplications(){
		try {
			ClerkComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ClerkComponent<>(new LoanApplicationDataAccess());
		List<FullApplication> list = cc.getAllApplications();
		return new ServiceResponse<List<FullApplication>>("records found",200,list);
		} catch (Exception e) {
			return new ServiceResponse<List<FullApplication>>(e.getMessage(), 400, null);
		}
	}
	
	@POST
	@Path("/status/{applicationNumber}/{status}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> updateApplication(@PathParam("applicationNumber") int applicationNumber, @PathParam("status") String status) throws Exception{
		try {
		ManagerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ManagerComponent<>(new LoanApplicationDataAccess());
		boolean flag = cc.updataStatus(applicationNumber,status);
		return new ServiceResponse<Boolean>("added",200,true);
		}catch(Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),400,false);
		}
	}
}
