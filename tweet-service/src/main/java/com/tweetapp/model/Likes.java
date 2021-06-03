package com.tweetapp.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@DynamoDBDocument
public class Likes {
	@DynamoDBAttribute
	String likedBy;
	@DynamoDBAttribute
	Date likedTime;

	public String getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(String likedBy) {
		this.likedBy = likedBy;
	}

	public Date getLikedTime() {
		return likedTime;
	}

	public void setLikedTime(Date likedTime) {
		this.likedTime = likedTime;
	}

}
