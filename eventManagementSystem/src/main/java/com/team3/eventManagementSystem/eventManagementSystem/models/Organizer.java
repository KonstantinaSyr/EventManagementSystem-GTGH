package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Organizer extends Visitor{
	
	private int afm;
	private String description;
	
	//Constructor
	public Organizer(String name, String surname, String email, int afm, String description) {
		super(name, surname, email);
		this.afm = afm;
		this.description = description;
	}
	
	// we shouldn't call this function from here, directly from RequestService
	/*
	public void makeApproveRequest(ApprovalRequest request) {
		RequestService.createRequest(request);
		System.out.println("Request for the event "+ request.getEvent().getTitle()+ " created successfully: ");
	}
	*/
	
	// we shouldn't call this function from here, directly from EventService
	/*public void showMyEvents() {
		EventService.showMyEvents(this);
	}
	*/
	
	public String toString() {
		return this.getName();
	}

}
