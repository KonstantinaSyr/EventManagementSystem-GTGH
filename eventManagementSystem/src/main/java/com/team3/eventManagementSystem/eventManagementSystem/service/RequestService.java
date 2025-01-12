package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;

@Service
public class RequestService {

	private List<ApprovalRequest> requests = new ArrayList<>();

	/**
	 * Adds a request to the List with the requests.
	 * 
	 * @param request
	 */
	public void createRequest(ApprovalRequest request) {
		requests.add(request);
	}

	public void deleteRequest(ApprovalRequest request) {
		requests.remove(request);
	}

	/**
	 * Returns the list of the Requests
	 * 
	 * @return requests
	 */
	public List<ApprovalRequest> getAllRequests() {
		return requests;
	}

	/**
	 * Returns true if the request exists at the list
	 * 
	 * @param request
	 * @return
	 */
	public boolean approvalRequestExists(ApprovalRequest request) {
		return requests.contains(request);
	}

	/**
	 * Shows the request with status " pending", "accepted" "rejected" Accorging to
	 * myStatus
	 * 
	 * @param myStatus
	 * @return
	 */
	public List<ApprovalRequest> showRequests(String myStatus) {
		List<ApprovalRequest> filteredRequests = requests.stream()
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
