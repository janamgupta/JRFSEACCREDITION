package com.tweet.model;

import java.sql.Date;

public class UserInfo {
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String gender;
	private Boolean isUserLoggedIn;
	private Date dob;
	
	public UserInfo() {
		super();
	}
	public UserInfo(String first_name, String last_name, String email, String password, String gender,
			Boolean isUserLoggedIn, Date dob) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.isUserLoggedIn = isUserLoggedIn;
		this.dob = dob;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Boolean getIsUserLoggedIn() {
		return isUserLoggedIn;
	}
	public void setIsUserLoggedIn(Boolean isUserLoggedIn) {
		this.isUserLoggedIn = isUserLoggedIn;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}	
}
