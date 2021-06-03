package com.tweetapp.repository;

import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.UpdateTweetRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public interface TweetRepositoryOperations {

	boolean addTweet(AddTweetRequest addTweetRequest);

	boolean updateTweet(UpdateTweetRequest updateTweetRequest);

	boolean deleteTweet(String tweetId);

	boolean likeTweet(String tweetId, String loginId);

	boolean replyTweet(ReplyTweetRequest replyTweetRequest);

}
