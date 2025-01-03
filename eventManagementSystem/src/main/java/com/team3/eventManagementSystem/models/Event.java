package com.team3.eventManagementSystem.models;

import java.util.ArrayList;

public class Event {
	
	private String title;
	private String theme;
	private String description;
	private int maxCapacity; 
	private int day;
	private int hour;
	private int minutes;
	//Duration is measured  in Hours. Example: 1,5 is 1 Hour and 30 Minutes.
	private float duration; 
	private Organizer organizer;
	/*Status describes the state of the event concerning it's approval from the Employee.
	 * It can have only 3 states: Pending, Approved and Rejected.
	 */
	private String status;  
	
	public Event(String title, String theme, String description, int maxCapacity, int day, int hour, int minutes,
			float duration, com.team3.eventManagementSystem.models.Organizer organizer) {
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.maxCapacity = maxCapacity;
		this.day = day;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.organizer = organizer;
		this.status = "Pending";
	}

	//GETTERS AND SETTERS
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	@Override
	public String toString() {
		return("Event's title: "+ title + ", description: " + description);
	}
	
	
	
	
}
