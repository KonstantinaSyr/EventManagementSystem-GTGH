package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.team3.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.models.Organizer;

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
	
    public static void showMyEvents(Organizer org) {
    	events.stream()
    		.filter(ev -> ev.getOrganizer().equals(org))
    		.forEach(event -> System.out.println(event));
    	//the method toString is overriten in event class
    }
    
    public static void showEventListStatus() {
    	int size= events.size();
    	long awaitingSize= events.stream()
    			.filter(ev -> ev.getStatus().equals("awaiting"))
    			.count();
    	long ongoingSize= events.stream()
    			.filter(ev -> ev.getStatus().equals("ongoing"))
    			.count();
    	long endedSize= events.stream()
    			.filter(ev -> ev.getStatus().equals("ended"))
    			.count();
    	System.out.println(" There are " + size + " events in total");
    	System.out.println(" There are " + awaitingSize + " events with awaiting status");
    	System.out.println(" There are " + ongoingSize + " events with awaiting status");
    	System.out.println(" There are " + endedSize + " events with ended status");

    }
}
