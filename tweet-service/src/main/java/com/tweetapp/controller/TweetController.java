package com.tweetapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.TweetLogger;
import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UpdateTweetRequest;
import com.tweetapp.service.TweetService;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@RestController()
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

	Logger logger = LoggerFactory.getLogger(TweetController.class);

	@Autowired
	private TweetService tweetService;

	@GetMapping("/all")
	public List<Tweet> getAllTweets() {
		TweetLogger.LOGGER.info("TweetController :: getAllTweets");
		return tweetService.getAllTweets();
	}

	@GetMapping("/user/{email}")
	public List<Tweet> getAllTweets(@PathVariable String email) {
		TweetLogger.LOGGER.info("TweetController :: getAllTweets");
		return tweetService.getAllTweets(email);
	}

	@PostMapping("/user/add")
	public String addTweet(@RequestBody AddTweetRequest addTweetRequest) {
		TweetLogger.LOGGER.info("TweetController :: addTweet");
		return tweetService.addTweet(addTweetRequest);
	}

	@PutMapping("/user/update")
	public String updateTweet(@RequestBody UpdateTweetRequest UpdateTweetRequest) {
		TweetLogger.LOGGER.info("TweetController :: updateTweet");
		return tweetService.updateTweet(UpdateTweetRequest);
	}

	@PutMapping("/user/like/{tweetId}/{loginId}")
	public String likeTweet(@PathVariable String tweetId, @PathVariable String loginId) {
		TweetLogger.LOGGER.info("TweetController :: likeTweet");
		return tweetService.likeTweet(tweetId, loginId);
	}

	@DeleteMapping("/user/delete/{tweetId}")
	public String deleteTweet(@PathVariable String tweetId) {
		TweetLogger.LOGGER.info("TweetController :: deleteTweet");
		return tweetService.deleteTweet(tweetId);
	}

	@PutMapping("/user/reply")
	public String replyTweet(@RequestBody ReplyTweetRequest replyTweetRequest) {
		TweetLogger.LOGGER.info("TweetController :: replyTweet");
		return tweetService.replyTweet(replyTweetRequest);
	}

}