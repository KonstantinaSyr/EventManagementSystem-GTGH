package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.service.RequestService;

@RestController
@RequestMapping("requests")
public class RequestController {

	@Autowired
	RequestService requestService;

	@GetMapping("/allRequests")
	public List<ApprovalRequest> getAllRequests() {
		return requestService.getAllRequests();
	}

	@PostMapping("/addRequest")
	public List<ApprovalRequest> addRequest(@RequestBody ApprovalRequest request) {
		return requestService.createRequest(request);
	}

	@DeleteMapping("/deleteRequest")
	public List<ApprovalRequest> deleteRequest(@RequestParam Integer id) {
		return requestService.deleteRequest(id);
	}

	@GetMapping("/requestsByStatus")
	public List<ApprovalRequest> requestsByStatus(@RequestParam String status) {
		return requestService.showRequests(status);
	}

	@PutMapping("/approveRequest")
	public List<ApprovalRequest> approveRequest(@RequestParam Integer requestId) {
		return requestService.approveRequest(requestId);
	}

	@PutMapping("/rejectRequest")
	public List<ApprovalRequest> rejectRequest(@RequestParam Integer requestId) {
		return requestService.rejectRequest(requestId);
	}
	
	@GetMapping("/requestsByEmployee")
	public List<ApprovalRequest> requestsByEmployee(@RequestParam int id) {
		return requestService.getRequestsOfEmployee(id);
	}
}
