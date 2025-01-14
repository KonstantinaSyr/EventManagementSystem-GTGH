package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;

@Service
public class EventService {

	private List<Event> eventList = new ArrayList<>();
	private int newId=1;

	@Autowired
	private ReservationService reservationService;

	/**
	 * Adds an Event to the List with the events.
	 * 
	 * @param event
	 */
	public List<Event> addEvent(Event event) {
		if (!eventExists(event.getTitle())) {
			event.setId(newId);
			newId++ ;
			eventList.add(event);
		}
		return eventList;
	}

	// Checks if an event already exists
	private boolean eventExists(String title) {
		return eventList.stream().anyMatch(event -> event.getTitle().equalsIgnoreCase(title));
	}

	// Adds many events to the event list
	public List<Event> addManyEvents(List<Event> eventsToAdd) {
		eventsToAdd.stream().forEach(event -> addEvent(event));
		return eventList;
	}

	/**
	 * Searches the list events for an Event by id.
	 * 
	 * @param id
	 * @return
	 */

	public Event findEventById(Integer id) {
		Event e = eventList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid ID provided. Please check again!");
			return null;
		}
	}

	/**
	 * Delete an Event from the List events by Id.
	 * 
	 * @param eventId
	 * @return
	 */

	public boolean deleteEvent(Integer eventId) {
		Event eventToDelete = findEventById(eventId);
		if (eventToDelete != null) {
			reservationService.deleteAllReservationsByEvent(eventId);
			eventList.remove(eventToDelete);
			System.out.println("event deleted");
			return true; // Deleted successfully
		}
		else
			return false; // No event with the given id
	}
	
	
	public boolean updateEvent(Integer eventId, String theme, String location, String description, Integer day,
			Integer month, Integer year, Integer hour, Integer minutes, Double duration) {
		if (!eventId.equals(null)) {
			Event eventToUpdate = findEventById(eventId);
			if (eventToUpdate != null) {
				if (theme != null) {
					eventToUpdate.setTheme(theme);
				}
				if (location != null) {
					eventToUpdate.setLocation(location);
				}
				if (description != null) {
					eventToUpdate.setDescription(description);
				}
				if (day != null) {
					eventToUpdate.setDay(day);
				}
				if (month != null) {
					eventToUpdate.setMonth(month);
				}
				if (year != null) {
					eventToUpdate.setYear(year);
				}
				if (hour != null) {
					eventToUpdate.setHour(hour);
				}
				if (minutes != null) {
					eventToUpdate.setHour(hour);
				}
				if (duration != null) {
					eventToUpdate.setDuration(duration);
				}
			}
			return true;
		}
		else
			return false;
	}

	/**
	 * Returns the list of the Events
	 * 
	 * @return events
	 */
	public List<Event> getAllEvents() {
		return eventList;
	}

	public void showEventListStatus() {
		int size = eventList.size();
		long awaitingSize = eventList.stream().filter(ev -> ev.getStatus().equals("awaiting")).count();
		long ongoingSize = eventList.stream().filter(ev -> ev.getStatus().equals("ongoing")).count();
		long endedSize = eventList.stream().filter(ev -> ev.getStatus().equals("ended")).count();
		System.out.println(" There are " + size + " events in total");
		System.out.println(" There are " + awaitingSize + " events with awaiting status");
		System.out.println(" There are " + ongoingSize + " events with awaiting status");
		System.out.println(" There are " + endedSize + " events with ended status");
	}

	/*
	 * If there are events on the event list, the function prints the titles of the
	 * available events , else it prints the appropriate message.
	 */
	public List<Event> viewExistingEvents() {
		if (!eventList.isEmpty()) {
			List<Event> existingEvents = eventList.stream().filter(event -> event.getStatus().equals("Awaiting")
					|| event.getStatus().equals("Ongoing")).toList();
			return existingEvents;
			
		} else
			return null;
	}
	

	// Searches an event by title, location or theme
	public List<Event> findEventByCredentials(String title, String location, String theme,
			Integer day, Integer month, Integer year) {
		List<Event> outputEvents = new ArrayList<Event>();
		List<Event> existingEvents = viewExistingEvents();
		
		if(existingEvents != null) {
			if(title != null && findEventByTitle(title) != null) {
				outputEvents.add(findEventByTitle(title));
			}
			if(location != null && findEventByLocation(location) != null) {
				for(Event e : findEventByLocation(location))
					outputEvents.add(e);
			}
			if (theme != null && findEventByTheme(theme) != null) {
				for(Event e : findEventByTheme(theme))
					outputEvents.add(e);
			}
			if(day != null && month != null && year != null && findEventByDay(day,month,year) != null) {
				for(Event e : findEventByDay(day,month,year))
					outputEvents.add(e);
			}
			if(day == null && month != null && year != null && findEventByMonth(month,year) != null) {
				for(Event e : findEventByMonth(month,year))
					outputEvents.add(e);
			}
			if(day == null && month == null && year != null && findEventByYear(year) != null) {
				for(Event e : findEventByYear(year))
					outputEvents.add(e);
			}
			return outputEvents;
		}
		else 
			return null;
		
	}
	
	//Searches an event by year
	private List<Event> findEventByYear(Integer year){
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			List<Event> events = existingEvents.stream()
					.filter(event -> event.getYear().equals(year)).collect(Collectors.toList());
			if(!events.isEmpty()) {
				return events;
			}
			else
				return null;
		}
		else
			return null;
	}
	
	//Searches an event by a month of a year
	private List<Event> findEventByMonth(Integer month, Integer year) {
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			List<Event> events = existingEvents.stream()
					.filter(event -> event.getYear().equals(year) && event.getMonth().equals(month)).collect(Collectors.toList());
			if(!events.isEmpty()) {
				return events;
			}
			else
				return null;
		}
		else
			return null;
	}
	
	//Searches an event by a day of a month
	private List<Event> findEventByDay(Integer day, Integer month, Integer year){
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			List<Event> events = existingEvents.stream()
					.filter(event -> event.getDay().equals(day) 
							&& event.getMonth().equals(month) && event.getYear().equals(year)).collect(Collectors.toList()) ;
			if(!events.isEmpty()) {
				return events;
			}
			else
				return null;
		}
		else
			return null;
	}

	// Searches an event by location
	private List<Event> findEventByLocation(String location) {
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			List<Event> events = existingEvents.stream()
					.filter(event -> event.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());
			if(!events.isEmpty()) {
				return events;
			}
			else
				return null;
		}
		else
			return null;
	}

	// Searches an event by theme
	private List<Event> findEventByTheme(String theme) {
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			List<Event> events = existingEvents.stream()
					.filter(event -> event.getTheme().equalsIgnoreCase(theme))
					.collect(Collectors.toList());
			if(!events.isEmpty()) {
				return events;
			}
			else
				return null;
		}
		else
			return null;
	}

	// Searches an event by title (event titles must by unique)
	private Event findEventByTitle(String title) {
		List<Event> existingEvents = viewExistingEvents();
		if(!existingEvents.isEmpty()) {
			Event e = existingEvents.stream().filter(event -> event.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
			if(e != null) {
				return e;
			}
			else
				return null;
		}
		else
			return null;
	}

	// Takes the id of an organizer and deletes his events and the reservations of these events
	public List<Event> deleteEventsOfOrganizer(Integer organizerId) {
		//deletes the reservations
		for(Event e: eventList) {
			if(e.getOrganizerId().equals(organizerId)) {
				reservationService.deleteAllReservationsByEvent(e.getId());
			}
		}
		//deletes the events
		eventList.removeIf(r -> r.getOrganizerId().equals(organizerId));
		return eventList;
	}

	// Check if a reservation can be made and creates a reservation if so
	public void checkForReservation(Integer userId, Integer eventId) {
		if (userId != null && eventId != null) {
			if (this.eventIsFull(eventId))
				reservationService.createReservation(userId, eventId);
		}
	}

	// Checks if an event is full
	public boolean eventIsFull(Integer eventId) {
		boolean temp = true;
		Event e = findEventById(eventId);

		int noVisitors = reservationService.getAllReservations().stream()
				.filter(reservation -> reservation.getEventId().equals(eventId)).toList().size();

		if (e != null)
			if (e.getMaxCapacity() > noVisitors) {
				temp = false;
			}
		return temp;
	}

}
