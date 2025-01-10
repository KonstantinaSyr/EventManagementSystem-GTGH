package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;

public class ReservationService {
	
	private static List<Reservation> reservations = new ArrayList<>();
    
	/**
	 * Adds a reservation to the List with the reservations.
	 * @param reservation
	 */
    public static void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    
  //Deletes a reservation from the list of reservations
    public static void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }
    
    /*The function returns all the reservations for a certain event
    given by title.
     */
    public static List<Reservation> getReservationsForEvent(String title) {
        return reservations.stream()
                .filter(reservation -> reservation.getEvent().getTitle().equals(title))
                .collect(Collectors.toList());
    }

    //GETTERS AND SETTERS
    public static List<Reservation> getReservations(){
        return reservations;
    }

}
