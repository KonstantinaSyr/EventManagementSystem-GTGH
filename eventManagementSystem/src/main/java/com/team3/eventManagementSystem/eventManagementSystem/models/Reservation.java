package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Reservation {

	private int id;
	private int visitorId;
	private int eventId;

	// Constructor
	public Reservation(int visitorId, int eventId) {
		this.visitorId = visitorId;
		this.eventId = eventId;
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
