package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.model.User;
import com.tweetapp.model.UserLoginRequest;

public interface UserDao {

	boolean register(User user) throws UserAlreadyExistsException;

	List<User> getAllUsers();

	User loginAndGetUser(UserLoginRequest userLoginRequest);

	User getUserDetailsByEmail(String email);

	boolean updatePassword(User user);

}
