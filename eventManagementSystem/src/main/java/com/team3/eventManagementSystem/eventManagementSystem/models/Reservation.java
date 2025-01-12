package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Reservation {

	private Integer id;
	private Integer visitorId;
	private Integer eventId;

	public Reservation(Integer visitorId, Integer eventId) {
		this.visitorId = visitorId;
		this.eventId = eventId;
	}

	// GETTERS AND SETTERS
	public Integer getEventId() {
		return this.eventId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getVisitorId() {
		return this.visitorId;
	}

}
