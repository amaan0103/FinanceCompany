package com.java.services;

import com.java.businesslayer.implementations.AuthenticationComponent;
import com.java.businesslayer.implementations.CustomersComponent;
import com.java.dataaccess.contracts.AuthenticationContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.implementations.AuthenticationDataAccess;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.AuthResponse;
import com.java.entities.Customer;
import com.java.entities.Role;
import com.java.entities.Secured;
import com.java.entities.ServiceResponse;
import com.java.entities.User;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
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
	public ServiceResponse<AuthResponse> login(@HeaderParam("Role") String role, User user) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		System.out.println(role+" : "+user.getUsername());
		AuthResponse flag = ac.login(user,role);
		if(flag == null)	return new ServiceResponse<AuthResponse>("incorrect username/pass",400,null);
		ServiceResponse<AuthResponse> sr = new ServiceResponse<AuthResponse>("success",200,flag);
//		sr.setCustomerId(flag);
		return sr;
	}
	
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<AuthResponse> signup(@HeaderParam("Role") String role, User user) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		long flag = ac.signup(user);
		if(flag==-1)	return new ServiceResponse<AuthResponse>("incorrect username",400,null);
		ServiceResponse<AuthResponse> sr = new ServiceResponse<AuthResponse>("success",200,new AuthResponse(flag,role));
//		sr.setCustomerId(flag);
		return sr;
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
	@POST
	@Path("/changePassword/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> changePassword(@PathParam("customerId") long customerId, User password) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		boolean flag = ac.changePassword(customerId, password);
		if(flag)	return new ServiceResponse<Boolean>("changed successfully",200,flag);
		else		return new ServiceResponse<Boolean>("fail",400,flag);
	}
	
	@POST
	@Path("/logout")
	//@Secured(Role.MANAGER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> logout(@HeaderParam("Authorization") String token,@HeaderParam("Role") String role) throws Exception{
		AuthenticationComponent<AuthenticationContract,AuthenticationDataAccess> ac = new AuthenticationComponent<>(new AuthenticationDataAccess());
		token = token.substring(7);
		System.out.println("logout service");
		boolean flag = ac.logout(token,role);
		if(!flag)	return new ServiceResponse<Boolean>("unsuccessful logout",400,null);
		return new ServiceResponse<Boolean>("success",200,flag);
	}
}
