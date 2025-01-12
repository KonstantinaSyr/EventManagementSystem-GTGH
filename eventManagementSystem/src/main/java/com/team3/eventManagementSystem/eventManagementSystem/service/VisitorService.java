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

	List<Visitor> visitorList = new ArrayList<Visitor>();

	// Returns the list of all the visitors
	public List<Visitor> getAllVisitors() {
		return visitorList;
	}

	/**
	 * Adds a Visitor to the List with the visitors.
	 * 
	 * @param visitor
	 */
	public List<Visitor> addVisitor(Visitor visitor) {
		if (!visitorExists(visitor)) {
			int newId = 1;
			if (visitorList.size() > 0) {
				newId = visitorList.get(visitorList.size() - 1).getId() + 1;
			}
			visitor.setId(newId);
			visitorList.add(visitor);
		}
		return visitorList;
	}

	// Adds many new visitors at once
	public void addManyVisitors(List<Visitor> visitorsToAdd) {
		visitorsToAdd.stream().forEach(event -> addVisitor(event));
	}
	
	// Checks if a visitor exists in the visitor list
		private boolean visitorExists(Visitor visitor) {
			return visitorList.stream().anyMatch(v -> v.getEmail().equals(visitor.getEmail()));
		}

	// Deletes a visitor by their id
	public void deleteVisitor(Integer visitorId) {
		Visitor visitorToDelete = findVisitorById(visitorId);
		if (visitorToDelete != null) {
			visitorList.remove(visitorToDelete);
			reservationService.deleteAllReservationsByVisitor(visitorId);
			System.out.println("Visitor removed: " + visitorToDelete);
		}
	}

	/**
	 * Searches the list visitors for a Visitor by id.
	 * 
	 * @param id
	 * @return
	 */

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
	
	/**
	 * Returns the visitors of a specific event
	 * 
	 * @param eventId
	 * @return
	 */
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


}
