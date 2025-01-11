package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;


public class ReservationService {

	private VisitorService visitorServ = new VisitorService();
	private EventService eventServ = new EventService();
	private List<Reservation> reservationsList = new ArrayList<>();

	public ReservationService( VisitorService visitorServ, EventService eventServ) {
		this.eventServ = eventServ;
		this.visitorServ = visitorServ;
	}

	// Checks if a reservation exists in the list
	private boolean reservationExists(int visitorId, int eventId) {
		return reservationsList.stream().anyMatch(
				reservation -> reservation.getVisitorId()==visitorId && reservation.getEventId()==eventId);
	}
	

	// Creates a new reservation for an event
	public void createReservation(int userId, int eventId) {
		if(eventServ.eventIsFull(eventId, this, visitorServ) == false) {
			if (userId != 0 && eventId != 0) {
				if (!reservationExists(userId, eventId)) {
					System.out.println("Reservation made");
					Reservation r = new Reservation(userId, eventId);
					reservationsList.add(r);
				} else
					System.out.println("Reservation already exists");
			} else
				System.out.println("Incorrect credentials, please check again");
		}
		else
			System.out.println("Event is full");
	}

	// Prints some details about a visitor's already made reservation
	public void viewReservation(int userId, int eventId) {
		if (userId != 0 && eventId != 0)  {
			if (!reservationExists(userId, eventId)) {
				System.out.println("You have not reserved a spot for the event " + eventServ.findEventById(eventId).getTitle());
			} else {
				System.out.println("Your reservation for the event " + eventServ.findEventById(eventId).getTitle() + " has been secured already.");
				System.out.println("Here are some details for your event: ");
				System.out.println(eventServ.findEventById(eventId));
			}
		} else
			System.out.println("Incorrect credentials, please check again");
	}

	// Deletes a visitor's reservation
	public void deleteReservation(int userId, int eventId) {
		if (userId != 0 && eventId != 0) {
			if (!reservationExists(userId, eventId)) {
				System.out.println("You have not reserved a spot for the event " + eventServ.findEventById(eventId));
			} else {
				reservationsList.removeIf(r ->r.getVisitorId() == userId && r.getEventId() == eventId);
				System.out.println("Your reservation has been deleted");
			}
		} else
			System.out.println("The credentials given are wrong!! Check again!!!");
	}


	// Returns all reservations for all events made (not the deleted reservations)
	public List<Reservation> getAllReservations() {
		return reservationsList;
	}

}
