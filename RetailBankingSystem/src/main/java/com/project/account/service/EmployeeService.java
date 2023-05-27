package com.project.account.service;

import java.util.Optional;

import com.project.account.entities.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);
	Optional<Employee> getEmployee(String username);
}
