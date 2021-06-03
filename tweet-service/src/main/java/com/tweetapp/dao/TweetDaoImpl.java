package com.tweetapp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
@Service
public class TweetDaoImpl implements TweetDao {

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	@Override
	public List<Tweet> getAllTweets() {
		TweetLogger.LOGGER.info("TweetDaoImpl :: getAllTweets");
		return dynamoDBMapper.scan(Tweet.class, new DynamoDBScanExpression());
	}

	@Override
	public List<Tweet> getTweetsByEmail(String email) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: getTweetsByLoginId");
		HashMap<String, AttributeValue> tweetHashMap = new HashMap<String, AttributeValue>();
		tweetHashMap.put(":v1", new AttributeValue().withS(email));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :v1")
				.withExpressionAttributeValues(tweetHashMap);
		return dynamoDBMapper.scan(Tweet.class, scanExpression);
	}

	@Override
	public boolean addTweet(AddTweetRequest addTweetRequest) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: addTweet");
		Tweet tweet = new Tweet();
		tweet.setLoginId(addTweetRequest.getLoginId());
		tweet.setEmail(addTweetRequest.getEmail());
		tweet.setDescription(addTweetRequest.getDescription());
		tweet.setName(addTweetRequest.getName());
		tweet.setPostedBy(addTweetRequest.getPostedBy());
		tweet.setPostedTime(new Date());
		dynamoDBMapper.save(tweet);
		return true;
	}

	@Override
	public boolean updateTweet(UpdateTweetRequest updateTweetRequest) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: updateTweet");
		Tweet updateTweet = getTweetById(updateTweetRequest.getTweetId()).get(0);
		updateTweet.setDescription(updateTweetRequest.getDescription());
		updateTweet.setLastUpdatedTime(new Date());
		dynamoDBMapper.save(updateTweet);
		return true;
	}

	@Override
	public boolean deleteTweet(String tweetId) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: deleteTweet");
		Tweet tweet = dynamoDBMapper.load(Tweet.class, tweetId);
		dynamoDBMapper.delete(tweet);
		return true;
	}

	@Override
	public boolean likeTweet(String tweetId, String loginId) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: likeTweet");
		Tweet updateLikeTweet = getTweetById(tweetId).get(0);
		ArrayList<Likes> likesList = new ArrayList<Likes>();
		Likes newLike = new Likes();
		newLike.setLikedBy(loginId);
		newLike.setLikedTime(new Date());
		likesList.add(newLike);
		if (updateLikeTweet.getLikes() != null && updateLikeTweet.getLikes().size() > 0) {
			likesList.addAll(updateLikeTweet.getLikes());
		}
		updateLikeTweet.setLikes(likesList);
		dynamoDBMapper.save(updateLikeTweet);
		return true;
	}

	@Override
	public boolean replyTweet(ReplyTweetRequest replyTweetRequest) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: replyTweet");
		Tweet updateReplyTweet = getTweetById(replyTweetRequest.getTweetId()).get(0);
		ArrayList<Comments> commentsList = new ArrayList<Comments>();
		Comments newComment = new Comments();
		newComment.setPostedBy(replyTweetRequest.getPostedBy());
		newComment.setPostedTime(new Date());
		newComment.setPostedByEmail(replyTweetRequest.getEmail());
		newComment.setDescription(replyTweetRequest.getDescription());
		commentsList.add(newComment);

		if (updateReplyTweet.getComments() != null && updateReplyTweet.getComments().size() > 0) {
			commentsList.addAll(updateReplyTweet.getComments());
		}
		updateReplyTweet.setComments(commentsList);
		dynamoDBMapper.save(updateReplyTweet);
		return true;
	}

	public List<Tweet> getTweetById(String tweetId) {
		TweetLogger.LOGGER.info("TweetDaoImpl :: getTweetById");
		HashMap<String, AttributeValue> tweetHashMap = new HashMap<String, AttributeValue>();
		tweetHashMap.put(":v1", new AttributeValue().withS(tweetId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("id = :v1")
				.withExpressionAttributeValues(tweetHashMap);
		return dynamoDBMapper.scan(Tweet.class, scanExpression);
	}

}
