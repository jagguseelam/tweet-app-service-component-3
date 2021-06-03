package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UpdateTweetRequest;

public interface TweetDao {

	List<Tweet> getAllTweets();

	List<Tweet> getTweetsByEmail(String email);

	boolean addTweet(AddTweetRequest addTweetRequest);

	boolean updateTweet(UpdateTweetRequest updateTweetRequest);

	boolean deleteTweet(String tweetId);

	boolean likeTweet(String tweetId, String loginId);

	boolean replyTweet(ReplyTweetRequest replyTweetRequest);

}
