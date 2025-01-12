package com.team3.eventManagementSystem.eventManagementSystem.models;

import com.team3.eventManagementSystem.eventManagementSystem.service.EventService;
import com.team3.eventManagementSystem.eventManagementSystem.service.RequestService;

public class Organizer extends Visitor {

	private int afm;
	private String description;

	// Constructor
	public Organizer(int id, String name, String surname, String email, int afm, String description) {
		super(id, name, surname, email);
		this.afm = afm;
		this.description = description;
	}

	public String toString() {
		return this.getName();
	}

	// GETTERS AND SETTERS
	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
