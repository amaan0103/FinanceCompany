package com.java.businesslayer.implementations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.java.businesslayer.contracts.AuthenticationBusinessContract;
import com.java.dataaccess.implementations.AuthenticationDataAccess;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.Customer;
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
	public long login(User user) throws Exception {
		// TODO Auto-generated method stub
		User userDB = null;
		if(user.getRole()==1)	userDB=((AuthenticationDataAccess) dao).getManager(user.getUsername());
		else if(user.getRole()==2)	userDB = ((AuthenticationDataAccess) dao).getClerk(user.getUsername());
		else	userDB=((AuthenticationDataAccess) dao).getUser(user.getUsername());
		if(userDB == null)	return -1;
		if(!isPassValid(user.getPassword(),userDB.getPassword()))	return -1;
		if(user.getRole()!=3)	return 0;
		CustomerDataAccess cdao = new CustomerDataAccess();
		Customer c = cdao.getCustomerByEmail(user.getUsername());
		return c.getCustomerId();
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








}
