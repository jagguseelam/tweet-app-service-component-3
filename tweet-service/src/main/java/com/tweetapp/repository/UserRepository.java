package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.User;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByLoginIdAndEmail(String loginId, String email);

	User findByEmail();

	User findByEmailAndPassword(String email, String password);

	User findByLoginId(String loginId);

}