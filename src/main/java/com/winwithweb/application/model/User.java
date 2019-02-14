package com.winwithweb.application.model;

public class User {

	private String UserName;
	private String email;
	private String password;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
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

	@Override
	public String toString() {
		return "Userdetail is [" + UserName +"|"+ email+"|"+ password +"]";
	}

}
