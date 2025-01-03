package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
        Event e = events.stream()
                    .filter(event -> event.getTitle().equals(title))
                    .findFirst()
                    .orElse(null);
        
        if (e != null){
            return e;
        }
        else {
            System.out.println("Invalid title provided. Please check again!");
            return null;
        }
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
    
    /*If there are events on the event list, the function prints the titles of the available events ,
    else it prints the appropriate message.
     */
    public static void viewExistingEvents(){
        if(!events.isEmpty()){
            System.out.println( events.size() + " events found: ");
            IntStream.range(0,events.size())
                    .mapToObj(i -> (i+1) + "." + events.get(i).getTitle() )
                    .forEach(System.out :: println);
        }
        else
            System.out.println("Currently there are no events happening.");
    }
    
	/**
     * Returns the list of the Events
     * @return events
     */
    public static List<Event> getAllEvents() {
        return events;
    }
	
}
