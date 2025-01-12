package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Organizer {

	private Integer id;
	private String name;
	private String surname;
	private String email;
	private Integer afm;
	private String description;

	// Constructor
	public Organizer(String name, String surname, String email, Integer afm, String description) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.afm = afm;
		this.description = description;
	}

	// GETTERS AND SETTERS
	public String toString() {
		return this.getName();
	}

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

	public Integer getAfm() {
		return afm;
	}

	public void setAfm(Integer afm) {
		this.afm = afm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
