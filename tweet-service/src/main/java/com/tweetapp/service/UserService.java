package com.tweetapp.service;

import java.util.List;

import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.ForgotPasswordRequest;
import com.tweetapp.model.User;
import com.tweetapp.model.UserLoginRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public interface UserService {

	boolean register(User user) throws UserAlreadyExistsException;

	List<User> getAllUsers();

	User getUserDetails(String email);

	User login(UserLoginRequest userLoginRequest) throws UserNotFoundException;

	boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException;

}
