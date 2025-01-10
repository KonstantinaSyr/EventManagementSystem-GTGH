package com.team3.eventManagementSystem.eventManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;

@Service
public class OrganizerService {

	@Autowired
	RequestService requestService;

	@Autowired
	EventService eventService;

	public void makeApproveRequest(ApprovalRequest request) {
		requestService.createRequest(request);
		System.out.println("Request for the event " + request.getEvent().getTitle() + " created successfully: ");
	}

	public void showMyEvents(int organizerId) {
		eventService.showMyEvents(organizerId);
	}

}
