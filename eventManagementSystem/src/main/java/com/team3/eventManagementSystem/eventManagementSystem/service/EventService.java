package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class EventService {

	private List<Event> eventList = new ArrayList<>();

	@Autowired
	OrganizerService organizerService;
	@Autowired
	ReservationService reservationService;

	/**
	 * Adds an Event to the List with the events.
	 * 
	 * @param event
	 */
	public List<Event> addEvent(Event event) {
		if (!eventExists(event.getTitle())) {
			int newId = 1;
			if (eventList.size() > 0) {
				newId = eventList.get(eventList.size() - 1).getId() + 1;
			}
			event.setId(newId);
			eventList.add(event);
		}
		return eventList ;
	}

	// Checks if an event already exists
	private boolean eventExists(String title) {
		return eventList.stream().anyMatch(event -> event.getTitle().equalsIgnoreCase(title));
	}

	// Adds many events to the event list
	public void addManyEvents(List<Event> eventsToAdd) {
		eventsToAdd.stream().forEach(event -> addEvent(event));
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
			eventList.remove(eventToDelete);
			reservationService.deleteAllReservationsByEvent(eventId);
			System.out.println("event deleted");
			return true; // Deleted successfully
		}
		return false; // No Event with this title
	}

	/**
	 * Returns the list of the Events
	 * 
	 * @return events
	 */
	public List<Event> getAllEvents() {
		return eventList;
	}

	// It takes the id of an organizer and returns all of his events
	// We should probably do it with id
	// add field id at Organizer
	public List<Event> showEventsByOrgId(Integer id) {
		Organizer myOrganizer = organizerService.findOrganizerById(id);
		return eventList.stream().filter(e -> e.getOrganizer().equals(myOrganizer)).collect(Collectors.toList());
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
	public void viewExistingEvents() {
		if (!eventList.isEmpty()) {
			System.out.println(eventList.size() + " events found: ");
			IntStream.range(0, eventList.size())
					.filter(i -> eventList.get(i).getStatus().equals("Awaiting")
							|| eventList.get(i).getStatus().equals("Ongoing"))
					.mapToObj(i -> (i + 1) + ". " + eventList.get(i).getTitle()).forEach(System.out::println);
		} else
			System.out.println("Currently there are no events happening.");
	}

	// Searches an event by title, location or theme
	public List<Event> findEventByCredentials(String title, String location, String theme) {
		List<Event> outputEvents = new ArrayList<Event>();
		if (title != null && findEventByTitle(title) != null) {
			outputEvents.add(findEventByTitle(title));
		}
		if (location != null && findEventByLocation(location) != null) {
			for (Event e : findEventByLocation(location))
				outputEvents.add(e);
		}
		if (theme != null && findEventByTheme(theme) != null) {
			for (Event e : findEventByTheme(theme))
				outputEvents.add(e);
		}
		return outputEvents;
	}

	// Searches an event by location
	private List<Event> findEventByLocation(String location) {
		List<Event> eventsByLocation = eventList.stream()
				.filter(event -> event.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());

		if (eventsByLocation != null) {
			return eventsByLocation;
		} else {
			System.out.println("Invalid location provided. Please check again!");
			return null;
		}
	}

	// Searches an event by theme
	private List<Event> findEventByTheme(String theme) {
		List<Event> eventsByTheme = eventList.stream().filter(event -> event.getTheme().equalsIgnoreCase(theme))
				.collect(Collectors.toList());

		if (eventsByTheme != null) {
			return eventsByTheme;
		} else {
			System.out.println("Invalid theme provided. Please check again!");
			return null;
		}
	}

	// Searches an event by title (event titles must by unique)
	private Event findEventByTitle(String title) {
		Event e = eventList.stream().filter(event -> event.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);

		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}

	public void deleteEventsOfOrganizer(Integer organizerId) {
		eventList.removeIf(r -> r.getOrganizer().getId().equals(organizerId));
	}

	// Check if a reservation can be made
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
