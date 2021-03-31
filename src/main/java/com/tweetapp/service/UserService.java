package com.tweetapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
