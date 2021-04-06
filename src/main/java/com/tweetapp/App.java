package com.tweetapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tweet.model.Login;
import com.tweet.model.UserInfo;
import com.tweetapp.service.UserService;

public class App 
{
    public static void main( String[] args ) throws IOException, ParseException
    {
		Boolean isLoggedIn = false;
		UserService userService = new UserService();
		Login loggedInuser = new Login();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			while (!isLoggedIn) {
				int choice;
				System.out.println(
						"Please Select An Option From Below List\n1. Register\n2. login\n3. Forgot Password\n");
				choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					System.out.println(userService.registerUser());
					break;
				case 2:
					System.out.println("Login User");
					System.out.println("------------------------------------------------");
					System.out.println("Enter Email:");
					System.out.println("------------------------------------------------");
					String email = br.readLine();
					System.out.println("------------------------------------------------");
					System.out.println("Enter Password:");
					System.out.println("------------------------------------------------");
					String password = br.readLine();
					System.out.println("------------------------------------------------");
					int status = userService.loginUser(email, password);
					if (status == 1) {
						isLoggedIn = true;
						loggedInuser.setEmail(email);
						System.out.println("User Logged In Successfully");
					} else {
						System.out.println("Wrong Username or Password");
					}
					break;
				case 3:
					int passwordChangeStatus = userService.changePassword();
					if (passwordChangeStatus == 1) {
						System.out.println("Password Changed Successfully");
					} else {
						System.out.println("UserName Not Found please register!!!");
					}
					break;
				default:
					System.out.println("Please Enter a valid choice");
				}
			}
			while (isLoggedIn) {
				int choice;
				System.out.println(
						"Please Select An Option From Below List\n1. Post a Tweet\n2. View My Tweet\n3. View All Tweets\n4. View All Users\n5. Reset Password\n6. Logout");
				choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					int tweetStatus = userService.addTweet(loggedInuser.getEmail());
					if (tweetStatus == 1) {
						System.out.println("Tweet Added Successfully");
					} else {
						System.out.println("Cannot Add Tweet Something Went Wrong");
					}
					break;
				case 2:
					List<String> tweetList = new ArrayList<String>();
					tweetList = userService.viewTweets(loggedInuser.getEmail());
					if (tweetList == null) {
						System.out.println("No Tweet Found Please Tweet Something First.");
					} else {
						System.out.println("Here Is Your Tweets");
						System.out.println("------------------------------------------------");
						tweetList.forEach(tweet -> {
							System.out.println(tweet);
							System.out.println("------------------------------------------------");
						});
					}
					break;
				case 3:
					HashMap<String, List<String>> allTweets = new HashMap<String, List<String>>();
					allTweets = userService.viewAllTweets(loggedInuser.getEmail());
					if (allTweets.isEmpty()) {
						System.out.println("No Tweet Found.");
					} else {
						System.out.println("Here Is Your Tweets");
						System.out.println("------------------------------------------------");
						allTweets.forEach((key, value) -> {
							System.out.println("Tweets of user " + key);
							value.forEach(tweet -> {
								System.out.println(tweet);
							});
						});
					}
					break;
				case 4:
					List<UserInfo> usersList = new ArrayList<UserInfo>();
					usersList = userService.viewAllUsers(loggedInuser.getEmail());
					if (usersList.isEmpty()) {
						System.out.println("No Users Found");
					} else {
						System.out.println("Here Are All The Users");
						System.out.println("------------------------------------------------");
						System.out.println("| Email | First Name | Last Name | Gender | DOB |");
						usersList.forEach(userInfo -> {
							System.out.println("| " + userInfo.getEmail() + " | " + userInfo.getFirst_name() + " | "
									+ userInfo.getLast_name() + " | " + userInfo.getGender() + " | " + userInfo.getDob()
									+ " |");
						});
					}
					break;
				case 5:
					System.out.println("Reset Password");
					System.out.println("------------------------------------------------");
					System.out.println("Enter Old Password:");
					System.out.println("------------------------------------------------");
					String oldPassword = br.readLine();
					System.out.println("------------------------------------------------");
					System.out.println("Enter New Password:");
					System.out.println("------------------------------------------------");
					String newPassword = br.readLine();
					System.out.println("------------------------------------------------");
					String status = userService.resetPassword(loggedInuser.getEmail(), oldPassword, newPassword);
					System.out.println(status);
					break;
				case 6:
					System.out.println("Logging out!!!!");
					int loginStatus = userService.logOutUser(loggedInuser.getEmail());
					if (loginStatus == 1) {
						isLoggedIn = false;
						loggedInuser = null;
						System.out.println("User Logged out Successfully");
					} else {
						System.out.println("Logging Out Failed Try Again");
					}
					break;
				default:
					System.out.println("Please Enter a valid choice");
				}
			}
		}
    }
}
