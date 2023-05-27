package com.project.account.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.account.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	public void findAccountByCustomerId(int customerId);
}
