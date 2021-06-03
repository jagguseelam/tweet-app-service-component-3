package com.tweetapp.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.TweetLogger;
import com.tweetapp.util.TweetConstants;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Service
public class Producer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public String publishToTopic(String notification) {
		TweetLogger.LOGGER.info("Producer :: publishToTopic :: Start");
		this.kafkaTemplate.send(TweetConstants.KAFKA_NOTIFICATIONS, notification);
		TweetLogger.LOGGER.info("Producer :: publishToTopic :: End");
		return TweetConstants.SUCCESS;
	}

}
