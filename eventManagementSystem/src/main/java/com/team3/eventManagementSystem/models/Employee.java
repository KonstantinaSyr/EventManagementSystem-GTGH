package com.team3.eventManagementSystem.models;

import java.util.Date;

import com.team3.eventManagementSystem.service.EventService;
import com.team3.eventManagementSystem.service.RequestService;


public class Employee extends Visitor{

	
	//Constructor
	public Employee(String Name, String Surname, String Email) {
		super(Name, Surname, Email);
	}
	
	/**
	 * Employee adds an Event to the list of events.
	 * @param event
	 */
	public void addEvent(Event event) {
		 EventService.createEvent(event);
	     System.out.println("Event added successfully: " + event.getTitle());
	}
	
	/**
	 * Employee deletes an Event from the list of events. 
	 * @param event
	 */
	public void deleteEvent(Event event) {
		 EventService.deleteEvent(event.getTitle());
	     System.out.println("Event deleted successfully: " + event.getTitle());
	}
	
	public boolean approveRequest(ApprovalRequest request) {
		Date today = new Date();
		request.setClosedAt(today);
		if(RequestService.ApprovalRequestExists(request)) {
			EventService.createEvent(request.getEvent()); //adds the event at the EventList
			RequestService.deleteRequest(request); //remove the request from requestList
			return true;
		}
		else {
			System.out.println(" Request not found");
			return false;
		}
		
		
	}

	public boolean rejectRequest(ApprovalRequest request) {
		Date today = new Date();
		request.setClosedAt(today);
		if( RequestService.ApprovalRequestExists(request)) { //the request is at the list
			request.setStatus(" Rejected");
			RequestService.deleteRequest(request); //Deletes the request if it is rejected
			//Show rejection message
			System.out.println(" The request for the event "+ request.getEvent().getTitle() +" was rejected");
			return true;
		}
		else {
			System.out.println(" The request doesn't exist");
			return false;
		}
		
	}
	
	public void showAllRequests() {
		RequestService.showAllRequests();
	}
	
	public void showApprovalRequestListStatus() {
		RequestService.showApprovalRequestListStatus();
	}

	public void showEventListStatus() {
		EventService.showEventListStatus();
	}
}
