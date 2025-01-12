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
	
	List<Employee> employeeList = new ArrayList<Employee>();
	
	/**
	 * Adds an Employee to the List with the employees.
	 * 
	 * @param employee
	 */
	public List<Employee> addEmployee(Employee employee) {
		if(!employeeExists(employee.getEmail())) {
			int newId = 1;
			if(employeeList.size() > 0) {
				newId = employeeList.get(employeeList.size() - 1).getId() + 1;
			}
			employee.setId(newId);
			employeeList.add(employee);
		}
		return employeeList ;
	}
	
	private boolean employeeExists(String email) {
		return employeeList.stream().anyMatch(employee -> employee.getEmail().equals(email)); 
	}

	public List<Employee> addManyEmployees(List<Employee> employeesToAdd) {
		employeesToAdd.stream().forEach(employee -> addEmployee(employee));
		return employeeList;
	}

	/**
	 * Searches the list employees for an Employee by id.
	 * 
	 * @param id
	 * @return
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
	

}
