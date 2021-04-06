package com.tweetapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tweet.model.Login;
import com.tweet.model.Tweet;
import com.tweet.model.UserInfo;
import com.tweetapp.dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	public String registerUser() throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Register New User");
		System.out.println("------------------------------------------------");
        System.out.println("Enter Email:");
        System.out.println("------------------------------------------------");
        String email = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Password:");
        System.out.println("------------------------------------------------");
        String password = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter First Name:");
        System.out.println("------------------------------------------------");
        String firstName = br.readLine();
        System.out.println("Enter Last Name:");
        System.out.println("------------------------------------------------");
        String lastName = br.readLine();
        System.out.println("Enter Gender:");
        System.out.println("------------------------------------------------");
        String gender = br.readLine();
        System.out.println("Enter DOB");
        System.out.println("------------------------------------------------");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date myDate = formatter.parse(br.readLine());
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        UserInfo userInfo = new UserInfo(firstName, lastName, email, password, gender, false,sqlDate);
        int status = userDao.registerUser(userInfo);
        if(status ==1 )
        {
            return "User Registered successfully";
        }
        else
        {
            return "ERROR while  registering user";
        }
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
	
	public int changePassword() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Change Password");
		System.out.println("------------------------------------------------");
        System.out.println("Enter Email:");
        System.out.println("------------------------------------------------");
        String email = br.readLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Password:");
        System.out.println("------------------------------------------------");
        String password = br.readLine();
        System.out.println("------------------------------------------------");
        Login login = new Login(email, password);
        int status = userDao.changePassword(login);
        return status;
	}
	
	public int addTweet(String email) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Add Tweet");
		System.out.println("------------------------------------------------");
        System.out.println("Enter Tweet:");
        System.out.println("------------------------------------------------");
        String tweet = br.readLine();
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
	
	public String resetPassword(String email, String oldPassword, String newPassword) {
		int status = 0;
		status = userDao.resetPassword(email, oldPassword, newPassword);
		if(status == 0) {
			return "Unable to reset password try again";
		}else {
			if(status == -1) {
				return "your old password is wrong try again.";
			}else {
				return "password reset successfull";
			}
		}
	}
	
	public int logOutUser(String email) {
		int status = 0;
		status = userDao.logoutUser(email);
		return status;
	}
}
