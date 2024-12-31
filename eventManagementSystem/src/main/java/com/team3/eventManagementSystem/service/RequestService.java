package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.models.ApprovalRequest;

public class RequestService {
	
	 private static List<ApprovalRequest> requests = new ArrayList<>();
	    
	    /**
	     * Adds a request to the List with the requests.
	     * @param request
	     */
	    public static void createRequest(ApprovalRequest request) {
	        requests.add(request);
	    }

	    /**
	     * Returns the list of the Requests
	     * @return requests
	     */
	    public static List<ApprovalRequest> getAllRequests() {
	        return requests;
	    }

}
