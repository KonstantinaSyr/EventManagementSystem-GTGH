package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.models.Reservation;

public class ReservationService {
	
	private static List<Reservation> reservations = new ArrayList<>();
    
	/**
	 * Adds a reservation to the List with the reservations.
	 * @param reservation
	 */
    public static void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

}
