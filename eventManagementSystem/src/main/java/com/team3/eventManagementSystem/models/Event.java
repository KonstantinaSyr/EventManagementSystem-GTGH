package com.team3.eventManagementSystem.models;

import java.util.ArrayList;

public class Event {
	
	private String Title;
	private String Theme;
	private String Description;
	private int maxCapacity; 
	private int Day;
	private int Hour;
	private int Minutes;
	//Duration is measured  in Hours. Example: 1,5 is 1 Hour and 30 Minutes.
	private float Duration; 
	private Organizer Organizer;
	/*Status describes the state of the event concerning it's approval from the Employee.
	 * It can have only 3 states: Pending, Approved and Rejected.
	 */
	private String Status;  
	private ArrayList<Visitor> Visitors = new ArrayList<>();
	
	public Event(String title, String theme, String description, int maxCapacity, int day, int hour, int minutes,
			float duration, com.team3.eventManagementSystem.models.Organizer organizer) {
		Title = title;
		Theme = theme;
		Description = description;
		this.maxCapacity = maxCapacity;
		Day = day;
		Hour = hour;
		Minutes = minutes;
		Duration = duration;
		Organizer = organizer;
		this.Status = "Pending";
	}
	
	public void addVisitor(Visitor visitor) {
		Visitors.add(visitor);
	}

	//GETTERS AND SETTERS
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	
}
