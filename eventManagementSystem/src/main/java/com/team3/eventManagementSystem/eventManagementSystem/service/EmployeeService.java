package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Employee;
import com.team3.eventManagementSystem.eventManagementSystem.models.Event;

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

	// Add the Event the Employee deleted to it's deletedEvent list
	public void addToDeletedList(Event deletedEvent, int employeeId) {
		findEmployeeById(employeeId).addToDeletedEvents(deletedEvent);
	}

}
