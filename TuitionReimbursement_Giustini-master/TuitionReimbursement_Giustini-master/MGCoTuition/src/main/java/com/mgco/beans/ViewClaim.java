package com.mgco.beans;

public class ViewClaim {
	private int claimID;
	private int userID;
	
	public ViewClaim(int claimID, int userID) {
		super();
		this.claimID = claimID;
		this.userID = userID;
	}
	
	public ViewClaim() {
		super();
	}
	
	public int getClaimID() {
		return claimID;
	}
	public void setClaimID(int claimID) {
		this.claimID = claimID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		return "ViewClaim [claimID=" + claimID + ", userID=" + userID + "]";
	}
}
