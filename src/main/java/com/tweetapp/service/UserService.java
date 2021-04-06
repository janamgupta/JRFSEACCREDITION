package com.tweetapp.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tweet.model.Login;
import com.tweet.model.Tweet;
import com.tweet.model.UserInfo;
import com.tweetapp.dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	public int registerUser(UserInfo userInfo) throws IOException, ParseException {
		return userDao.registerUser(userInfo);
	}
	
	public int loginUser(String email, String password) throws IOException, ParseException {
        Login login = new Login(email, password);
        int status = userDao.loginUser(login);
        if(status == 1)
        {
        	int loginStatus = userDao.changeLoginStatus(login);
            return loginStatus;
        }
        else
        {
            return status;
        }
	}
	
	public int changePassword(String email, String password){
        Login login = new Login(email, password);
        int status = userDao.changePassword(login);
        return status;
	}
	
	public int addTweet(String email, String tweet) throws IOException {
        Tweet tweetInfo = new Tweet(tweet, email) ;
        int status = userDao.addTweet(tweetInfo);
        return status;
	}
	
	public List<String> viewTweets(String email) {
		List<String> tweetList = userDao.viewTweets(email);
		return tweetList;
	}
	
	public HashMap<String, List<String>> viewAllTweets(String email) {
		List<Tweet> tweetList = userDao.viewAllTweets(email);
		HashMap<String, List<String>> allTweets = new HashMap<String, List<String>>();
		tweetList.forEach(allUserTweets -> {
				if(allTweets.containsKey(allUserTweets.getEmail())) {
					List<String> tweet = new ArrayList<String>();
					tweet = allTweets.get(allUserTweets.getEmail());
					tweet.add(allUserTweets.getTweet());
					allTweets.put(allUserTweets.getEmail(), tweet);
				}else {
					List<String> tweet = new ArrayList<String>();
					tweet.add(allUserTweets.getTweet());
					allTweets.put(allUserTweets.getEmail(), tweet);
				}
			});
		return allTweets;
	}
	
	public List<UserInfo> viewAllUsers(String email) {
		List<UserInfo> userList = userDao.viewAllUsers(email);
		System.out.println(userList);
		return userList;
	}
	
	public int resetPassword(String email, String oldPassword, String newPassword) {
		int status = 0;
		status = userDao.resetPassword(email, oldPassword, newPassword);
		return status;
	}
	
	public int logOutUser(String email) {
		int status = 0;
		status = userDao.logoutUser(email);
		return status;
	}
}
