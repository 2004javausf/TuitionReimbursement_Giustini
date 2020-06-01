package com.mgco.beans;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 388378234458941113L;
	private int id;
	private String fName;
	private String lName;
	private int title;
	private String username;
	private String password;
	private int amtBalance;
	
	public Employee(int id, String fName, String lName, int title, String username, String password, int amtBalance) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.title = title;
		this.username = username;
		this.password = password;
		this.amtBalance = amtBalance;
	}
	
	public Employee() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAmtBalance() {
		return amtBalance;
	}
	public void setAmtBalance(int amtBalance) {
		this.amtBalance = amtBalance;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fName=" + fName + ", lName=" + lName + ", title=" + title + ", username="
				+ username + ", password=" + password + ", amtBalance=" + amtBalance + "]";
	}
}
