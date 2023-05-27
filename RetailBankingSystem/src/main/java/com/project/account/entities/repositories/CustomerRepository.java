package com.project.account.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.account.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
}
