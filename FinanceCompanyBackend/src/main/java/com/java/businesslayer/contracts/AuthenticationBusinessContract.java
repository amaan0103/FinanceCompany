package com.java.businesslayer.contracts;

import com.java.entities.AuthResponse;
import com.java.entities.User;

public interface AuthenticationBusinessContract {
	long signup(User user) throws Exception;
	AuthResponse login(User user, String role) throws Exception;
	boolean logout(String token, String role)	throws Exception;
	boolean changePassword(long customerId, String pass) throws Exception;
}
