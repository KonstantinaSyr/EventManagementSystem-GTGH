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

	private List<Employee> employeeList = new ArrayList<Employee>();

	/**
	 * Returns the list with all the employees
	 * 
	 * @return the list with all the employees
	 */
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	/**
	 * Adds an Employee to the List with the employees.
	 * 
	 * @param employee the Employee to be added to the list
	 */
	public List<Employee> addEmployee(Employee employee) {
		if (!employeeExists(employee.getEmail())) {
			int newId = 1;
			if (employeeList.size() > 0) {
				newId = employeeList.get(employeeList.size() - 1).getId() + 1;
			}
			employee.setId(newId);
			employeeList.add(employee);
		}
		return employeeList;
	}

	private boolean employeeExists(String email) {
		return employeeList.stream().anyMatch(employee -> employee.getEmail().equals(email));
	}

	public void addManyEmployees(List<Employee> employeesToAdd) {
		employeesToAdd.stream().forEach(employee -> addEmployee(employee));
	}

	/**
	 * Searches the list employees for an Employee by id.
	 * 
	 * @param id the id of the employee to be found
	 * @return the employee(if found, else null)
	 */
	public Employee findEmployeeById(Integer id) {
		Employee e = employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);

		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}

	/**
	 * Updates the fields of an Employee and returns the list with all the
	 * employees.
	 * 
	 * @param employeeId the id of the employee to be updated
	 * @param name       the new name(it can be null)
	 * @param surname    the new surname(it can be null)
	 * @param email      the new email(it can be null)
	 * @return the list with all the employees
	 */
	public List<Employee> updateEmployee(Integer employeeId, String name, String surname, String email) {
		if (!employeeId.equals(null)) {
			Employee employeeToUpdate = this.findEmployeeById(employeeId);

			if (name != null)
				employeeToUpdate.setName(name);
			if (surname != null)
				employeeToUpdate.setSurname(surname);
			if (email != null) {
				if (!this.employeeExists(email))
					employeeToUpdate.setEmail(email);
			}
		}
		return this.getAllEmployees();
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
