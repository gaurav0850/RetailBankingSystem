package com.project.account.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.account.entities.Account;
import com.project.account.entities.Customer;
import com.project.account.entities.repositories.AccountRepository;
import com.project.account.entities.repositories.CustomerRepository;
import com.project.account.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountService accountService;
	
	
	
	
	@RequestMapping("/createAccount")
	public String showCreateAccountPage() {
		return "createAccount";
	}
	
	
	@PostMapping("/createAccountResponse")
	public String createAccountResponse(@RequestParam("customerId") int customerId,@ModelAttribute("account") Account account, ModelMap map, HttpServletRequest request) {
		
		System.out.println(customerId);
		System.out.println(account.toString());
		account.setCustomerId(customerId);
			try{
				Account savedAccount= accountRepository.save(account);
			}
			catch(Exception e) {
				System.out.println(save.toString());
				map.addAttribute("result", "Account created with account Id: "+save.getAccountId());
				map.addAttribute("customer", save);
				System.out.println(e.printStackTrace());//create custome exception for account creation failed
				//create html page for failed exception also
			}
			finally() {
			//todo	
			}
		return "createAccount";
	}
	
	@RequestMapping("/getCustomerAccountsInput")
	public String getCustomerInput() {
		return "CustomerAccountsInput";
	}
	
	
	@GetMapping("/getCustomerAccounts")
	public String getCustomerAccounts(@RequestParam("customerId") int customerId, ModelMap map) {
		List<Account> accounts = accountService.findAccountsByCustomerId(customerId);
		map.addAttribute("accounts", accounts);
		return "CustomerAccounts";
	}
}
