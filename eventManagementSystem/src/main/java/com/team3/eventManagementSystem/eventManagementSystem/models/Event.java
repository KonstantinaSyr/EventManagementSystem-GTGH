package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Event {
	
	private Integer id;
	private String title;
	private String theme;
	private String description;
	private Integer maxCapacity;
	private Integer day;
	private Integer month;
	private Integer year;
	private Integer hour;
	private Integer minutes;
	// Duration is measured in Hours. Example: 1,5 is 1 Hour and 30 Minutes.
	private Double duration;
	private String location;
	private Integer organizerId;
	/*
	 * Status describes the state of the event concerning it's approval from the
	 * Employee. It can have only 3 states: Awaiting, Ongoing and Ended.
	 */
	private String status;

	public Event(String title, String theme, String description, String location, Integer maxCapacity, Integer day,
			Integer month, Integer year, Integer hour, Integer minutes, Double duration, Integer organizerId) {
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
		this.organizerId = organizerId;
		this.status = "Awaiting";
	}

	public String toString() {
		return "~~~~" + title + "~~~~\n" + "Theme: " + theme + "\n" + "Quick description: " + description + "\n"
				+ "Location: " + location + "\n" + "Date: " + day + "\n" + "Start time: " + hour + ":" + minutes + "\n"
				+ "Duration: " + duration + "\n" ;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(Integer organizerId) {
		this.organizerId = organizerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
