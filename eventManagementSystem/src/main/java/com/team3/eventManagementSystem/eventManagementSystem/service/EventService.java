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
	private int newId = 1;

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private EmployeeService employeeService;

	public List<Event> addEvent(Event event) {
		if (!eventExists(event.getTitle())) {
			event.setId(newId);
			newId++;
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

	// Searches the list events for an Event by id.
	public Event findEventById(Integer id) {
		Event e = eventList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid ID provided. Please check again!");
			return null;
		}
	}

	// Delete an Event from the List events by Id.
	public boolean deleteEvent(Integer eventId, Integer employeeId) {
		Event eventToDelete = findEventById(eventId);
		if (eventToDelete != null) {
			reservationService.deleteAllReservationsByEvent(eventId);
			employeeService.addToDeletedList(eventToDelete, employeeId);
			eventList.remove(eventToDelete);
			System.out.println("event deleted");
			return true; // Deleted successfully
		} else
			return false; // No event with the given id
	}

	// Update an Event
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
		} else
			return false;
	}

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

	// If there are events on the event list, the function prints the titles of the
	// available events , else it prints the appropriate message.
	public List<Event> viewExistingEvents() {
		if (!eventList.isEmpty()) {
			List<Event> existingEvents = eventList.stream()
					.filter(event -> event.getStatus().equals("Awaiting") || event.getStatus().equals("Ongoing"))
					.toList();
			return existingEvents;

		} else
			return null;
	}

	// Searches an event by title, location or theme
	public List<Event> findEventByCriteria(String title, String location, String theme, Integer day, Integer month,
			Integer year) {
		List<Event> outputEvents = viewExistingEvents(); ;

		if (!outputEvents.isEmpty()) {
			if(title!=null) {				
			outputEvents = outputEvents.stream().filter(event -> event.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
			}
			if(location!=null) {
				outputEvents = outputEvents.stream().filter(event -> event.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());
			}
			if(theme!=null) {
				outputEvents = outputEvents.stream().filter(event -> event.getTheme().equalsIgnoreCase(theme)).collect(Collectors.toList());
			}
			if(day!=null) {
				outputEvents = outputEvents.stream().filter(event -> event.getDay().equals(day)).collect(Collectors.toList());
			}
			if(month!=null) {
				outputEvents = outputEvents.stream().filter(event -> event.getMonth().equals(month)).collect(Collectors.toList());
			}
			if(year != null) {
				outputEvents = outputEvents.stream().filter(event -> event.getYear().equals(year)).collect(Collectors.toList());
			}
			
			return outputEvents;
		} else
			return null;
	}
	


	// Takes the id of an organizer and deletes his events and the reservations of
	// these events
	public List<Event> deleteEventsOfOrganizer(Integer organizerId) {
		// deletes the reservations
		for (Event e : eventList) {
			if (e.getOrganizerId().equals(organizerId)) {
				reservationService.deleteAllReservationsByEvent(e.getId());
			}
		}
		// deletes the events
		eventList.removeIf(r -> r.getOrganizerId().equals(organizerId));
		return eventList;
	}

	// Check if a reservation can be made and creates a reservation if so
	public boolean bookSpotForEvent(Integer userId, Integer eventId) {
		if (userId != null && eventId != null) {
			if (this.eventIsFull(eventId) == false) {
				reservationService.createReservation(userId, eventId);
				return true;
			} else
				return false;
		} else
			return false;
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
