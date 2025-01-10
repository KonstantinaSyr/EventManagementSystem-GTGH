package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Event {

	private int id;
	private String title;
	private String theme;
	private String description;
	private int maxCapacity;
	private int day;
	private int hour;
	private int minutes;
	// Duration is measured in Hours. Example: 1,5 is 1 Hour and 30 Minutes.
	private float duration;
	private String location;
	private Organizer organizer;
	/*
	 * Status describes the state of the event concerning it's approval from the
	 * Employee. It can have only 3 states: Awaiting, Ongoing and Ended.
	 */
	private String status;

	public Event(String title, String theme, String description, String location, int maxCapacity, int day, int hour,
			int minutes, float duration, Organizer organizer) {
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.maxCapacity = maxCapacity;
		this.day = day;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.location = location;
		this.organizer = organizer;
		this.status = "Awaiting";
	}

	public String toString() {
		return "~~~~" + title + "~~~~\n" + "Theme: " + theme + "\n" + "Quick description: " + description + "\n"
				+ "Location: " + location + "\n" + "Date: " + day + "\n" + "Start time: " + hour + ":" + minutes + "\n"
				+ "Duration: " + duration + "\n" + "Organizer: " + organizer.getName() + " " + organizer.getSurname();
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

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

	public int getMaxCapacity() {
		return maxCapacity;
	}

}
