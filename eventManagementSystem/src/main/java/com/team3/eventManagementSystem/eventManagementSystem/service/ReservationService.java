package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

@Service
public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();

	/**
	 * Adds a reservation to the List with the reservations.
	 * 
	 * @param reservation
	 */

	public List<Reservation> createReservation(Integer userId, Integer eventId) {
		Reservation r = new Reservation(userId, eventId);
		reservationsList.add(r);
		return this.getAllReservations();
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
		return getAllReservations();
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
	public boolean checkIfNotExists(Integer visitorId, Integer eventId) {
		return getAllReservations().stream().noneMatch(reservation -> reservation.getEventId().equals(eventId)
				&& reservation.getVisitorId().equals(visitorId));
	}

	public void deleteAllReservationsByVisitor(Integer userId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(userId));
	}

	public void deleteAllReservationsByEvent(Integer eventId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(eventId));
	}

}
