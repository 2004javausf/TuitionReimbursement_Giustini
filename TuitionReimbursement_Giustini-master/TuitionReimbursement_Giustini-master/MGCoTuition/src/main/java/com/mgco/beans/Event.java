package com.mgco.beans;

public class Event {
	private int eventID;
	private String eventType;
	private int eventPercent;
	
	public Event(int eventID, String eventType, int eventPercent) {
		super();
		this.eventID = eventID;
		this.eventType = eventType;
		this.eventPercent = eventPercent;
	}
	
	public Event() {
		super();
	}
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getEventPercent() {
		return eventPercent;
	}
	public void setEventPercent(int eventPercent) {
		this.eventPercent = eventPercent;
	}
	
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventType=" + eventType + ", eventPercent=" + eventPercent + "]";
	}
}
