package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class OrganizerService {
	
	private List<Organizer> organizers = new ArrayList<>();

	// Takes the id of an organizer and returns the organizer
	public Organizer getOrganizerById(int id) {
		Organizer myOrganizer =organizers.stream().
			filter(o-> o.getId()==id).
			findFirst().orElse(null);
		return myOrganizer;
	}
	
}
