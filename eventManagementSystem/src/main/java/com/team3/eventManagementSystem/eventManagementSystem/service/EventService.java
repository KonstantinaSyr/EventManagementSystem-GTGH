package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;

@Service
public class EventService {

	private List<Event> events = new ArrayList<>(); 
		
	/**
	 * Adds an Event to the List with the events.
	 * @param event
	 */
	public void createEvent(Event event) {
        events.add(event);
    }
	
	/**
	 * Searches the list events for an Event by id.
	 * @param id
	 * @return
	 */
    public Event findEventById(int id) {
        Event e = events.stream()
                    .filter(event -> event.getId() == id)
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
    public boolean deleteEvent(String title) {
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
    public void viewExistingEvents(){
        if(!events.isEmpty()){
            System.out.println( events.size() + " events found: ");
            IntStream.range(0, events.size())
            .filter(i -> events.get(i).getStatus().equals("Awaiting") || events.get(i).getStatus().equals("Ongoing"))
            .mapToObj(i -> (i + 1) + ". " + events.get(i).getTitle())
            .forEach(System.out::println);
        }
        else
            System.out.println("Currently there are no events happening.");
    }
    
	/**
     * Returns the list of the Events
     * @return events
     */
    public List<Event> getAllEvents() {
        return events;
    }
	
    public void showMyEvents(int organizerId) {
    	events.stream()
    		.filter(ev -> ev.getOrganizer().getId() == organizerId)
    		.forEach(event -> System.out.println(event));
    	//the method toString is overriten in event class
    }
    
    public void showEventListStatus() {
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
