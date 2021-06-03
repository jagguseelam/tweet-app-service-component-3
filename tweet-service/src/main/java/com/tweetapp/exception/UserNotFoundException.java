package com.tweetapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

}
