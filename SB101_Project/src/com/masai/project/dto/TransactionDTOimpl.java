package com.masai.project.dto;

import java.time.LocalDate;

public class TransactionDTOimpl implements TransactionDTO {

	
	int transactionId;
	int accountNumber;
	LocalDate transactionDate;
	String transactionType;
	double amount;
	
	
	public TransactionDTOimpl(int transactionId, int accountNumber, LocalDate transactionDate, String transactionType, double amount) {
		
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	@Override
	public int getTransactionId() {
		return transactionId;
	}

	@Override
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public int getAccountNumber() {
		return accountNumber;
	}

	@Override
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	@Override
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String getTransactionType() {
		return transactionType;
	}

	@Override
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
}
