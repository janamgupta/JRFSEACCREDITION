package com.tweetapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tweet.model.Login;
import com.tweet.model.UserInfo;
import com.tweetapp.service.UserService;

public class App 
{
    public static void main( String[] args ) throws IOException, ParseException
    {
		Boolean isLoggedIn = false;
		UserService userService = new UserService();
		Login loggedInuser = null;
		Boolean regStatus = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			while (!isLoggedIn) {
				int choice;
				loggedInuser = new Login();
				System.out.println(
						"Please Select An Option From Below List\n1. Register\n2. login\n3. Forgot Password\n");
				choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					String regEmail = null;
					String regPassword = null;
					String firstName = null;
					String lastName = null;
					String gender = null;
					System.out.println("Register New User");
					System.out.println("------------------------------------------------");
			        System.out.println("Enter Email:");
			        System.out.println("------------------------------------------------");
			        while(!regStatus) {
			        	regEmail = br.readLine();
			        	regStatus = Validator("emailReg", regEmail);
			        	if(!regStatus) {
			        		System.out.println("ERROR: Please enter a valid email address");
			        		System.out.println("Enter Email Again:");
			        	}
			        }
			        regStatus = false;
			        System.out.println("------------------------------------------------");
			        System.out.println("Enter Password:");
			        System.out.println("------------------------------------------------");
			        while(!regStatus) {
			        	regPassword = br.readLine();
			        	regStatus = Validator("password", regPassword);
			        	if(!regStatus) {
			        		System.out.println("ERROR: Please enter a valid password");
			        		System.out.println("A password is considered valid if all the following constraints are satisfied:\r\n" + 
			        				"\r\n" + 
			        				"It contains at least 8 characters and at most 20 characters.\r\n" + 
			        				"It contains at least one digit.\r\n" + 
			        				"It contains at least one upper case alphabet.\r\n" + 
			        				"It contains at least one lower case alphabet.\r\n" + 
			        				"It contains at least one special character which includes !@#$%&*()-+=^.\r\n" + 
			        				"It doesn’t contain any white space.");
			        		System.out.println("Enter Password Again:");
			        	}
			        }
			        regStatus = false;
			        System.out.println("------------------------------------------------");
			        System.out.println("Enter First Name:");
			        System.out.println("------------------------------------------------");while(!regStatus) {
			        	firstName = br.readLine();
			        	regStatus = Validator("alphabatical", firstName);
			        	if(!regStatus) {
			        		System.out.println("ERROR: Please enter a valid first name.");
			        		System.out.println("First Name Should Contains Only Alphabets And No Spaces");
			        		System.out.println("Enter First Name Again:");
			        	}
			        }
			        regStatus = false;
			        System.out.println("Enter Last Name:");
			        System.out.println("------------------------------------------------");
			        while(!regStatus) {
			        	lastName = br.readLine();
			        	regStatus = Validator("alphabatical", lastName);
			        	if(!regStatus) {
			        		System.out.println("ERROR: Please enter a valid last name");
			        		System.out.println("First Name Should Contains Only Alphabets And No Spaces.");
			        		System.out.println("Enter Last Name Again:");
			        	}
			        }
			        regStatus = false;
			        System.out.println("Enter Gender:");
			        System.out.println("------------------------------------------------");
			        while(!regStatus) {
			        	gender = br.readLine();
			        	regStatus = Validator("gender", gender);
			        	if(!regStatus) {
			        		System.out.println("ERROR: Please enter a valid gender");
			        		System.out.println("Gender Should Be Either Male or Female or Other.");
			        		System.out.println("Enter Gender Again:");
			        	}
			        }
			        regStatus = false;
			        System.out.println("Enter DOB");
			        System.out.println("------------------------------------------------");
			        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			        java.util.Date myDate = formatter.parse(br.readLine());
			        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			        UserInfo userInfo = new UserInfo(firstName, lastName, regEmail, regPassword, gender, false,sqlDate);
			        int registerStatus = userService.registerUser(userInfo);
			        if(registerStatus == 1 )
			        {
			            System.out.println("User Registered successfully");
			        }
			        else
			        {
			            System.out.println("ERROR while  registering user");
			        }
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
					String forgetPassword = null;
					System.out.println("Change Password");
					System.out.println("------------------------------------------------");
			        System.out.println("Enter Email:");
			        System.out.println("------------------------------------------------");
			        String forgetEmail = br.readLine();
			        System.out.println("------------------------------------------------");
			        System.out.println("Enter New Password:");
			        System.out.println("------------------------------------------------");
			        while (!regStatus) {
			        	forgetPassword = br.readLine();
						regStatus = Validator("password", forgetPassword);
						if (!regStatus) {
							System.out.println("ERROR: Please enter a valid password");
							System.out.println(
									"A password is considered valid if all the following constraints are satisfied:\r\n"
											+ "\r\n"
											+ "It contains at least 8 characters and at most 20 characters.\r\n"
											+ "It contains at least one digit.\r\n"
											+ "It contains at least one upper case alphabet.\r\n"
											+ "It contains at least one lower case alphabet.\r\n"
											+ "It contains at least one special character which includes !@#$%&*()-+=^.\r\n"
											+ "It doesn’t contain any white space.");
							System.out.println("Enter New Password Again:");
						}
					}
					regStatus = false;
			        System.out.println("------------------------------------------------");
					int passwordChangeStatus = userService.changePassword(forgetEmail, forgetPassword);
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
					System.out.println("Add Tweet");
					System.out.println("------------------------------------------------");
			        System.out.println("Enter Tweet:");
			        System.out.println("------------------------------------------------");
			        String newTweet = br.readLine();
					int tweetStatus = userService.addTweet(loggedInuser.getEmail(), newTweet);
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
					String newPassword = null;
					int status = 0;
					while(status != 1) {
						System.out.println("Reset Password");
						System.out.println("------------------------------------------------");
						System.out.println("Enter Old Password:");
						System.out.println("------------------------------------------------");
						String oldPassword = br.readLine();
						System.out.println("------------------------------------------------");
						System.out.println("Enter New Password:");
						System.out.println("------------------------------------------------");
						while (!regStatus) {
							newPassword = br.readLine();
							regStatus = Validator("password", newPassword);
							if (!regStatus) {
								System.out.println("ERROR: Please enter a valid password");
								System.out.println(
										"A password is considered valid if all the following constraints are satisfied:\r\n"
												+ "\r\n"
												+ "It contains at least 8 characters and at most 20 characters.\r\n"
												+ "It contains at least one digit.\r\n"
												+ "It contains at least one upper case alphabet.\r\n"
												+ "It contains at least one lower case alphabet.\r\n"
												+ "It contains at least one special character which includes !@#$%&*()-+=^.\r\n"
												+ "It doesn’t contain any white space.");
								System.out.println("Enter New Password Again:");
							}
						}
						regStatus = false;
						System.out.println("------------------------------------------------");
						status = userService.resetPassword(loggedInuser.getEmail(), oldPassword, newPassword);
						if(status == 0) {
							System.out.println("ERROR: Unable to reset password try again");
						}else {
							if(status == -1) {
								System.out.println("ERROR: your old password is wrong try again.");
							}else if(status == -2){
								System.out.println("ERROR: New Password Shouldn't Be Same As Old Password.Try Again");
							}else {
								System.out.println("Password Reset Successful.");
							}
						}
					}
					System.out.println("Please Login Again To Continue.");
					isLoggedIn = false;
					loggedInuser = null;
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
    
    public static Boolean Validator(String key, String value) {
    	Pattern pattern = null;
    	Matcher matcher = null;
    	if(key == "emailReg") {
    		String emailRegex = "^(.+)@(.+)$";
    		pattern = Pattern.compile(emailRegex);
    		matcher = pattern.matcher(value);
    		if(value != null && !value.equals("")) {
    			if(matcher.matches()) {
        			return true;
        		}
    		}
    	}else if(key == "password") {
    		String passwordRegex = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$";
    		pattern = Pattern.compile(passwordRegex);
    		matcher = pattern.matcher(value);
    		if(value != null && !value.equals("")) {
    			if(matcher.matches()) {
        			return true;
        		}
    		}
    	}else if(key == "alphabatical") {
    		String alphaRegex = "^[a-zA-Z]*$";
    		pattern = Pattern.compile(alphaRegex);
    		matcher = pattern.matcher(value);
    		if(value != null && !value.equals("")) {
    			if(matcher.matches()) {
        			return true;
        		}
    		}
    	}else if(key == "gender") {
    		if(value != null && !value.equals("")) {
    			if(value.equalsIgnoreCase("male") || value.equalsIgnoreCase("female") || value.equalsIgnoreCase("other")) {
        			return true;
        		}
    		}
    	}
    	return false;
    }
}
