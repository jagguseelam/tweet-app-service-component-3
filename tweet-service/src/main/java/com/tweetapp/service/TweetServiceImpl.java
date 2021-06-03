package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.TweetLogger;
import com.tweetapp.dao.TweetDao;
import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UpdateTweetRequest;
import com.tweetapp.util.TweetConstants;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetDao tweetDao;

	@Override
	public List<Tweet> getAllTweets() {
		TweetLogger.LOGGER.info("TweetServiceImpl :: getAllTweets");
		return tweetDao.getAllTweets();
	}

	@Override
	public List<Tweet> getAllTweets(String email) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: getAllTweets :: User");
		return tweetDao.getTweetsByEmail(email);
	}

	@Override
	public String addTweet(AddTweetRequest addTweetRequest) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: addTweet");
		boolean isTweetAdded = tweetDao.addTweet(addTweetRequest);
		if (isTweetAdded) {
			return TweetConstants.SUCCESS;
		} else {
			return TweetConstants.FAIL;
		}
	}

	@Override
	public String updateTweet(UpdateTweetRequest updateTweetRequest) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: updateTweet");
		boolean isTweetUpdated = tweetDao.updateTweet(updateTweetRequest);
		if (isTweetUpdated) {
			return TweetConstants.SUCCESS;
		} else {
			return TweetConstants.FAIL;
		}

	}

	@Override
	public String deleteTweet(String tweetId) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: deleteTweet");
		boolean isTweetDeleted = tweetDao.deleteTweet(tweetId);
		if (isTweetDeleted) {
			return TweetConstants.SUCCESS;
		} else {
			return TweetConstants.FAIL;
		}
	}

	@Override
	public String likeTweet(String tweetId, String loginId) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: likeTweet");
		boolean isTweetLiked = tweetDao.likeTweet(tweetId, loginId);
		if (isTweetLiked) {
			return TweetConstants.SUCCESS;
		} else {
			return TweetConstants.FAIL;
		}
	}

	@Override
	public String replyTweet(ReplyTweetRequest replyTweetRequest) {
		TweetLogger.LOGGER.info("TweetServiceImpl :: replyTweet");
		boolean isTweetReplied = tweetDao.replyTweet(replyTweetRequest);
		if (isTweetReplied) {
			return TweetConstants.SUCCESS;
		} else {
			return TweetConstants.FAIL;
		}
	}

}
