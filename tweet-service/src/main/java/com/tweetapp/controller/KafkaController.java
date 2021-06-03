package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.TweetLogger;
import com.tweetapp.kafka.Producer;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@RestController()
@RequestMapping("/api/admin/kafka")
public class KafkaController {

	@Autowired
	Producer producer;

	@PostMapping("/notification/{notification}")
	public String sendNotification(@PathVariable String notification) {
		TweetLogger.LOGGER.info("KafkaController :: sendNotification");
		return producer.publishToTopic(notification);
	}
}
