package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UpdateTweetRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public interface TweetService {

	List<Tweet> getAllTweets();

	List<Tweet> getAllTweets(String email);

	String addTweet(AddTweetRequest addTweetRequest);

	String updateTweet(UpdateTweetRequest updateTweetRequest);

	String deleteTweet(String tweetId);

	String likeTweet(String tweetId, String loginId);

	String replyTweet(ReplyTweetRequest replyTweetRequest);

}
