package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class OrganizerService {

	@Autowired
	RequestService requestService;

	@Autowired
	EventService eventService;

	List<Organizer> organizers = new ArrayList<Organizer>();

	/**
	 * Adds an Organizer to the List with the organizers.
	 * 
	 * @param employee
	 */
	public void addOrganizer(Organizer organizer) {
		organizers.add(organizer);
	}

	/**
	 * Searches the list organizers for an Organizer by id.
	 * 
	 * @param id
	 * @return
	 */
	public Organizer findOrganizerById(int id) {
		Organizer o = organizers.stream().filter(organizer -> organizer.getId() == id).findFirst().orElse(null);

		if (o != null) {
			return o;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}

	public void makeApproveRequest(ApprovalRequest request) {
		requestService.createRequest(request);
		System.out.println("Request for the event " + request.getEvent().getTitle() + " created successfully: ");
	}

	public void showMyEvents(int organizerId) {
		eventService.showMyEvents(organizerId);
	}

}
