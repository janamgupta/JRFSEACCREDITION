package com.tweet.model;

public class Tweet {
	private String tweet;
	private String email;
	
	public Tweet(String tweet, String email) {
		super();
		this.tweet = tweet;
		this.email = email;
	}
	
	public Tweet() {
		super();
	}

	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
