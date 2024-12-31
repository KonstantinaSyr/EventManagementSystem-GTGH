package com.team3.eventManagementSystem.models;

public class Organizer extends Visitor{
	
	private int afm;
	private String description;
	
	//Constructor
	public Organizer(String name, String surname, String email, int afm, String description) {
		super(name, surname, email);
		this.afm = afm;
		this.description = description;
	}

}
