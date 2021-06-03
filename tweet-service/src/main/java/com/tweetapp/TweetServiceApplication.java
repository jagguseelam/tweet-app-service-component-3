package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@SpringBootApplication
public class TweetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetServiceApplication.class, args);
		TweetLogger.LOGGER.info("Tweet Application Started Successfully....");
	}

}
