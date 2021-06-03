package com.tweetapp.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.tweetapp.TweetLogger;
import com.tweetapp.model.ForgotPasswordRequest;
import com.tweetapp.model.User;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Component
public class UserRepositoryOperationImpl implements UserRepositoryOperations {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean updatePassword(ForgotPasswordRequest forgotPasswordRequest) {
		TweetLogger.LOGGER.info("UserRepositoryOperationImpl :: updatePassword");
		Query query = new Query();
		query.addCriteria(Criteria.where("loginId").is(forgotPasswordRequest.getLoginId()));

		Update updatePassword = new Update().set("password", forgotPasswordRequest.getNewPassword());
		updatePassword.set("lastUpdatedTime", new Date());

		try {
			mongoTemplate.updateFirst(query, updatePassword, User.class);
			TweetLogger.LOGGER.info("Password Updated Successfully");
			return true;
		} catch (Exception e) {
			TweetLogger.LOGGER.info("Failed to update Password");
			return false;
		}
	}

	@Override
	public void pushNotifications(String notification) {
		TweetLogger.LOGGER.info("UserRepositoryOperationImpl :: pushNotifications :: Kafka");
		Query query = new Query();
		query.addCriteria(Criteria.where("email").exists(true));
		Update update = new Update().push("notifications", notification);
		mongoTemplate.upsert(query, update, User.class);
		TweetLogger.LOGGER.info("Pused the Notifications Successfully");
	}

}
