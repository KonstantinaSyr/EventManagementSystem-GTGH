package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;

@Service
public class RequestService {

	private List<ApprovalRequest> requestList = new ArrayList<>();
	
	/**
	 * Adds a request to the List with the requests.
	 * 
	 * @param request
	 */
	public List<ApprovalRequest> createRequest(ApprovalRequest request) {
		int newId = 1;
		if(requestList.size() > 0) {
			newId = requestList.get(requestList.size() - 1).getId() + 1; 
		}
		request.setId(newId);
		requestList.add(request);
		return requestList;
	}
	

	public void deleteRequest(ApprovalRequest request) {
		requestList.remove(request);
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
	public boolean approvalRequestExists(ApprovalRequest request) {
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

}
