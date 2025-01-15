package com.team3.eventManagementSystem.eventManagementSystem.models;

import java.util.ArrayList;
import java.util.List;

public class Employee{
	
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private List<Event> deletedEvents = new ArrayList<Event>();

	// Constructor
	public Employee(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addToDeletedEvents(Event deletedEvent) {
		deletedEvents.add(deletedEvent);
	}
	

}
