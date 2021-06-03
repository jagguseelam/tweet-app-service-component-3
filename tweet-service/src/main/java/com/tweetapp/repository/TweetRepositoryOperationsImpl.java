package com.tweetapp.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.tweetapp.TweetLogger;
import com.tweetapp.model.AddTweetRequest;
import com.tweetapp.model.Comments;
import com.tweetapp.model.Likes;
import com.tweetapp.model.ReplyTweetRequest;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.UpdateTweetRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Component
public class TweetRepositoryOperationsImpl implements TweetRepositoryOperations {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean addTweet(AddTweetRequest addTweetRequest) {
		TweetLogger.LOGGER.info("TweetRepositoryOperationsImpl :: addTweet");
		Tweet addTweet = new Tweet();
		addTweet.setDescription(addTweetRequest.getDescription());
		addTweet.setLoginId(addTweetRequest.getLoginId());
		addTweet.setPostedBy(addTweetRequest.getPostedBy());
		addTweet.setPostedTime(new Date());
		addTweet.setName(addTweetRequest.getName());

		try {
			mongoTemplate.insert(addTweet);
			TweetLogger.LOGGER.info("Tweet Posted Successfully");
			return true;
		} catch (Exception e) {
			TweetLogger.LOGGER.info("Failed to post Tweet");
			return false;
		}
	}

	@Override
	public boolean updateTweet(UpdateTweetRequest updateTweetRequest) {
		TweetLogger.LOGGER.info("TweetRepositoryOperationsImpl :: updateTweet");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(updateTweetRequest.getTweetId()));

		Update updateTweet = new Update().set("description", updateTweetRequest.getDescription());
		updateTweet.set("lastUpdatedTime", new Date());

		try {
			mongoTemplate.updateFirst(query, updateTweet, Tweet.class);
			TweetLogger.LOGGER.info("Tweet updated Successfully");
			return true;
		} catch (Exception e) {
			TweetLogger.LOGGER.info("Failed to update Tweet");
			return false;
		}

	}

	@Override
	public boolean deleteTweet(String tweetId) {
		TweetLogger.LOGGER.info("TweetRepositoryOperationsImpl :: deleteTweet");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(tweetId));

		try {
			mongoTemplate.remove(query, Tweet.class);
			TweetLogger.LOGGER.info("Tweet deleted Successfully");
			return true;
		} catch (Exception e) {
			TweetLogger.LOGGER.info("Failed to delete Tweet");
			return false;
		}
	}

	@Override
	public boolean likeTweet(String tweetId, String loginId) {
		TweetLogger.LOGGER.info("TweetRepositoryOperationsImpl :: likeTweet");
		Likes updateLikes = new Likes();
		updateLikes.setLikedBy(loginId);
		updateLikes.setLikedTime(new Date());

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(tweetId));
		Update update = new Update().push("likes", updateLikes);

		try {
			mongoTemplate.upsert(query, update, Tweet.class);
			TweetLogger.LOGGER.info("Liked the Tweet Successfully");
			return true;
		} catch (Exception e) {
			TweetLogger.LOGGER.info("Failed to like the Tweet");
			return false;
		}
	}

	@Override
	public boolean replyTweet(ReplyTweetRequest replyTweetRequest) {
		Comments replyComments = new Comments();
		replyComments.setPostedBy(replyTweetRequest.getPostedBy());
		replyComments.setDescription(replyTweetRequest.getDescription());
		replyComments.setPostedTime(new Date());

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(replyTweetRequest.getTweetId()));
		Update update = new Update().push("comments", replyComments);

		try {
			mongoTemplate.upsert(query, update, Tweet.class);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
