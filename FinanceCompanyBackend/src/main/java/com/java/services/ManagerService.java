package com.java.services;

import java.util.List;

import com.java.businesslayer.implementations.LoanApplicationComponent;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;
import com.java.entities.Role;
import com.java.entities.Secured;
import com.java.entities.ServiceResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/manager")
@Secured(Role.MANAGER)
public class ManagerService {
	public ManagerService() {}
	
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
	
	@POST
	@Path("/status/{applicationNumber}/{status}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> updateApplication(@PathParam("applicationNumber") long applicationNumber, @PathParam("status") String status) throws Exception{
		try {
//		ManagerComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new ManagerComponent<>(new LoanApplicationDataAccess());
			LoanApplicationComponent<LoanApplicationContract,LoanApplicationDataAccess> cc = new LoanApplicationComponent<>(new LoanApplicationDataAccess());
			boolean flag = cc.updataStatus(applicationNumber,status);
			return new ServiceResponse<Boolean>("updated",200,true);
		}catch(Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(),400,false);
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
			List<FullApplication> list = cc.getAllApplications(sort);
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
}
