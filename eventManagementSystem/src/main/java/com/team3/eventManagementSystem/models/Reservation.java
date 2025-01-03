package com.team3.eventManagementSystem.models;

import java.util.*;
import static com.team3.eventManagementSystem.service.ReservationService.getReservations;
import static com.team3.eventManagementSystem.service.ReservationService.getReservationsForEvent;
import static com.team3.eventManagementSystem.service.ReservationService.deleteReservation;
import static com.team3.eventManagementSystem.service.ReservationService.createReservation;

public class Reservation {
	
	private Visitor visitor;
	private Event event;
	
	public Reservation(Visitor visitor, Event event) {
		this.visitor = visitor;
		this.event = event;
	}
	
	/*The function checks whether there is room in the event for more visitors or not.
    If there is room for more visitors it returns true, if the event is full
    it returns false
     */
    private static boolean checkCapacity(Event event){
        return event.getMaxCapacity() > getReservationsForEvent(event.getTitle()).size();
    }

    /*The function checks whether a visitor has already reserved a spot for an event or not.
    If the visitor has not reserved their spot yet, the function returns true,
     else it returns false.
     */
    public static boolean checkIfNotExists(Visitor visitor, Event event) {
        return getReservations().stream()
                .noneMatch(reservation -> reservation.getEvent().equals(event) &&
                        reservation.getVisitor().equals(visitor));
    }


    /* The function returns the appropriate message when a visitor is trying
    to book a spot for a certain event
     */
    public static void approveReservation(Visitor visitor, Event event){
        if (checkCapacity(event) && checkIfNotExists(visitor,event)){
            createReservation(new Reservation(visitor , event));
            System.out.println(visitor.getName() + " your spot for the event: " + event.getTitle() + " has been secured!");
        }

        else if (!checkIfNotExists(visitor, event) )
            System.out.println(visitor.getName() + " you have already secured your spot for the event: " + event.getTitle());

        else
            System.out.println("Event: " + event.getTitle() + " is full!!!");
    }


    /*The function tests whether there is an existing reservation with the given visitor and event.
    If there is no existing reservation with the given credentials, it prints an appropriate message,
    else it deletes the reservation and prints the appropriate message.
     */
    public static void removeReservation(Visitor visitor , Event event){
        Optional<Reservation> result = getReservations().stream()
                .filter(r -> r.getEvent().equals(event) &&
                        r.getVisitor().equals(visitor))
                .findFirst();

        if (result.isPresent()) {
            Reservation reservation = result.get();  // Safe because we checked
            deleteReservation(reservation);
            System.out.println("Your reservation has been deleted!!!");
        } else {
            System.out.println("Oooops...No matching reservation found!!!");
        }

    }
    
    //GETTERS AND SETTERS
    public Event getEvent() {
        return this.event;
    }

    public Visitor getVisitor(){
        return this.visitor;
    }
    

}
