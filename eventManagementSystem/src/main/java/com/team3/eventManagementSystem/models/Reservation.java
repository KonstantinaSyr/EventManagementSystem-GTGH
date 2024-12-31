package com.team3.eventManagementSystem.models;

public class Reservation {
	
	private Visitor visitor;
	private Event event;
	
	public Reservation(Visitor visitor, Event event) {
		this.visitor = visitor;
		this.event = event;
	}

}
