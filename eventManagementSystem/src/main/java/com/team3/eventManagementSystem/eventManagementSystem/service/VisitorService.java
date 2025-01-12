package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

public class VisitorService {

	private List<Visitor> visitorList = new ArrayList<>();
	private int nextKey = 1;

	public VisitorService() {
	}

	// Checks if a visitor exists in the visitor list
	private boolean visitorExists(Visitor visitor) {
		return visitorList.stream().anyMatch(v -> v.getEmail().equals(visitor.getEmail()));
	}
	
	
	// Adds a new visitor to the visitor list
	public void addNewVisitor(Visitor visitor) {
		if (!visitorExists(visitor)) {
			visitor.setId(nextKey);
			visitorList.add(visitor);
			System.out.println("Visitor added successfully");
			nextKey++;
		} else
			System.out.println("This visitor already exists");
	}
	
	// Adds many new visitors at once
	public void addManyVisitors(List<Visitor> visitorsToAdd) {
    	visitorsToAdd.stream().forEach(event -> addNewVisitor(event));
    }
	
	// Returns a visitor by their id
	public Visitor findVisitorById(int userId) {
		Visitor visitor = visitorList.stream().filter(v -> v.getId() == userId).findFirst().orElse(null);

		if (visitor != null) {
			System.out.println(visitor.getName() + " " + visitor.getSurname());
			return visitor;
		} else {
			System.out.println("Invalid user name provided. Please check again!");
			return null;
		}
	}
	
	
	// Deletes a visitor by their id
	public void deleteVisitor(int userId) {
		Visitor visitorToDelete = findVisitorById(userId);
		if(visitorToDelete != null) {
			visitorList.remove(visitorToDelete);
			System.out.println("Visitor removed: " + visitorToDelete);
		}
	}
	
	// Returns the list of all the visitors
	public List<Visitor> getTotalVisitors() {
		return visitorList;
	}

}
