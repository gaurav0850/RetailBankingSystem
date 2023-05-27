package com.project.account.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statement")
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="statementId")
	private long statementId;
	
	@Column(name="accountId")
	private int accountId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="narration")
	private String narration;
	
	@Column(name="chequeOrReferenceNo")
	private String chequeOrReferenceNo;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="transactionType")
	private String transactionType;
	
	@Column(name="closingBalance")
	private double closingBalance;

	public long getStatementId() {
		return statementId;
	}

	public void setStatementId(long statementId) {
		this.statementId = statementId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getChequeOrReferenceNo() {
		return chequeOrReferenceNo;
	}

	public void setChequeOrReferenceNo(String chequeOrReferenceNo) {
		this.chequeOrReferenceNo = chequeOrReferenceNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	@Override
	public String toString() {
		return "Statement [statementId=" + statementId + ", accountId=" + accountId + ", date=" + date + ", narration="
				+ narration + ", chequeOrReferenceNo=" + chequeOrReferenceNo + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", closingBalance=" + closingBalance + "]";
	}

	


}
