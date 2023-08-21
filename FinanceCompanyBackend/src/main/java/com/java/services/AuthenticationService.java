package com.java.services;

import com.java.businesslayer.implementations.AuthenticationComponent;
import com.java.businesslayer.implementations.CustomersComponent;
import com.java.dataaccess.contracts.AuthenticationContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.implementations.AuthenticationDataAccess;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.AuthResponse;
import com.java.entities.ServiceResponse;
import com.java.entities.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class AuthenticationService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<AuthResponse> login(User user) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		long flag = ac.login(user);
		if(flag==-1)	return new ServiceResponse<AuthResponse>("incorrect username/pass",400,null);
		ServiceResponse<AuthResponse> sr = new ServiceResponse<AuthResponse>("success",200,new AuthResponse(flag,user.getRole()));
//		sr.setCustomerId(flag);
		return sr;
	}
	
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<AuthResponse> signup(User user) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		long flag = ac.signup(user);
		user.setRole(3);
		if(flag==-1)	return new ServiceResponse<AuthResponse>("incorrect username",400,null);
		ServiceResponse<AuthResponse> sr = new ServiceResponse<AuthResponse>("success",200,new AuthResponse(flag,user.getRole()));
//		sr.setCustomerId(flag);
		return sr;
	}
	
	@POST
	@Path("/changePassword/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> changePassword(@PathParam("customerId") long customerId, String password) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		boolean flag = ac.changePassword(customerId, password);
		if(flag)	return new ServiceResponse<Boolean>("changed successfully",200,flag);
		else		return new ServiceResponse<Boolean>("fail",400,flag);
	}
}
