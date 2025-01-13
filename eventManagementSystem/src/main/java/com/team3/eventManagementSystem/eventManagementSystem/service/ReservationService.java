package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;

@Service
public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();

	/**
	 * Adds a reservation to the List with the reservations.
	 * 
	 * @param reservation
	 */

	public List<Reservation> createReservation(Integer userId, Integer eventId) {
		int newId = 1;
		if(reservationsList.size() > 0) {
			newId = reservationsList.get(reservationsList.size() - 1).getId() + 1; 
		}
		Reservation reservation = new Reservation(userId, eventId);
		reservation.setId(newId);
		reservationsList.add(reservation);
		return reservationsList;
	}

	/**
	 * Searches the list reservation for all the reservations made by a Visitor.
	 * 
	 * @param userId
	 * @return
	 */

	public List<Reservation> findReservationByVisitor(Integer userId) {
		List<Reservation> r = reservationsList.stream().filter(reservation -> reservation.getVisitorId().equals(userId))
				.toList();

		return r;
	}

	// Deletes a visitor's reservation
	public List<Reservation> deleteReservation(Integer userId, Integer eventId) {
		if (userId != null && eventId != null) {
			reservationsList.removeIf(r -> r.getVisitorId().equals(userId) && r.getEventId().equals(eventId));

		}
		return reservationsList;
	}

	// Returns all reservations for all events made (not the deleted reservations)
	public List<Reservation> getAllReservations() {
		return reservationsList;
	}

	/*
	 * The function checks whether a visitor has already reserved a spot for an
	 * event or not. If the visitor has not reserved their spot yet, the function
	 * returns true, else it returns false.
	 */
	private boolean checkIfNotExists(Integer visitorId, Integer eventId) {
		return getAllReservations().stream().noneMatch(reservation -> reservation.getEventId().equals(eventId)
				&& reservation.getVisitorId().equals(visitorId));
	}

	public List<Reservation> deleteAllReservationsByVisitor(Integer userId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(userId));
		return reservationsList;
	}

	public List<Reservation> deleteAllReservationsByEvent(Integer eventId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(eventId));
		return reservationsList;
	}
	

}
