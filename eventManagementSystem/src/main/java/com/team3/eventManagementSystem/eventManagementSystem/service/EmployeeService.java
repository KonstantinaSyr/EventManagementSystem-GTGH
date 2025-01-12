package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.eventManagementSystem.models.Employee;
import com.team3.eventManagementSystem.eventManagementSystem.models.Event;

@Service
public class EmployeeService {
	
	@Autowired 
	EventService eventService;
	
	@Autowired 
	RequestService RequestService;
	
	List<Employee> employees = new ArrayList<Employee>();
	
	/**
	 * Adds an Employee to the List with the employees.
	 * 
	 * @param employee
	 */
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	/**
	 * Searches the list employees for an Employee by id.
	 * 
	 * @param id
	 * @return
	 */
	public Employee findEmployeeById(Integer id) {
		Employee e = employees.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);

		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}
	
// TRANSFER THE METHODS TO REQUESTSERVICE
// NEW PARAM -> Integer requestId
//	public boolean approveRequest(ApprovalRequest request) {
//		Date today = new Date();
//		request.setClosedAt(today);
//		if (RequestService.ApprovalRequestExists(request)) {
//			if (request.getType().equals("create")) {
//				eventService.addEvent(request.getEvent()); // adds the event at the EventList
//			} else {// delete event
//				eventService.deleteEvent(request.getEvent().getId());
//			}
//			request.setStatus("accepted");
//			return true;
//		} else {
//			System.out.println(" Request not found");
//			return false;
//		}
//
//	}
//
//	public boolean rejectRequest(ApprovalRequest request) {
//		Date today = new Date();
//		request.setClosedAt(today);
//		if (RequestService.ApprovalRequestExists(request)) { // the request is at the list
//			request.setStatus("rejected");
//			// RequestService.deleteRequest(request); //Deletes the request if it is
//			// rejected
//			// Show rejection message
//			System.out.println(" The request for the event " + request.getEvent().getTitle() + " was rejected");
//			return true;
//		} else {
//			System.out.println(" The request doesn't exist");
//			return false;
//		}
//
//	}
//
//	public void showRequests(String status) {
//		RequestService.showRequests(status);
//	}

}
