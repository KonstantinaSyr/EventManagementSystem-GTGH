package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Visitor {

	private int id;
	private String name;
	private String surname;
	private String email;

	// Constructor
	public Visitor(int id, String name, String surname, String email) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	// GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

}
