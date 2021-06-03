package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.tweetapp.model.Tweet;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Component
public interface TweetRepository extends MongoRepository<Tweet, String> {

	List<Tweet> findAll();

	List<Tweet> findByLoginId(String loginId);

	List<Tweet> findByPostedBy(String loginId);

}
