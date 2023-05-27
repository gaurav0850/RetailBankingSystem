package com.project.account.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.account.entities.Account;
import com.project.account.entities.Customer;
import com.project.account.entities.Employee;
import com.project.account.entities.repositories.AccountRepository;
import com.project.account.service.AccountService;
import com.project.account.service.CustomerService;
import com.project.account.service.EmployeeService;

@Controller
public class LoginController {

	@Autowired
	CustomerService cService;

	@Autowired
	EmployeeService service;
	
	@Autowired
	AccountService aService;
	
	@Autowired
	CustomerService cservice;
	

	@RequestMapping("/e")
	public String employeeLogin() {
		return "login";
	}
	
	@RequestMapping("/c")
	public String customerLogin() {
		return "customerLogin";
	}
	
	

	@PostMapping("/customerHome")
	public String customerHome(@RequestParam("customerId") int customerId, @RequestParam("password") String password,
			ModelMap map) {
		
		System.out.println("get customer method called");
		
		String redirectTo;
		Optional<Customer> optional = cService.findCustomerByCustomerId(customerId);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			
			if (customer.getPassword().equals(password)) {
				map.addAttribute("customer", customer);
				List<Account> accounts = aService.findAccountsByCustomerId(customerId);
				map.addAttribute("accounts", accounts);
				redirectTo = "customerHome";
			} else {
				map.addAttribute("res", "Password wrong");
				map.addAttribute("username-style", "2px solid red");
				map.addAttribute("username", customerId);
				redirectTo = "customerLogin";
			}
		} else {
			map.addAttribute("res", "Username doesnt exist");
			map.addAttribute("password-style", "2px solid red");
			map.addAttribute("username", customerId);
			redirectTo = "customerLogin";

		}
		return redirectTo;
	}

	@PostMapping("/employeeHome")
	public String employeeHome(@ModelAttribute("employee") Employee employee, ModelMap map) {

		System.out.println("get employee method called");
		System.out.println(employee.toString());
		String redirectTo;
		Optional<Employee> optional = service.getEmployee(employee.getUsername());
		if (optional.isPresent()) {
			Employee employee1 = optional.get();
			System.out.println(employee1);
			if (employee1.getPassword().equals(employee.getPassword())) {
				map.addAttribute("employee", employee1);
				map.addAttribute("customers", cservice.getCustomers());
				redirectTo = "employeeHome";
			} else {
				map.addAttribute("res", "Password wrong");
				map.addAttribute("username-style", "2px solid red");
				map.addAttribute("username", employee.getUsername());
				redirectTo = "login";
			}
		} else {
			map.addAttribute("res", "Username doesnt exist");
			map.addAttribute("password-style", "2px solid red");
			map.addAttribute("username", employee.getUsername());
			redirectTo = "login";

		}
		return redirectTo;
	}

}
