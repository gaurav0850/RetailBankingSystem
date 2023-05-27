package com.project.account.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.account.entities.Account;
import com.project.account.entities.Customer;
import com.project.account.entities.Statement;
import com.project.account.entities.repositories.AccountRepository;
import com.project.account.service.AccountService;
import com.project.account.service.CustomerService;
import com.project.account.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TransactionController {

	@Autowired
	TransactionService service;
	
	@Autowired
	AccountRepository aRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired 
	CustomerService customerService;
	
	@PostMapping("/transaction")
	public String showTransactionPage(@RequestParam("customerId") int customerId, ModelMap map) {
		List<Account> accounts = accountService.findAccountsByCustomerId(customerId);
		map.addAttribute("accounts", accounts);
		map.addAttribute("customer", customerService.findCustomerByCustomerId(customerId).get());
		
		return "transaction";
	}
	
	@PostMapping("/createTransactionResponse")
	public String createTransaction(@ModelAttribute("transaction") Statement transaction, ModelMap map) {
		
		Account account = aRepository.findById(transaction.getAccountId()).get();
		double balance = account.getInitialBalance();
		System.out.println(balance);
		
		balance = (transaction.getTransactionType().equals("deposit")) ? balance + transaction.getAmount() : balance - transaction.getAmount();
		System.out.println(balance);
		
		//minimum balance should be 100
		if(balance<=100) {
			map.addAttribute("result", "Transaction failed. There should be minimum balance 100 in account");
			map.addAttribute("customer", customerService.findCustomerByCustomerId(account.getCustomerId()).get());
			List<Account> accounts = accountService.findAccountsByCustomerId(account.getCustomerId());
			map.addAttribute("accounts", accounts);
			return "transaction";

		}
		
		transaction.setClosingBalance(balance);
		
		service.createStatement(transaction);
		account.setInitialBalance(balance);
		aRepository.save(account);
		
		Statement createStatement = service.createStatement(transaction);
		
		map.addAttribute("result", "Amount transferred successfully. You new balance is "+balance);
		map.addAttribute("customer", customerService.findCustomerByCustomerId(account.getCustomerId()).get());
		List<Account> accounts = accountService.findAccountsByCustomerId(account.getCustomerId());
		map.addAttribute("accounts", accounts);
		return "transaction";
	}
	
	@PostMapping("/statementsPage")
	public String showStatementPage(@RequestParam("customerId") int customerId, ModelMap map) {
		List<Account> accounts = accountService.findAccountsByCustomerId(customerId);
		map.addAttribute("accounts", accounts);
		map.addAttribute("customer", customerService.findCustomerByCustomerId(customerId).get());
		
		return "statementInput";
	}
	
	@PostMapping("/statements")
	public String getStatements(@ModelAttribute("accountId") int accountId,@ModelAttribute("customer") Customer customer, ModelMap map, HttpServletRequest request) {
		   
		System.out.println(accountId);
		Date fromDate = Date.valueOf(request.getParameter("fromDate"));
		Date toDate = Date.valueOf(request.getParameter("toDate"));
		
		List<Statement> allStatements = service.getStatements(accountId);
		List<Statement> statements=new ArrayList<>();
		System.out.println(fromDate);
		System.out.println(toDate);
		
		for (Statement statement : allStatements) {
			System.out.println(statement.getDate());
			if(statement.getDate().equals(fromDate) || statement.getDate().equals(fromDate) || (statement.getDate().before(toDate)  && statement.getDate().after(fromDate))) {
				statements.add(statement);
			}
		}
		List<Account> accounts = accountService.findAccountsByCustomerId(customer.getCustomerId());
		map.addAttribute("accounts", accounts);
		map.addAttribute("statements", statements);
		map.addAttribute("customer", customer);
		
		if(fromDate.after(toDate)) {
			map.addAttribute("result", "Invalid Date Range.");
			return "statementInput";
		}
		if(statements.size()==0) {
			map.addAttribute("result", "No records found for accountId "+accountId);
			return "statementInput";
		}
		return "statements";
	}
	
}
