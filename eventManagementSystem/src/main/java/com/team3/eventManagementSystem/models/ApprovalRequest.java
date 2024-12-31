package com.team3.eventManagementSystem.models;

import java.util.Date;

public class ApprovalRequest {
	
	private String type;
	private Event event;
	private Organizer submittedBy;
	//The Date the Request was filed.
	private Date createdAt;
	private String status;
	private Employee handleBy;
	//The Date the Employee accepted or rejected the Request
	private Date closedAt;
	private String comments;
	
	public ApprovalRequest(String type, com.team3.eventManagementSystem.models.Event event, Organizer submittedBy,
			Date createdAt, String comments) {
		this.type = type;
		this.event = event;
		this.submittedBy = submittedBy;
		this.createdAt = createdAt;
		this.comments = comments;
	}
	
	//GETTERS AND SETTERS
	public void setStatus(String status) {
		this.status = status;
	}

	public void setHandleBy(Employee handleBy) {
		this.handleBy = handleBy;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

}
