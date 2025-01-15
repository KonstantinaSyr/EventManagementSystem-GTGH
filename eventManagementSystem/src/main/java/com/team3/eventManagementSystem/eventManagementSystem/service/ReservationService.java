package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;

@Service
public class ReservationService {

	private List<Reservation> reservationsList = new ArrayList<>();
	// Counter for assigning an id to a new Reservation
	private int newId = 1;

	public Reservation createReservation(Integer userId, Integer eventId) {
		Reservation reservation = new Reservation(userId, eventId);
		reservation.setId(newId);
		newId++;
		reservationsList.add(reservation);

		return reservation;
	}

	// Searches the list reservation for all the reservations made by a Visitor.
	public List<Reservation> findReservationByVisitor(Integer userId) {
		if (userId != null) {
			List<Reservation> r = reservationsList.stream()
					.filter(reservation -> reservation.getVisitorId().equals(userId)).toList();

			return r;
		} else
			return null;
	}

	// Deletes a visitor's reservation
	public List<Reservation> deleteReservation(Integer userId, Integer eventId) {
		if (userId != null && eventId != null) {
			reservationsList.removeIf(r -> r.getVisitorId().equals(userId) && r.getEventId().equals(eventId));
		}
		return reservationsList;
	}

	// Returns all reservations for all events made (not the deleted reservations)
	public List<Reservation> getAllReservations() {
		return reservationsList;
	}

	// The function deletes all the reservations made by a visitor
	public List<Reservation> deleteAllReservationsByVisitor(Integer userId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(userId));
		return reservationsList;
	}

	// The function deletes all reservations linked to an event
	public List<Reservation> deleteAllReservationsByEvent(Integer eventId) {
		reservationsList.removeIf(r -> r.getVisitorId().equals(eventId));
		return reservationsList;
	}

	// Generate QR Code for a reservation and return it as a BufferedImage
	public BufferedImage generateQRCodeForReservation(Reservation reservation) throws Exception {
		String data = "Reservation ID: " + reservation.getId() + ", Visitor ID: " + reservation.getVisitorId()
				+ ", Event ID: " + reservation.getEventId();

		Map<EncodeHintType, Object> hintMap = new HashMap<>();
		hintMap.put(EncodeHintType.MARGIN, 1);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200, hintMap);

		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

		return qrImage;
	}

}
