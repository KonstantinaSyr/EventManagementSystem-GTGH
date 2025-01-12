package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;


public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();
	
	@Autowired  
	VisitorService visitorService;
	@Autowired  
	EventService eventService;


	// Checks if a reservation exists in the list
	private boolean reservationExists(int visitorId, int eventId) {
		return reservationsList.stream().anyMatch(
				reservation -> reservation.getVisitorId()==visitorId && reservation.getEventId()==eventId);
	}
	

	// Creates a new reservation for an event
	public void createReservation(int userId, int eventId) {
		if(eventService.eventIsFull(eventId) == false) {
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
				System.out.println("You have not reserved a spot for the event " + eventService.findEventById(eventId).getTitle());
			} else {
				System.out.println("Your reservation for the event " + eventService.findEventById(eventId).getTitle() + " has been secured already.");
				System.out.println("Here are some details for your event: ");
				System.out.println(eventService.findEventById(eventId));
			}
		} else
			System.out.println("Incorrect credentials, please check again");
	}

	// Deletes a visitor's reservation
	public void deleteReservation(int userId, int eventId) {
		if (userId != 0 && eventId != 0) {
			if (!reservationExists(userId, eventId)) {
				System.out.println("You have not reserved a spot for the event " + eventService.findEventById(eventId));
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
	
	// Returns all the visitors for a specific event
	public List<Visitor> getVisitorsForEvent(int eventId){
		//get the reservations for this event
		List<Reservation> reservations= 
				reservationsList.stream()
				.filter(reservation->reservation.getEventId()==eventId).toList();
		List<Visitor> myVisitors = null;
		if(reservations!=null) {
		for(Reservation r: reservations) {
			Visitor v=visitorService.findVisitorById(r.getVisitorId());
			myVisitors.add(v);
		}
		return myVisitors;
		}
		else {
			System.out.println("No one has made a reservation for the event");
			return null;
		}
	}

}
