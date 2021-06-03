package com.tweetapp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tweetapp.TweetLogger;
import com.tweetapp.repository.UserRepositoryOperations;
import com.tweetapp.util.TweetConstants;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Service
public class Consumer {

	@Autowired
	private UserRepositoryOperations userRepositoryOperations;

	@KafkaListener(topics = TweetConstants.KAFKA_NOTIFICATIONS, groupId = TweetConstants.KAFKA_TWEET_SERVICE)
	public void consumeFromTopic(String notification) {
		TweetLogger.LOGGER.info("Consumer :: consumeFromTopic");
		userRepositoryOperations.pushNotifications(notification);
	}
}
