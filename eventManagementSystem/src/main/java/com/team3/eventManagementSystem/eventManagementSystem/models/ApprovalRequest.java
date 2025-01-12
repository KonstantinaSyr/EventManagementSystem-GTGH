package com.team3.eventManagementSystem.eventManagementSystem.models;

import java.util.Date;

public class ApprovalRequest {

	private Integer id;

	private String type;
	private Event event;
	private Integer submittedById;
	// The Date the Request was filed.
	private Date createdAt;
	private String status;
	private Integer handleById;
	// The Date the Employee accepted or rejected the Request
	private Date closedAt;
	private String comments;

	public ApprovalRequest(String type, Event event, Integer submittedById, String comments) {
		Date today = new Date();
		this.type = type;
		this.event = event;
		this.submittedById = submittedById;
		this.createdAt = today;
		this.comments = comments;
		this.status = "pending";
	}

	// GETTERS AND SETTERS

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setHandleBy(Integer handleBy) {
		this.handleById = handleBy;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public Event getEvent() {
		return event;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	// For printing the objects
	public String toString() {
		return "Request type= " + type + "\nEvent" + event.getTitle() + "\nOrganizer: " + submittedById + "\n";
	}

}
