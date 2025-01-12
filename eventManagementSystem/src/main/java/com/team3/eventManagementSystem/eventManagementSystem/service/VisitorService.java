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

	/**
	 * Returns the list with all the visitors
	 * 
	 * @return the list with all the visitors
	 */
	public List<Visitor> getAllVisitors() {
		return visitorList;
	}

	/**
	 * Adds a Visitor to the List with the visitors.
	 * 
	 * @param visitor
	 */
	public List<Visitor> addVisitor(Visitor visitor) {
		if (!visitorExists(visitor.getEmail())) {
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
	public List<Visitor> addManyVisitors(List<Visitor> visitorsToAdd) {
		visitorsToAdd.stream().forEach(event -> addVisitor(event));
		return visitorList;
	}
	
	// Checks if a visitor exists in the visitor list
		private boolean visitorExists(Visitor visitor) {
			return visitorList.stream().anyMatch(v -> v.getEmail().equals(visitor.getEmail()));
		}

	// Deletes a visitor by their id

	/**
	 * The email is like a key for the entity Visitor. The method checks if the
	 * Visitor exists in the list by the email
	 * 
	 * @param email the email of the visitor to be checked
	 * @return true if the visitor exists, false otherwise
	 */
	private boolean visitorExists(String email) {
		return visitorList.stream().anyMatch(v -> v.getEmail().equals(email));
	}

	/**
	 * Deletes a visitor by id
	 * 
	 * @param visitorId the id of the visitor to be deleted
	 * @return the list with all the visitors
	 */

	public List<Visitor> deleteVisitor(Integer visitorId) {
		Visitor visitorToDelete = findVisitorById(visitorId);
		if (visitorToDelete != null) {
			visitorList.remove(visitorToDelete);
			reservationService.deleteAllReservationsByVisitor(visitorId);
			// System.out.println("Visitor removed: " + visitorToDelete);
		}
		return visitorList;
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

	/**
	 * Updates the fields of a Visitor and returns the list with all the visitors.
	 * 
	 * @param visitorId the id of the visitor to be updated
	 * @param name      the new name(it can be null)
	 * @param surname   the new surname(it can be null)
	 * @param email     the new email(it can be null)
	 * @return the list with all the visitors
	 */
	public List<Visitor> updateVisitor(Integer visitorId, String name, String surname, String email) {
		if (!visitorId.equals(null)) {
			Visitor visitorToUpdate = this.findVisitorById(visitorId);

			if (name != null)
				visitorToUpdate.setName(name);
			if (surname != null)
				visitorToUpdate.setSurname(surname);
			if (email != null) {
				if (!this.visitorExists(email))
					visitorToUpdate.setEmail(email);
			}
		}
		return this.getAllVisitors();
	}

}
