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

	private List<Event> events = new ArrayList<>();
	private int nextKey = 1; 
	@Autowired
	OrganizerService organizerService;
	@Autowired
	ReservationService reservationService;

    public EventService() {
    }

    //Checks if an event already exists
    private boolean eventExists(String title) {
        return events.stream()
                .anyMatch(event -> event.getTitle().equalsIgnoreCase(title));
    }

    //Creates a new event
    public void addEvent(Event event) {
        if(!eventExists(event.getTitle())){
            event.setId(nextKey);
        	events.add(event);
            System.out.println("New event added.Title: " + event.getTitle());
            nextKey++;
        }
        else
            System.out.println("Event with this title already exists!!!");
    }

    // Adds many events to the event list
    public void addManyEvents(List<Event> eventsToAdd) {
    	eventsToAdd.stream().forEach(event -> addEvent(event));
    }
    
    // Returns an event by its id
    public Event findEventById(int id) {
    	Event e = events.stream()
                .filter(event -> (event.getId() == id))
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
        List<Event> eventsByLocation = events.stream()
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
    	List<Event> eventsByTheme = events.stream()
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
        Event e = events.stream()
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
    public boolean deleteEvent(int id) {
        Event eventToDelete = findEventById(id);

        if (eventToDelete != null) {
            events.remove(eventToDelete);
            System.out.println("event deleted");
            return true; //Deleted successfully
        }
        return false; // No Event with this title
    }


    //Prints all events that a person can make a reservation. Event type : "Awaiting" or "Ongoing"
    public void viewExistingEvents(){
        if(!events.isEmpty()){
            List<Event> existingEvents = events.stream()
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
    
    
    // Returns the names of the visitors that have reserved a spot for a certain event
    /*  public List<String> getVisitorsForEvent(int eventId) {
   
		List<Reservation> reservationList = reservationService.getAllReservations();
		
		if (eventId != 0) {
			List<String> nameList = reservationList.stream()
					.filter(reservation -> reservation.getEventId()==eventId)
					.map(reservation -> visitorService.findVisitorById(reservation.getVisitorId()).getName()).toList();

			if (nameList.isEmpty()) {
				System.out.println("No one has made a reservation for the event");
				
			} else {
				System.out.println("People who have reserved a spot are: ");
				nameList.forEach(System.out::println);
				}
			return nameList;
			}	
		return null;
		
	}*/
    
    
    // Checks if an event is full
    public boolean eventIsFull(int eventId) {
    	boolean temp = true;
    	Event e = findEventById(eventId);
    	
    	int noVisitors = reservationService.getAllReservations().stream()
				.filter(reservation -> reservation.getEventId()==eventId)
				.toList().size()  ;
    	
    	if(e!=null)
    		if(e.getMaxCapacity() > noVisitors) {
    			temp = false;
    		} 	
    	return temp;
    }

    
    //Returns all events whether they are of type: ongoing, awaiting or ended
    public List<Event> getAllEvents() {
        return events;
    }
    
    //It takes the id of an organizer and returns all of his events
    // We should probably do it with id
    //add field id at Organizer
    public List<Event> showEventsByOrgId(int id) {
    	Organizer myOrganizer= organizerService.getOrganizerById(id);
    	return events.stream()
    			.filter(e-> e.getOrganizer().equals(myOrganizer)) 
    			.collect(Collectors.toList());
    }
    
    
}
