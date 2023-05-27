package com.project.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.account.entities.Customer;
import com.project.account.entities.repositories.CustomerRepository;
import com.project.account.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Override
	public List<Customer> getCustomers() {
		return repository.findAll();
	}

	@Override
	public Optional<Customer> findCustomerByCustomerId(int customerId) {
		return repository.findById(customerId);
	}

}
