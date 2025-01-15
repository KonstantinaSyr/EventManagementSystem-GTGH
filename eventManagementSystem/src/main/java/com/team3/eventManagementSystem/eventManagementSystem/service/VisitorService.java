package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

@Service
public class VisitorService {

	@Autowired
	EventService eventService;

	@Autowired
	ReservationService reservationService;

	private List<Visitor> visitorList = new ArrayList<Visitor>();
	//Counter for assigning an id to a new Visitor
	private int newId = 1;

	public List<Visitor> getAllVisitors() {
		return visitorList;
	}

	public List<Visitor> addVisitor(Visitor visitor) {
		if (!visitorExists(visitor.getEmail())) {
			visitor.setId(newId);
			newId++;
			visitorList.add(visitor);
		}
		return visitorList;
	}

	// Adds many new visitors at once
	public List<Visitor> addManyVisitors(List<Visitor> visitorsToAdd) {
		visitorsToAdd.stream().forEach(event -> addVisitor(event));
		return visitorList;
	}

	// The email is like a key for the entity Visitor. The method checks if the
	// Visitor exists in the list by the email

	private boolean visitorExists(String email) {
		return visitorList.stream().anyMatch(v -> v.getEmail().equals(email));
	}

	// Deletes a visitor by their id
	public List<Visitor> deleteVisitor(Integer visitorId) {
		Visitor visitorToDelete = findVisitorById(visitorId);
		if (visitorToDelete != null) {
			visitorList.remove(visitorToDelete);
			reservationService.deleteAllReservationsByVisitor(visitorId);
		}
		return visitorList;
	}

	// Searches the list visitors for a Visitor by id.
	public Visitor findVisitorById(Integer userId) {
		Visitor visitor = visitorList.stream().filter(v -> v.getId().equals(userId)).findFirst().orElse(null);

		if (visitor != null) {
			System.out.println(visitor.getName() + " " + visitor.getSurname());
			return visitor;
		} else {
			System.out.println("Invalid user name provided. Please check again!");
			return null;
		}
	}

	// Returns the visitors of a specific event
	public List<Visitor> getVisitorsForEvent(Integer eventId) {
		// get the reservations for this event
		List<Reservation> reservations = reservationService.getAllReservations().stream()
				.filter(reservation -> reservation.getEventId().equals(eventId)).toList();

		List<Visitor> myVisitors = new ArrayList<Visitor>();
		if (reservations != null) {
			for (Reservation r : reservations) {
				Visitor v = this.findVisitorById(r.getVisitorId());
				myVisitors.add(v);
			}
			return myVisitors;
		} else {
			return null;
		}

	}

	// Updates the fields of a Visitor and returns the list with all the visitors.
	public List<Visitor> updateVisitor(Integer visitorId, String name, String surname, String email) {
		if (!visitorId.equals(null)) {
			Visitor visitorToUpdate = this.findVisitorById(visitorId);
			if(visitorToUpdate != null) {
				if (name != null && !name.isEmpty())
					visitorToUpdate.setName(name);
				if (surname != null && !surname.isEmpty())
					visitorToUpdate.setSurname(surname);
				if (email != null && !email.isEmpty()) {
					if (!this.visitorExists(email))
						visitorToUpdate.setEmail(email);
				}				
			}
		}
		return this.getAllVisitors();
	}

}
