package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

@Service
public class ReservationService {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	VisitorService visitorService;

	private List<Reservation> reservations = new ArrayList<>();

	/**
	 * Adds a reservation to the List with the reservations.
	 * 
	 * @param reservation
	 */
	public void createReservation(Reservation reservation) {
		reservations.add(reservation);
	}

	// Deletes a reservation from the list of reservations
	public void deleteReservation(Reservation reservation) {
		reservations.remove(reservation);
	}

	/*
	 * The function returns all the reservations for a certain event given by title.
	 */
	public List<Reservation> getReservationsForEvent(String title) {
		return reservations.stream().filter(reservation -> reservation.getEvent().getTitle().equals(title))
				.collect(Collectors.toList());
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	/*
	 * The function checks whether there is room in the event for more visitors or
	 * not. If there is room for more visitors it returns true, if the event is full
	 * it returns false
	 */
	private boolean checkCapacity(Event event) {
		return event.getMaxCapacity() > getReservationsForEvent(event.getTitle()).size();
	}

	/*
	 * The function checks whether a visitor has already reserved a spot for an
	 * event or not. If the visitor has not reserved their spot yet, the function
	 * returns true, else it returns false.
	 */
	public boolean checkIfNotExists(int visitorId, int eventId) {
		return getReservations().stream().noneMatch(
				reservation -> reservation.getEvent().getId() == eventId && reservation.getVisitor().getId() == visitorId);
	}

	/*
	 * The function returns the appropriate message when a visitor is trying to book
	 * a spot for a certain event
	 */
	public void approveReservation(int visitorId, int eventId) {
		if (checkCapacity(eventService.findEventById(eventId)) && checkIfNotExists(visitorId, eventId)) {
			createReservation(new Reservation(visitorService.findEventById(visitorId), eventService.findEventById(eventId)));
			System.out.println(
					visitorService.findEventById(visitorId).getName() + " your spot for the event: " + eventService.findEventById(eventId).getTitle() + " has been secured!");
		}

		else if (!checkIfNotExists(visitorId, eventId))
			System.out.println(
					visitorService.findEventById(visitorId).getName() + " you have already secured your spot for the event: " + eventService.findEventById(eventId).getTitle());

		else
			System.out.println("Event: " + eventService.findEventById(eventId).getTitle() + " is full!!!");
	}

	/*
	 * The function tests whether there is an existing reservation with the given
	 * visitor and event. If there is no existing reservation with the given
	 * credentials, it prints an appropriate message, else it deletes the
	 * reservation and prints the appropriate message.
	 */
	public void removeReservation(int visitorId, int eventId) {
		Optional<Reservation> result = getReservations().stream()
				.filter(r -> r.getEvent().getId() == eventId && r.getVisitor().getId() == visitorId).findFirst();

		if (result.isPresent()) {
			Reservation reservation = result.get(); // Safe because we checked
			deleteReservation(reservation);
			System.out.println("Your reservation has been deleted!!!");
		} else {
			System.out.println("Oooops...No matching reservation found!!!");
		}

	}

}
