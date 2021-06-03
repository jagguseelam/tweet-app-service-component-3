package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.TweetLogger;
import com.tweetapp.dao.UserDao;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.ForgotPasswordRequest;
import com.tweetapp.model.User;
import com.tweetapp.model.UserLoginRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public boolean register(User user) throws UserAlreadyExistsException {
		TweetLogger.LOGGER.info("UserServiceImpl :: register");
		return userDao.register(user);
	}

	@Override
	public List<User> getAllUsers() {
		TweetLogger.LOGGER.info("UserServiceImpl :: getAllUsers");
		return userDao.getAllUsers();
	}

	@Override
	public User getUserDetails(String email) {
		return userDao.getUserDetailsByEmail(email);
	}

	@Override
	public User login(UserLoginRequest userLoginRequest) throws UserNotFoundException {
		TweetLogger.LOGGER.info("UserServiceImpl :: login");
		User user = userDao.loginAndGetUser(userLoginRequest);
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			return user;
		}
	}

	@Override
	public boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws UserNotFoundException {
		TweetLogger.LOGGER.info("UserServiceImpl :: forgotPassword");
		User user = userDao.getUserDetailsByEmail(forgotPasswordRequest.getLoginId());
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			user.setPassword(forgotPasswordRequest.getNewPassword());
			return userDao.updatePassword(user);
		}
	}

}
