package com.tweetapp.model;

/**
 * @author Seelam Jagadeeswara Reddy
 *
 */
public class UpdateTweetRequest {
	private String tweetId;

	private String description;

	private String lastUpdatedTime;

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}
