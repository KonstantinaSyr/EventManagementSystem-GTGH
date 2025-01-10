package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

public class VisitorService {

	private List<Visitor> visitorList = new ArrayList<>();
	private int nextKey = 1;

	public VisitorService() {
	}

	private boolean visitorExists(int userId) {
		return visitorList.stream().anyMatch(visitor -> visitor.getId() == (userId));
	}

	public void addNewVisitor(Visitor visitor) {
		if (!visitorExists(visitor.getId())) {
			visitor.setId(nextKey);
			visitorList.add(visitor);
			System.out.println("Visitor added successfully");
			nextKey++;
		} else
			System.out.println("There already exists a visitor with that username");
	}

	public Visitor findVisitorById(int userId) {
		Visitor visitor = visitorList.stream().filter(v -> v.getId() == userId).findFirst().orElse(null);

		if (visitor != null) {
			return visitor;
		} else {
			System.out.println("Invalid user name provided. Please check again!");
			return null;
		}

	}

	public void deleteVisitor(int userId) {
		Optional<Visitor> visitorToDelete = visitorList.stream().filter(v -> v.getId() == userId).findFirst();
		
		if (visitorToDelete.isPresent()) {
			visitorList.remove(visitorToDelete.get());
			System.out.println("Visitor removed: " + visitorToDelete);
		}
	}

	public List<Visitor> getTotalVisitors() {
		return visitorList;
	}

}
