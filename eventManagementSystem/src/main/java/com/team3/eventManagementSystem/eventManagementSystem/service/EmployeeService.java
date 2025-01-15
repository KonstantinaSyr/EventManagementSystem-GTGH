package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Employee;
import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Organizer;

@Service
public class EmployeeService {

	private List<Employee> employeeList = new ArrayList<Employee>();
	private int newId = 1;

	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	public List<Employee> addEmployee(Employee employee) {
		if (!employeeExists(employee.getEmail())) {
			employee.setId(newId);
			newId++;
			employeeList.add(employee);
		}
		return employeeList;
	}
	
	// Takes the id of an employee and deletes him
		public List<Employee> deleteEmployeeById(Integer orgId) {
			Employee employee = findEmployeeById(orgId);
			if (employee != null) {
				employeeList.remove(employee);
			}
			return this.getAllEmployees();
		}

	private boolean employeeExists(String email) {
		return employeeList.stream().anyMatch(employee -> employee.getEmail().equals(email));
	}

	public List<Employee> addManyEmployees(List<Employee> employeesToAdd) {
		employeesToAdd.stream().forEach(employee -> addEmployee(employee));
		return employeeList;
	}

	// Searches the list employees for an Employee by id.
	public Employee findEmployeeById(Integer id) {
		Employee e = employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);

		if (e != null) {
			return e;
		} else {
			System.out.println("Invalid title provided. Please check again!");
			return null;
		}
	}

	// Updates the fields of an Employee and returns the list with all the
	// employees.
	public List<Employee> updateEmployee(Integer employeeId, String name, String surname, String email) {
		if (!employeeId.equals(null)) {
			Employee employeeToUpdate = this.findEmployeeById(employeeId);
			if(employeeToUpdate != null) {
				if (name != null)
					employeeToUpdate.setName(name);
				if (surname != null)
					employeeToUpdate.setSurname(surname);
				if (email != null) {
					if (!this.employeeExists(email))
						employeeToUpdate.setEmail(email);
				}		
			}

		}
		return this.getAllEmployees();
	}

	// Add the Event the Employee deleted to it's deletedEvent list
	public void addToDeletedList(Event deletedEvent, Integer employeeId) {
		findEmployeeById(employeeId).addToDeletedEvents(deletedEvent);
	}

}
