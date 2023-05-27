package com.project.account.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.account.entities.Employee;
import com.project.account.entities.repositories.EmployeeRepository;
import com.project.account.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Override
	public Employee createEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Optional<Employee> getEmployee(String username) {
		return repository.findById(username);
	}

}
