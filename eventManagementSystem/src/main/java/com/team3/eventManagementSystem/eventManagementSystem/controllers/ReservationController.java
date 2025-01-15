package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public List<Reservation> deleteAReservation(@RequestParam Integer userId, @RequestParam Integer eventId) {
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
	
	@GetMapping("/generateQRCode")
	public ResponseEntity<byte[]> generateQRCodeForReservation(@RequestParam Integer userId, @RequestParam Integer eventId) {
	    try {
	        Reservation reservation = reservationService.createReservation(userId, eventId);
	        BufferedImage qrCodeImage = reservationService.generateQRCodeForReservation(reservation);

	        // Convert BufferedImage to byte array for response
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(qrCodeImage, "PNG", baos);
	        byte[] imageBytes = baos.toByteArray();

	        // Return the image in the response
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

}
