package com.java.dataaccess.contracts;

import com.java.entities.User;

public interface AuthenticationContract {
	User getUser(String username) throws Exception;
	User getClerk(String username) throws Exception;
	User getManager(String username) throws Exception;
	boolean addUser(User user) throws Exception;
}
