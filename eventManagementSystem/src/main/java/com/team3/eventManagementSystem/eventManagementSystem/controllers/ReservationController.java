package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@DeleteMapping("/delete")
	public List<Reservation> deleteAReservation(@RequestParam Integer userId,@RequestParam Integer eventId) {
		return reservationService.deleteReservation(userId, eventId);
	}
	
	@GetMapping("/all")
	public List<Reservation> getALLReservations() {
		return reservationService.getAllReservations();
	}
	
	@GetMapping("/reservationsOfVisitor")
	public List<Reservation> findReservationsByVisitor(@RequestParam Integer userId) {
		return reservationService.findReservationByVisitor(userId);
	}

}
