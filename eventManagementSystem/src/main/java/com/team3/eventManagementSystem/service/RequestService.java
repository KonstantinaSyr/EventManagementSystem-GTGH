package com.team3.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.models.Event;

public class RequestService {
	
	 private static List<ApprovalRequest> requests = new ArrayList<>();
	    
	    /**
	     * Adds a request to the List with the requests.
	     * @param request
	     */
	    public static void createRequest(ApprovalRequest request) {
	        requests.add(request);
	    }
	    
	    public static void deleteRequest(ApprovalRequest request) {
	        requests.remove(request);
	    }

	    /**
	     * Returns the list of the Requests
	     * @return requests
	     */
	    public static List<ApprovalRequest> getAllRequests() {
	        return requests;
	    }
	    
	    
	    
	 /*   public static ApprovalRequest findRequestByType(String type) {
	        return requests.stream()
	                    .filter(request -> request.getType().equals(type))
	                    .findFirst()
	                    .orElse(null);
	    }*/

	    //returns true if the request exists at the list
	    public static boolean ApprovalRequestExists(ApprovalRequest request) {
	    	return requests.contains(request);
	    }
}
