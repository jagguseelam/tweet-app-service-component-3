package com.tweetapp.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tweetapp.TweetLogger;
import com.tweetapp.exception.UserAlreadyExistsException;
import com.tweetapp.model.User;
import com.tweetapp.model.UserLoginRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	@Override
	public User getUserDetailsByEmail(String email) {
		TweetLogger.LOGGER.info("UserDaoImpl :: getUserDetails");

		HashMap<String, AttributeValue> userHashMap = new HashMap<String, AttributeValue>();
		userHashMap.put(":v1", new AttributeValue().withS(email));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :v1")
				.withExpressionAttributeValues(userHashMap);

		return dynamoDBMapper.scan(User.class, scanExpression).stream().filter(data -> email.equals(data.getEmail()))
				.findAny().orElse(null);
	}

	@Override
	public boolean register(User user) throws UserAlreadyExistsException {
		TweetLogger.LOGGER.info("UserDaoImpl :: register");
		boolean isRegistered = false;
		User verifyUser = getUserDetailsByEmail(user.getEmail());

		if (verifyUser == null) {
			user.setUserCreatedTime(new Date());
			dynamoDBMapper.save(user);
			isRegistered = true;
		} else {
			throw new UserAlreadyExistsException();
		}
		return isRegistered;
	}

	@Override
	public List<User> getAllUsers() {
		TweetLogger.LOGGER.info("UserDaoImpl :: getAllUsers");
		return dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
	}

	@Override
	public User loginAndGetUser(UserLoginRequest userLoginRequest) {
		HashMap<String, AttributeValue> userHashMap = new HashMap<String, AttributeValue>();
		userHashMap.put(":v1", new AttributeValue().withS(userLoginRequest.getEmail()));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :v1")
				.withExpressionAttributeValues(userHashMap);

		return dynamoDBMapper.scan(User.class, scanExpression).stream()
				.filter(data -> ((userLoginRequest.getEmail().equals(data.getEmail()))
						&& (userLoginRequest.getPassword().equals(data.getPassword()))))
				.findAny().orElse(null);
	}

	@Override
	public boolean updatePassword(User user) {
		TweetLogger.LOGGER.info("UserDaoImpl :: updatePassword");
		dynamoDBMapper.save(user);
		return true;
	}

}
