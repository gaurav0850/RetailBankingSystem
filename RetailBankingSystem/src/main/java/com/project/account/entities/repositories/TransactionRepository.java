package com.project.account.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.account.entities.Statement;

public interface TransactionRepository extends JpaRepository<Statement, Long>{

}
