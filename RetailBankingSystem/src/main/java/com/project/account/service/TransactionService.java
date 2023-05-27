package com.project.account.service;

import java.util.List;

import com.project.account.entities.Statement;

public interface TransactionService {
	Statement createStatement(Statement statement);

	List<Statement> getStatements(int accountId);
}
