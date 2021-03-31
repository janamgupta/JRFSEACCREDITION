package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tweet.model.Login;
import com.tweet.model.Tweet;
import com.tweet.model.UserInfo;
import com.tweetapp.util.DbUtil;

public class UserDao {
	Connection conn = null;
	PreparedStatement ps = null;
	public int registerUser(UserInfo userInfo) {
		int status = 0;
        try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("INSERT INTO user_info VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, userInfo.getEmail());
            ps.setString(2, userInfo.getPassword());
            ps.setBoolean(3, userInfo.getIsUserLoggedIn());
            ps.setString(4, userInfo.getFirst_name());
            ps.setString(5, userInfo.getLast_name());
            ps.setString(6, userInfo.getGender());
            ps.setDate(7,  userInfo.getDob());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
	}
	
	public int loginUser(Login login) {
		int status = 0;
		try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("SELECT * FROM user_info WHERE email = ? and password = ?");
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	status = 1;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
	}
	
	public int changeLoginStatus(Login login) {
		int status = 0;
		try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("UPDATE user_info SET isLoggedIn = true WHERE email = ? and password = ?");
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
	}
	
	public int changePassword(Login login) {
		int status = 0;
		try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("UPDATE user_info SET password = ? WHERE email = ?");
            ps.setString(1, login.getPassword());
            ps.setString(2, login.getEmail());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
	}
	
	public int addTweet(Tweet tweetInfo) {
		int status = 0;
		try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("INSERT INTO tweets(tweets, email) VALUES(?,?)");
            ps.setString(1, tweetInfo.getTweet());
            ps.setString(2, tweetInfo.getEmail());
            status = ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
	}
	
	public List<String> viewTweets(String email){
		List<String> tweetList = null;
		String tweet = null;
		try
        {
            conn = DbUtil.createNewDBconnection();
            ps = conn.prepareStatement("SELECT tweets FROM tweets WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs != null) {
            	tweetList = new ArrayList<String>();
            	while(rs.next()) {
            		tweet = rs.getString("tweets");
            		tweetList.add(tweet);
            	}
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		return tweetList;
	}
}
