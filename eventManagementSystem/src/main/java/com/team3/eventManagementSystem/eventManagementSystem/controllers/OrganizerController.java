package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;
import com.team3.eventManagementSystem.eventManagementSystem.service.OrganizerService;

@RestController
@RequestMapping("organizer")
public class OrganizerController {
	@Autowired
	OrganizerService organizerService;

	// Works for adding 1 or more Organizers
	@PostMapping("/add")
	public List<Organizer> addOrganizers(@RequestBody List<Organizer> organizers) {
		return organizerService.addManyOrganizers(organizers);
	}

	@GetMapping("/show")
	public List<Organizer> showOrganizers() {
		return organizerService.getAllOrganizers();
	}

	@PostMapping("/update")
	public List<Organizer> updateOrganizer(@RequestParam int id, @RequestParam(required = false) String name,
			@RequestParam(required = false) String surname, @RequestParam(required = false) String email,
			@RequestParam(required = false) Integer afm, @RequestParam(required = false) String description) {
		return organizerService.updateOrganizer(id, name, surname, email, afm, description);
	}

	@DeleteMapping("/delete")
	public List<Organizer> deleteOrganizer(@RequestParam int id) {
		return organizerService.deleteOrganizerById(id);
	}

	@GetMapping("/showEvents")
	public List<Event> showEvents(@RequestParam int id) {
		return organizerService.showEventsByOrgId(id);
	}

	@GetMapping("/visitors")
	public Map<Event, List<Visitor>> showVisitorsOfEventsOfOrg(@RequestParam Integer organizerId) {
		return organizerService.showVisitorsOfEventsOfOrg(organizerId);
	}
}
