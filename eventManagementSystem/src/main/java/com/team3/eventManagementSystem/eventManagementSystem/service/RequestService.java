package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;

@Service
public class RequestService {

	private List<ApprovalRequest> requestList = new ArrayList<>();
	@Autowired
	EventService eventService;

	/**
	 * Adds a request to the List with the requests.
	 * 
	 * @param request
	 */
	public List<ApprovalRequest> createRequest(ApprovalRequest request) {
		int newId = 1;
		if (requestList.size() > 0) {
			newId = requestList.get(requestList.size() - 1).getId() + 1;
		}
		request.setId(newId);
		requestList.add(request);
		return requestList;
	}

	// Takes the id of a request and deletes the request
	public List<ApprovalRequest> deleteRequest(Integer requestId) {
		ApprovalRequest requestToDelete = requestList.stream().filter(r -> r.getId().equals(requestId)).findFirst()
				.orElse(null);
		requestList.remove(requestToDelete);
		return requestList;
	}

	/**
	 * Returns the list of the Requests
	 * 
	 * @return requests
	 */
	public List<ApprovalRequest> getAllRequests() {
		return requestList;
	}

	/**
	 * Returns true if the request exists at the list
	 * 
	 * @param request
	 * @return
	 */
	private boolean approvalRequestExists(ApprovalRequest request) {
		return requestList.contains(request);
	}

	/**
	 * Shows the request with status " pending", "accepted" "rejected" Accorging to
	 * myStatus
	 * 
	 * @param myStatus
	 * @return
	 */
	public List<ApprovalRequest> showRequests(String myStatus) {
		List<ApprovalRequest> filteredRequests = requestList.stream()
				.filter(request -> request.getStatus().equals(myStatus)).collect(Collectors.toList());

		// Print each filtered request
		filteredRequests.forEach(request -> System.out.println(request));

		// Count the number of filtered requests
		long size = filteredRequests.size();
		System.out.println("There are " + size + " requests with status " + myStatus);

		// Return the list of filtered requests
		return filteredRequests;
	}

	// Takes the id of an ApprovalRequest and returns the ApprovalRequest
	public ApprovalRequest getRequestById(Integer id) {
		ApprovalRequest request = requestList.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
		return request;
	}

	// Takes the id of a request, accepts it and
	// creates an event if the Request type is Create
	// deletes it if it is Delete
	public List<ApprovalRequest> approveRequest(Integer requestId) {
		ApprovalRequest request = this.getRequestById(requestId);
		if (request != null) {
			Date today = new Date();
			request.setClosedAt(today);
			if (this.approvalRequestExists(request)) {
				if (request.getType().equals("create")) {
					eventService.addEvent(request.getEvent()); // adds the event at the EventList
				} else {// delete event
					eventService.deleteEvent(request.getEvent().getId());
				}
				request.setStatus("accepted");

			} else {
				System.out.println(" Request not found");
			}
		}
		return this.getAllRequests();
	}

	public List<ApprovalRequest> rejectRequest(Integer requestId) {
		ApprovalRequest request = this.getRequestById(requestId);
		if (request != null) {
			Date today = new Date();
			request.setClosedAt(today);
			if (this.approvalRequestExists(request)) { // the request is at the list
				request.setStatus("rejected");
				// RequestService.deleteRequest(request); //Deletes the request if it is
				// rejected
				// Show rejection message
				System.out.println(" The request for the event " + request.getEvent().getTitle() + " was rejected");
			} else {
				System.out.println(" The request doesn't exist");
			}
		}
		return this.getAllRequests();
	}

}
