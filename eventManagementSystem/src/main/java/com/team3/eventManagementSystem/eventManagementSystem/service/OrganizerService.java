package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class OrganizerService {

	@Autowired
	RequestService requestService;

	List<Organizer> organizerList = new ArrayList<Organizer>();

	/**
	 * Adds an Organizer to the List with the organizers.
	 * 
	 * @param employee
	 */
	public List<Organizer> addOrganizer(Organizer organizer) {
		if (!organizerExists(organizer.getAfm())) {
			int newId = 1;
			if (organizerList.size() > 0) {
				newId = organizerList.get(organizerList.size() - 1).getId() + 1;
			}
			organizer.setId(newId);
			organizerList.add(organizer);
		}
		return organizerList;
	}

	// Checks if an organizer already exists
	private boolean organizerExists(Integer afm) {
		return organizerList.stream().anyMatch(organizer -> organizer.getAfm().equals(afm));
	}

	public void addManyOrganizers(List<Organizer> organizersToAdd) {
		organizersToAdd.stream().forEach(event -> addOrganizer(event));
	}

	/**
	 * Searches the list organizers for an Organizer by id.
	 * 
	 * @param id
	 * @return
	 */
	public Organizer findOrganizerById(Integer id) {
		Organizer o = organizerList.stream().filter(organizer -> organizer.getId().equals(id)).findFirst().orElse(null);

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

}
