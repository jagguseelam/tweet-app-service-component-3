package com.tweetapp.repository;

import com.tweetapp.model.ForgotPasswordRequest;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public interface UserRepositoryOperations {

	boolean updatePassword(ForgotPasswordRequest forgotPasswordRequest);

	void pushNotifications(String notification);

}
