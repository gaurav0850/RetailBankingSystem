package com.project.account.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.account.entities.Customer;
import com.project.account.entities.repositories.CustomerRepository;
import com.project.account.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	CustomerService service;
	


	
	@RequestMapping("/showCreateCustomerPage")
	public String showCreateCustomerPage() {
		return "createCustomerPage";
	}
	
	
	@PostMapping("/createCustomer")
	public String createCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("document") MultipartFile multiPartFile, ModelMap map) throws IOException {
		customer.setProfileImageData(multiPartFile.getBytes());
		Customer save = repository.save(customer);
		map.addAttribute("result", "customer profile created with id: "+save.getCustomerId());
		return "createCustomerPage";
	}
	

	
	@RequestMapping("/showCustomerDetailsPage")
	public String showGetUserDetails() {
		return "customerDetailsInput";
	}
	
	
	@GetMapping("/getCustomerDetails")
	public String getCustomerDetails(@RequestParam("customerId") int customerId, ModelMap map) {
		System.out.println("get customer method called");
		
		String redirectTo;
		Optional<Customer> optional = repository.findById(customerId);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			map.addAttribute("customer", customer);
			redirectTo="customerDetails";
		} else {
			map.addAttribute("errMessage","Customer doesn't exist with id: "+customerId);
			redirectTo="customerDetailsInput";
		}
		
		return redirectTo;
	}
	
	
	@GetMapping("/customers") 
	public String getCustomerProfiles(ModelMap map) {
		map.addAttribute("customers", service.getCustomers());
		return "employeeHome";
	}
}
