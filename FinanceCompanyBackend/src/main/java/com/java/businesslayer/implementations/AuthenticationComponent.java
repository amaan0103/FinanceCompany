package com.java.businesslayer.implementations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.java.businesslayer.contracts.AuthenticationBusinessContract;
import com.java.dataaccess.contracts.AuthenticationContract;
import com.java.dataaccess.implementations.AuthenticationDataAccess;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.AuthResponse;
import com.java.entities.Customer;
import com.java.entities.RandomString;
import com.java.entities.User;

public class AuthenticationComponent<TContract, TImplementation> implements AuthenticationBusinessContract {

	TContract dao;
	
	public AuthenticationComponent(TContract dao) {
		super();
		this.dao = dao;
	}

	@Override
	public long signup(User user) throws Exception {
		// TODO Auto-generated method stub
		User userDB =((AuthenticationDataAccess) dao).getUser(user.getUsername());
		if(userDB != null)	return -1;
		System.out.println("reaching");
		user.setPassword(hashPasswords(user.getPassword()));;
		boolean flag = ((AuthenticationDataAccess) dao).addUser(user);
		if(!flag)return -1;
		System.out.println("reaching here too");
		CustomerDataAccess cdao = new CustomerDataAccess();
		Customer c = cdao.getCustomerByEmail(user.getUsername());
		return c.getCustomerId();
	}

	@Override
	public AuthResponse login(User user, String role) throws Exception {
		// TODO Auto-generated method stub
		User userDB = null;
		
		if(role.equals("MANAGER"))	userDB=((AuthenticationDataAccess) dao).getManager(user.getUsername());
		else if(role.equals("CLERK"))	userDB = ((AuthenticationDataAccess) dao).getClerk(user.getUsername());
		else	userDB=((AuthenticationDataAccess) dao).getUser(user.getUsername());
		
		if(userDB == null)	return null;
		if(!isPassValid(user.getPassword(),userDB.getPassword()))	return null;
		
		user.setToken(new RandomString().nextString());
		boolean flag = false;
		if(role.equals("MANAGER"))	flag=((AuthenticationDataAccess) dao).setManagerToken(user);
		else if(role.equals("CLERK"))	flag = ((AuthenticationDataAccess) dao).setClerkToken(user);
		else	flag=((AuthenticationDataAccess) dao).setCustomerToken(user);
		
		if(!flag)	return null;
		if(!role.equals("CUSTOMER"))	return new AuthResponse(0,user.getToken());
		CustomerDataAccess cdao = new CustomerDataAccess();
		Customer c = cdao.getCustomerByEmail(user.getUsername());
		return new AuthResponse(c.getCustomerId(),user.getToken());
	}

	private String hashPasswords(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());

            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashBytes) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not supported.", e);
        }
    }

    private boolean isPassValid(String plainPassword, String storedPassword) {
        return hashPasswords(plainPassword).equals(storedPassword);
    }

	@Override
	public boolean changePassword(long customerId, String pass) throws Exception {
		return ((AuthenticationContract) dao).changePassword(customerId,hashPasswords(pass));
	}

	@Override
	public boolean logout(String token, String role) throws Exception {
		// TODO Auto-generated method stub
		if(role.equals("CUSTOMER"))	return ((AuthenticationDataAccess) dao).removeCustomerToken(token);
		else if(role.equals("MANAGER"))	return ((AuthenticationDataAccess) dao).removeManagerToken(token);
		else	return ((AuthenticationDataAccess) dao).removeClerkToken(token);
	}








}
