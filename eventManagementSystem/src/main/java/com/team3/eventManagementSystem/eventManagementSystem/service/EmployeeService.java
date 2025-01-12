package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.eventManagementSystem.eventManagementSystem.models.Employee;

@Service
public class EmployeeService {

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

	public List<Employee> addManyEmployees(List<Employee> employeesToAdd) {
		employeesToAdd.stream().forEach(employee -> addEmployee(employee));
		return employeeList;
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

}
