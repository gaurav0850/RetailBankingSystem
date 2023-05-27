package com.project.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.account.entities.Statement;
import com.project.account.entities.repositories.TransactionRepository;
import com.project.account.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository repository;

	@Override
	public Statement createStatement(Statement statement)  {

		return repository.save(statement);
	}
	
	@Override
	public List<Statement> getStatements(int accountId) {
		
		List<Statement> statements=new ArrayList<>();
		
		for (Statement statement : repository.findAll()) {
			if(statement.getAccountId()==accountId) {
				statements.add(statement);
			}
		}
		return statements;
	}

	
}
