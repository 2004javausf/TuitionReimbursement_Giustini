package com.mgco.beans;

import java.io.Serializable;

import java.sql.Date;

import oracle.sql.BLOB;

public class ClaimForm implements Serializable{
	
	private static final long serialVersionUID = -433677546440327130L;
	private int id;
	private String fName;
	private String lName;
	private int eventType;
	private String eventDescription;
	private java.sql.Date eventDate;
	private int eventTime;
	private String eventLocation;
	private int eventCost;
	private int gradeType;
	private String justification;
	private BLOB optFile;
	private int amtApproved;
	private int status;
	
	public ClaimForm(int id, String fName, String lName, int eventType, String eventDescription, Date eventDate,
			int eventTime, String eventLocation, int eventCost, int gradeType, String justification, BLOB optFile,
			int amtApproved, int status) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.eventType = eventType;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventCost = eventCost;
		this.gradeType = gradeType;
		this.justification = justification;
		this.optFile = optFile;
		this.amtApproved = amtApproved;
		this.status = status;
	}
	
	
	
	public ClaimForm() {
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
	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public java.sql.Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(java.sql.Date eventDate) {
		this.eventDate = eventDate;
	}
	public int getEventTime() {
		return eventTime;
	}
	public void setEventTime(int eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public int getEventCost() {
		return eventCost;
	}
	public void setEventCost(int eventCost) {
		this.eventCost = eventCost;
	}
	public int getGradeType() {
		return gradeType;
	}
	public void setGradeType(int gradeType) {
		this.gradeType = gradeType;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public BLOB getOptFile() {
		return optFile;
	}
	public void setOptFile(BLOB optFile) {
		this.optFile = optFile;
	}
	public int getAmtApproved() {
		return amtApproved;
	}
	public void setAmtApproved(int amtApproved) {
		this.amtApproved = amtApproved;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ClaimForm [id=" + id + ", fName=" + fName + ", lName=" + lName + ", eventType=" + eventType
				+ ", eventDescription=" + eventDescription + ", eventDate=" + eventDate + ", eventTime=" + eventTime
				+ ", eventLocation=" + eventLocation + ", eventCost=" + eventCost + ", gradeType=" + gradeType
				+ ", justification=" + justification + ", optFile=" + optFile + ", amtApproved=" + amtApproved
				+ ", status=" + status + "]";
	}

}
