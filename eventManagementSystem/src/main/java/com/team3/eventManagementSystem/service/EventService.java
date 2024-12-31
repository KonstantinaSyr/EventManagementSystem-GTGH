package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.team3.eventManagementSystem.models.Event;

public class EventService {

	private static List<Event> events = new ArrayList<>();
		
	/**
	 * Adds an Event to the List with the events.
	 * @param event
	 */
	public static void createEvent(Event event) {
        events.add(event);
    }
	
	/**
	 * Searches the list events for an Event by title.
	 * @param title
	 * @return
	 */
    public static Event findEventByTitle(String title) {
        return events.stream()
                    .filter(event -> event.getTitle().equals(title))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Delete an Event from the List events.
     * @param title
     * @return
     */
    public static boolean deleteEvent(String title) {
        Optional<Event> eventToDelete = events.stream()
                                              .filter(event -> event.getTitle().equals(title))
                                              .findFirst();
        
        if (eventToDelete.isPresent()) {
            events.remove(eventToDelete.get());
            return true; //Deleted successfully
        }
        return false; // No Event with this title
    }
    
	/**
     * Returns the list of the Events
     * @return events
     */
    public static List<Event> getAllEvents() {
        return events;
    }
	
}
