package com.project.account.service;

import java.util.List;
import java.util.Optional;

import com.project.account.entities.Customer;


public interface CustomerService {

	List<Customer> getCustomers();
	Optional<Customer> findCustomerByCustomerId(int customerId);
}
