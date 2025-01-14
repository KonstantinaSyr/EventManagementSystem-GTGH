package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;

@Service
public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();
	private int newId=1;
	/**
	 * Adds a reservation to the List with the reservations.
	 * 
	 * @param reservation
	 */

	public List<Reservation> createReservation(Integer userId, Integer eventId) {
		Reservation reservation = new Reservation(userId, eventId);
		reservation.setId(newId);
		newId++;
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
		if(userId != null) {
			List<Reservation> r = reservationsList.stream().filter(reservation -> reservation.getVisitorId().equals(userId))
					.toList();

			return r;
		}
		else
			return null;
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

	// The function deletes all the reservations made by a visitor
	public List<Reservation> deleteAllReservationsByVisitor(Integer userId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(userId));
		return reservationsList;
	}
	
	// The function deletes all reservations linked to an event
	public List<Reservation> deleteAllReservationsByEvent(Integer eventId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(eventId));
		return reservationsList;
	}
	

}
