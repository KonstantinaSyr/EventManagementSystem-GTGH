package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

public class EventService {

	private List<Event> events = new ArrayList<>();


    public EventService() {
    }

    //checks if an event already exists
    private boolean eventExists(String title) {
        return events.stream()
                .anyMatch(event -> event.getTitle().equalsIgnoreCase(title));
    }

    //creates a new event
    public void createEvent(Event event) {
        if(!eventExists(event.getTitle())){
//            int oldId = event.getId();
//            int newId = events.size() + 1 ;
//            event.setId(newId);
            events.add(event);
            //System.out.println("Old id " + oldId);
            System.out.println("New event added.Title: " + event.getTitle());
        }
        else
            System.out.println("Event with this title already exists!!!");

    }

    //searches an event by title
    public Event findEventByTitle(String title) {
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

    //deletes an event
    public boolean deleteEvent(String title) {
        Optional<Event> eventToDelete = events.stream()
                .filter(event -> event.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (eventToDelete.isPresent()) {
            events.remove(eventToDelete.get());
            System.out.println("event deleted");
            return true; //Deleted successfully
        }
        return false; // No Event with this title
    }


    //prints all events that a person can make a reservation
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

    public List<Event> getAllEvents() {
        return events;
    }
}
