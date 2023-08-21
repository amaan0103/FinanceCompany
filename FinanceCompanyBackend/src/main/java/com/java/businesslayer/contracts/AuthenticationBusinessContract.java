package com.java.businesslayer.contracts;

import com.java.entities.User;

public interface AuthenticationBusinessContract {
	long signup(User user) throws Exception;
	long login(User user) throws Exception;
	boolean changePassword(long customerId, String pass) throws Exception;
}
