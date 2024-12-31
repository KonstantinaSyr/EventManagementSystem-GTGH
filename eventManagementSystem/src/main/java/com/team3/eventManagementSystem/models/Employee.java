package com.team3.eventManagementSystem.models;

import com.team3.eventManagementSystem.service.EventService;

public class Employee extends Visitor{

	
	//Constructor
	public Employee(String Name, String Surname, String Email) {
		super(Name, Surname, Email);
	}
	
	/**
	 * Employee adds an Event to the list of events.
	 * @param event
	 */
	public void addEvent(Event event) {
		 EventService.createEvent(event);
	     System.out.println("Event added successfully: " + event.getTitle());
	}
	
	/**
	 * Employee deletes an Event from the list of events. 
	 * @param event
	 */
	public void deleteEvent(Event event) {
		 EventService.deleteEvent(event.getTitle());
	     System.out.println("Event deleted successfully: " + event.getTitle());
	}

}
