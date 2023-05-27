package com.project.account.service;

import java.util.List;

import com.project.account.entities.Account;

public interface AccountService {

	List<Account> findAccountsByCustomerId(int customerId);
}
