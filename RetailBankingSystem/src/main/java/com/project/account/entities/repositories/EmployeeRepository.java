package com.project.account.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.account.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
