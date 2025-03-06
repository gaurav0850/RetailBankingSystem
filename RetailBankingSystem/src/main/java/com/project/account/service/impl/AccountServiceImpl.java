package com.project.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.account.entities.Account;
import com.project.account.entities.repositories.AccountRepository;
import com.project.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<Account> findAccountsByCustomerId(int customerId) {
		// List<Account> allAccounts = accountRepository.findAll();
		
		// List<Account> accounts=new ArrayList<>();
		// for (Account account : allAccounts) {
		// 	if(account.getCustomerId()==customerId) {
		// 		accounts.add(account);
		// 	}
		// }

	
		for(Account account : accountRepository.findAll()) {
			if(account.getCustomerId.equals(customerId)) {

			}
		}
		return accounts;
	}

}
