package com.tweetapp.model;

import java.util.ArrayList;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
@DynamoDBTable(tableName = "tweet")
@JsonInclude(Include.NON_NULL)
public class Tweet {

	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	String id;

	@DynamoDBAttribute
	String name;

	@DynamoDBAttribute
	String email;

	@DynamoDBAttribute
	String loginId;

	@DynamoDBAttribute
	String description;

	@DynamoDBAttribute
	String postedBy;

	@DynamoDBAttribute
	Date postedTime;

	@DynamoDBAttribute
	Date lastUpdatedTime;

	@DynamoDBAttribute
	ArrayList<Likes> likes;

	@DynamoDBAttribute
	ArrayList<Comments> comments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	public ArrayList<Likes> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<Likes> likes) {
		this.likes = likes;
	}

	public ArrayList<Comments> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comments> comments) {
		this.comments = comments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}