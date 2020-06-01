package com.mgco.beans;

public class ClaimStatus {
	int claimID;
	int empID;
	int apprID;
	String title;
	String claimStatus;
	String reason;
	public ClaimStatus(int claimID, int empID, int apprID, String title, String claimStatus, String reason) {
		super();
		this.claimID = claimID;
		this.empID = empID;
		this.apprID = apprID;
		this.title = title;
		this.claimStatus = claimStatus;
		this.reason = reason;
	}
	public ClaimStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getClaimID() {
		return claimID;
	}
	public void setClaimID(int claimID) {
		this.claimID = claimID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public int getApprID() {
		return apprID;
	}
	public void setApprID(int apprID) {
		this.apprID = apprID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "ClaimStatus [claimID=" + claimID + ", empID=" + empID + ", apprID=" + apprID + ", title=" + title
				+ ", claimStatus=" + claimStatus + ", reason=" + reason + "]";
	}
}
