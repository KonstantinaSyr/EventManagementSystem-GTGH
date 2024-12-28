package com.team3.eventManagementSystem.models;

public class Organizer extends Visitor{
	
	private int AFM;
	private String Description;
	
	//Constructor
	public Organizer(String Name, String Surname, String Email, int AFM, String Description) {
		super(Name, Surname, Email);
		this.AFM = AFM;
		this.Description = Description;
	}

}
