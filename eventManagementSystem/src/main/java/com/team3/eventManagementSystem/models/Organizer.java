package com.team3.eventManagementSystem.models;

import com.team3.eventManagementSystem.service.EventService;
import com.team3.eventManagementSystem.service.RequestService;

public class Organizer extends Visitor{
	
	private int afm;
	private String description;
	
	//Constructor
	public Organizer(String name, String surname, String email, int afm, String description) {
		super(name, surname, email);
		this.afm = afm;
		this.description = description;
	}
	
	public void makeApproveRequest(ApprovalRequest request) {
		RequestService.createRequest(request);
		System.out.println("Request for the event"+ request.getEvent().getTitle()+ "created successfully: ");
	}
	
	public void showMyEvents() {
		EventService.showMyEvents(this);
	}

}
