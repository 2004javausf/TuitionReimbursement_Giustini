package com.mgco.beans;

public class UserLogin {
	String user;
	String pass;
	public UserLogin(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
