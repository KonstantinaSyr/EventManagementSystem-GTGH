package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.models.Event;

@Service
public class EmployeeService {
	
	@Autowired 
	EventService eventService;
	
	@Autowired 
	RequestService RequestService;
	
	/**
	 * Employee adds an Event to the list of events.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		eventService.createEvent(event);
		System.out.println("Event added successfully: " + event.getTitle());
	}

	/**
	 * Employee deletes an Event from the list of events.
	 * 
	 * @param event
	 */

	public void deleteEvent(Event event) {
		eventService.deleteEvent(event.getTitle());
		System.out.println("Event deleted successfully: " + event.getTitle());
	}

	public boolean approveRequest(ApprovalRequest request) {
		Date today = new Date();
		request.setClosedAt(today);
		if (RequestService.ApprovalRequestExists(request)) {
			if (request.getType().equals("create")) {
				eventService.createEvent(request.getEvent()); // adds the event at the EventList
			} else {// delete event
				this.deleteEvent(request.getEvent());
			}
			request.setStatus("accepted");
			return true;
		} else {
			System.out.println(" Request not found");
			return false;
		}

	}

	public boolean rejectRequest(ApprovalRequest request) {
		Date today = new Date();
		request.setClosedAt(today);
		if (RequestService.ApprovalRequestExists(request)) { // the request is at the list
			request.setStatus("rejected");
			// RequestService.deleteRequest(request); //Deletes the request if it is
			// rejected
			// Show rejection message
			System.out.println(" The request for the event " + request.getEvent().getTitle() + " was rejected");
			return true;
		} else {
			System.out.println(" The request doesn't exist");
			return false;
		}

	}

	public void showRequests(String status) {
		RequestService.showRequests(status);
	}

	public void showEventListStatus() {
		eventService.showEventListStatus();
	}

}
