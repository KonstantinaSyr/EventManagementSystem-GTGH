package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;


public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();
	
	@Autowired  
	VisitorService visitorService;
	@Autowired  
	EventService eventService;

	

	// Checks if a reservation exists in the list. If the reservation exists it returns true
	// else it returns false 
	private boolean reservationExists(Integer visitorId, Integer eventId) {
		return reservationsList.stream().
				anyMatch(reservation ->reservation.getVisitorId().equals(visitorId) 
						&& reservation.getEventId().equals(eventId));
	}
	

	// Creates a new reservation for an event
	public void createReservation(Integer userId, Integer eventId) {
		if(eventService.eventIsFull(eventId) == false) {
			if (userId != null && eventId != null) {
					System.out.println("Reservation made");
					Reservation r = new Reservation(userId, eventId);
					reservationsList.add(r);
			} else
				System.out.println("Incorrect credentials, please check again");
		}
		else
			System.out.println("Event is full");
	}
	
	public void createManyReservations(List<Integer> userIds , List<Integer> eventIds) {
		if(userIds.size() == eventIds.size()) {
			for(int i=0 ; i<userIds.size() ; i++) {
				createReservation(userIds.get(i) , eventIds.get(i));
			}
		}
		else
			System.out.println("Invalid credentials");
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
	public void deleteReservation(Integer userId, Integer eventId) {
		if (userId != null && eventId != null) {
			if (!reservationExists(userId, eventId)) {
				System.out.println("You have not reserved a spot for the event " + eventService.findEventById(eventId));
			} else {
				reservationsList.removeIf(r ->r.getVisitorId().equals(userId) && r.getEventId().equals(eventId) );
				System.out.println("Your reservation has been deleted");
			}
		} else
			System.out.println("The credentials given are wrong!! Check again!!!");
	}
	
	public void deleteReservationsByUser(Integer userId) {
		reservationsList.removeIf(r ->r.getVisitorId().equals(userId));
	}
	
	public void deleteReservationsByEvent(Integer eventId) {
		reservationsList.removeIf(r ->r.getVisitorId().equals(eventId));
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
				.filter(reservation->reservation.getEventId().equals(eventId)).toList();
		
		List<Visitor> myVisitors = new ArrayList<Visitor>();
		if(reservations!=null) {
			for(Reservation r : reservations) {
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
