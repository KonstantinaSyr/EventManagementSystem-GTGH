package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
//import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class EventService {

	private List<Event> eventList = new ArrayList<>();
	@Autowired
	OrganizerService organizerService;
	@Autowired
	ReservationService reservationService;

    

//    public EventService(OrganizerService organizerService, ReservationService reservationService) {
//		
//		this.organizerService = organizerService;
//		this.reservationService = reservationService;
//	}

	//Checks if an event already exists
    private boolean eventExists(String title) {
        return eventList.stream()
                .anyMatch(event -> event.getTitle().equalsIgnoreCase(title));
    }

    //Creates a new event
    public void addEvent(Event event) {
        if(!eventExists(event.getTitle())){
        	int newId = 1;
            if(eventList.size() > 0) {
            	newId = eventList.get(eventList.size()-1).getId() + 1;
            }
            event.setId(newId);
        	eventList.add(event);
            System.out.println("New event added.Title: " + event.getTitle()); 
        }
        else
            System.out.println("Event with this title already exists!!!");
    }

    // Adds many events to the event list
    public void addManyEvents(List<Event> eventsToAdd) {
    	eventsToAdd.stream().forEach(event -> addEvent(event));
    }
    
    // Returns an event by its id
    public Event findEventById(Integer id) {
    	Event e = eventList.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElse(null);
    	if (e != null){
            return e;
        }
        else {
            System.out.println("Invalid ID provided. Please check again!");
            return null;
        }
    }
    
    
    //Searches an event by title, location or theme
    public List<Event> findEventByCredentials(String title , String location, String theme) {
    	List<Event> outputEvents = new ArrayList<Event>();
    		if(title != null && findEventByTitle(title) != null) {
    				outputEvents.add(findEventByTitle(title));
    		}
    		if(location != null && findEventByLocation(location) != null) {
    			for(Event e : findEventByLocation(location) )
    				outputEvents.add(e);
    		}
    		if(theme != null && findEventByTheme(theme) != null) {
    			for(Event e : findEventByTheme(theme) )
    				outputEvents.add(e);
    		}
    	return outputEvents;
    }
    
    // Searches an event by location
    private List<Event> findEventByLocation(String location) {
        List<Event> eventsByLocation = eventList.stream()
                .filter(event -> event.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
             
        if(eventsByLocation != null){
            return eventsByLocation;
        }
        else {
            System.out.println("Invalid location provided. Please check again!");
            return null;
        }
    }
    
    // Searches an event by theme
    private List<Event> findEventByTheme(String theme) {
    	List<Event> eventsByTheme = eventList.stream()
                .filter(event -> event.getTheme().equalsIgnoreCase(theme))
                .collect(Collectors.toList());

        if (eventsByTheme != null){
            return eventsByTheme;
        }
        else {
            System.out.println("Invalid theme provided. Please check again!");
            return null;
        }
    }
    
    // Searches an event by title (event titles must by unique)
    private Event findEventByTitle(String title) {
        Event e = eventList.stream()
                .filter(event -> event.getTitle().equalsIgnoreCase(title))
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

    //Deletes an event by its id
    public boolean deleteEvent(Integer eventId) {
        Event eventToDelete = findEventById(eventId);

        if (eventToDelete != null) {
            eventList.remove(eventToDelete);
            reservationService.deleteReservationsByEvent(eventId);
            System.out.println("event deleted");
            return true; //Deleted successfully
        }
        return false; // No Event with this title
    }

    public void deleteEventsOfOrganizer(Integer organizerId) {
		eventList.removeIf(r -> r.getOrganizer().getId().equals(organizerId));
	}

    //Prints all events that a person can make a reservation. Event type : "Awaiting" or "Ongoing"
    public void viewExistingEvents(){
        if(!eventList.isEmpty()){
            List<Event> existingEvents = eventList.stream()
                    .filter(event -> event.getStatus().equals("Awaiting") || event.getStatus().equals("Ongoing"))
                    .toList();

            System.out.println(existingEvents.size() + " events found: ");
            IntStream.range(0,existingEvents.size())
                    .mapToObj(i -> (i+1) +"." + existingEvents.get(i).getTitle())
                    .forEach(System.out :: println);
        }
        else
            System.out.println("Currently there are no events happening.");
    }
    
    
  
    
    // Checks if an event is full
    public boolean eventIsFull(Integer eventId) {
    	boolean temp = true;
    	Event e = findEventById(eventId);
    	
    	int noVisitors = reservationService.getAllReservations().stream()
				.filter(reservation -> reservation.getEventId().equals(eventId))
				.toList().size()  ;
    	
    	if(e!=null)
    		if(e.getMaxCapacity() > noVisitors) {
    			temp = false;
    		} 	
    	return temp;
    }

    
    //Returns all events whether they are of type: ongoing, awaiting or ended
    public List<Event> getAllEvents() {
        return eventList;
    }
    
    //It takes the id of an organizer and returns all of his events
    // We should probably do it with id
    //add field id at Organizer
    public List<Event> showEventsByOrgId(Integer id) {
    	Organizer myOrganizer= organizerService.getOrganizerById(id);
    	return eventList.stream()
    			.filter(e-> e.getOrganizer().equals(myOrganizer)) 
    			.collect(Collectors.toList());
    }
    
    
}
