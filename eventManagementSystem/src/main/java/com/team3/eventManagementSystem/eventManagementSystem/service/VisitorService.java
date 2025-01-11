package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

@Service
public class VisitorService {

	@Autowired
	EventService eventService;

	@Autowired
	ReservationService reservationService;
	
	List<Visitor> visitors = new ArrayList<>();
	
	/**
	 * Searches the list visitors for a Visitor by id.
	 * @param id
	 * @return
	 */
    public Visitor findEventById(int id) {
        Visitor v = visitors.stream()
                    .filter(visitor -> visitor.getId() == id)
                    .findFirst()
                    .orElse(null);
        
        if (v != null){
            return v;
        }
        else {
            System.out.println("Invalid id provided. Please check again!");
            return null;
        }
    }

	// Searches for a certain event and prints its information.
	public void searchEvent(int eventId) {
		Event event = eventService.findEventById(eventId);
		if (event != null) {
			System.out.println(event);
		}
	}

	/*
	 * Uses the function service.EventService.viewExistingEvents() that prints all
	 * the existing events
	 */
	public void viewEvents() {
		eventService.viewExistingEvents();
	}

	/*
	 * The function makes a reservation for the visitor for a certain event, given
	 * by its id, by using the function approveReservation
	 */
	public void makeReservation(int visitorId, int eventId) {
		Event event = eventService.findEventById(eventId);
		if (event != null) {
			reservationService.approveReservation(visitorId, eventId);
		}
	}

	/*
	 * If the visitor has reserved a spot for the given event it returns information
	 * about the event, else it returns the appropriate message.
	 */
	public void viewReservation(int visitorId, int eventId) {
		if (reservationService.checkIfNotExists(visitorId, eventId)) {
			System.out.println("You have not reserved a spot for the event " + eventService.findEventById(eventId) + "!!!");
		} else {
			System.out.println("Here are some details for your reservation: ");
			searchEvent(eventId);
		}
	}

	/*
	 * The function cancels the visitor's reservation for a certain event, given by
	 * title
	 */
	public void cancelReservation(int visitorId, int eventId) {
		Event event = eventService.findEventById(eventId);
		if (event != null) {
			reservationService.removeReservation(visitorId, event.getId());
		}
	}
}
