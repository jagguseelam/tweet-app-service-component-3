package com.tweetapp.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@DynamoDBDocument
public class Comments {
	@DynamoDBAttribute
	String description;
	@DynamoDBAttribute
	String postedBy;
	@DynamoDBAttribute
	String postedByEmail;
	@DynamoDBAttribute
	Date postedTime;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getPostedByEmail() {
		return postedByEmail;
	}

	public void setPostedByEmail(String postedByEmail) {
		this.postedByEmail = postedByEmail;
	}

	public Date getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

}
