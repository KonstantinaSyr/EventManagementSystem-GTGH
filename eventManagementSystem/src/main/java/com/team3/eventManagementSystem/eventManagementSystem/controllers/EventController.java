package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@PostMapping("/addOne")
	public List<Event> addAnEvent(@RequestBody Event event){
		return eventService.addEvent(event);
	}
	
	@PostMapping("/addMany")
	public List<Event> AddManyEvents(@RequestBody List<Event> eventsToAdd){
		return eventService.addManyEvents(eventsToAdd);
		
	}
	
	@PostMapping("/bookSpot")
	public boolean bookSpot(@RequestParam Integer userId,@RequestParam Integer eventId) {
		return eventService.bookSpotForEvent(userId, eventId);
	}
	
	@DeleteMapping("/delete")
	public boolean deleteAnEvent(@RequestParam Integer id) {
		return eventService.deleteEvent(id);
	}

	@GetMapping("/all")
	public List<Event> getAll(){
		return eventService.getAllEvents();
	}
	
	@GetMapping("/existing")
	public List<Event> getExisting(){
		return eventService.viewExistingEvents();
	}
	
	@GetMapping("/byCredentials")
	public List<Event> viewByCredentials(@RequestParam(required=false) String title,@RequestParam(required=false) String location,
			@RequestParam(required=false) String theme,
			@RequestParam(required=false) Integer day, @RequestParam(required=false) Integer month, @RequestParam(required=false) Integer year){
		return eventService.findEventByCredentials(title, location, theme, day, month, year);
	}
	
	@PutMapping("/update")
	public boolean updateAnEvent(@RequestParam Integer eventId,@RequestParam(required=false) String theme,
			@RequestParam(required=false) String location,@RequestParam(required=false) String description,
			@RequestParam(required=false) Integer day,@RequestParam(required=false) Integer month,
			@RequestParam(required=false) Integer year,@RequestParam(required=false) Integer hour,
			@RequestParam(required=false) Integer minutes,@RequestParam(required=false) Double duration) {
		
		return eventService.updateEvent(eventId, theme, location, description, day, month, year, hour, minutes, duration);
	}
	
}
