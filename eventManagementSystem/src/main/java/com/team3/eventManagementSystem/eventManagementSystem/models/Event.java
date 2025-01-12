package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Event {

	private int id;
	private String title;
	private String theme;
	private String description;
	private int maxCapacity;
	private int day;
	private int month;
	private int year;
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

	public Event(String title, String theme, String description, String location, int maxCapacity, int day,int month, int year,
			int hour,int minutes, float duration, Organizer organizer) {
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.maxCapacity = maxCapacity;
		this.day = day;
		this.month = month;
		this.year = year;
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

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
