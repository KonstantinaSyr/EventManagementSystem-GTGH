package com.team3.eventManagementSystem.models;

import java.util.Date;

public class ApprovalRequest {
	
	private String Type;
	private Event Event;
	private Organizer SubmittedBy;
	//The Date the Request was filed.
	private Date CreatedAt;
	private String Status;
	private Employee HandleBy;
	//The Date the Employee accepted or rejected the Request
	private Date ClosedAt;
	private String Comments;
	
	public ApprovalRequest(String type, com.team3.eventManagementSystem.models.Event event, Organizer submittedBy,
			Date createdAt, String comments) {
		Type = type;
		Event = event;
		SubmittedBy = submittedBy;
		CreatedAt = createdAt;
		Status = Event.getStatus();
		Comments = comments;
	}
	
	//GETTERS AND SETTERS
	public void setStatus(String status) {
		Status = status;
	}

	public void setHandleBy(Employee handleBy) {
		HandleBy = handleBy;
	}

	public void setClosedAt(Date closedAt) {
		ClosedAt = closedAt;
	}

}
