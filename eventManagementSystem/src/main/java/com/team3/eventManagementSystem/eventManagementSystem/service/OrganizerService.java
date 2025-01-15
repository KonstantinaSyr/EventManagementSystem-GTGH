package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

@Service
public class OrganizerService {

	@Autowired
	private EventService eventService;
	@Autowired
	private VisitorService visitorService;

	private List<Organizer> organizerList = new ArrayList<Organizer>();
	private int newId = 1;

	public List<Organizer> getAllOrganizers() {
		return organizerList;
	}

	public List<Organizer> addOrganizer(Organizer organizer) {
		if (!organizerExists(organizer.getAfm())) {
			organizer.setId(newId);
			newId++;
			organizerList.add(organizer);
		}
		return organizerList;
	}

	// The afm is like a key for the entity Organizer. The method checks if the
	// Organizer exists in the list by the afm
	private boolean organizerExists(Integer afm) {
		return organizerList.stream().anyMatch(organizer -> organizer.getAfm().equals(afm));
	}

	public List<Organizer> addManyOrganizers(List<Organizer> organizersToAdd) {
		organizersToAdd.stream().forEach(event -> addOrganizer(event));
		return organizerList;
	}

	// Searches the list organizers for an Organizer by id.
	public Organizer findOrganizerById(Integer id) {
		Organizer o = organizerList.stream().filter(organizer -> organizer.getId().equals(id)).findFirst().orElse(null);

		if (o != null) {
			return o;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}

	// Updates the fields of an Organizer and returns the list with all the
	// organizers.
	public List<Organizer> updateOrganizer(Integer organizerId, String name, String surname, String email, Integer afm,
			String description) {
		if (!organizerId.equals(null)) {
			Organizer organizerToUpdate = this.findOrganizerById(organizerId);

			if (name != null)
				organizerToUpdate.setName(name);
			if (surname != null)
				organizerToUpdate.setSurname(surname);
			if (email != null)
				organizerToUpdate.setSurname(email);
			if (afm != null) {
				organizerToUpdate.setAfm(afm);
			}
			if (description != null)
				organizerToUpdate.setDescription(description);
		}
		return organizerList;
	}

	// Takes the id of an organizer and deletes the organizer and his events
	public List<Organizer> deleteOrganizerById(int orgId) {
		Organizer myOrganizer = findOrganizerById(orgId);
		if (myOrganizer != null) {
			eventService.deleteEventsOfOrganizer(orgId);
			organizerList.remove(myOrganizer);
		}
		return organizerList;
	}

	// It takes the id of an organizer and returns all of his events
	// We should probably do it with id
	// add field id at Organizer
	public List<Event> showEventsByOrgId(Integer id) {
		return eventService.getAllEvents().stream().filter(e -> e.getOrganizerId().equals(id))
				.collect(Collectors.toList());
	}

	// Given an organizerId it returns the visitors grouped by the event they are
	// in,
	// of the events made by the Organizer
	public Map<Event, List<Visitor>> showVisitorsOfEventsOfOrg(Integer organizerId) {
		List<Event> eventsOfOrganizer = this.showEventsByOrgId(organizerId);
		Map<Event, List<Visitor>> eventVisitorsMap = new HashMap<>();

		if (eventsOfOrganizer != null) {
			for (Event event : eventsOfOrganizer) {
				List<Visitor> visitorsForEvent = visitorService.getVisitorsForEvent(event.getId());
				eventVisitorsMap.put(event, visitorsForEvent);
			}
		}

		return eventVisitorsMap;
	}

}
