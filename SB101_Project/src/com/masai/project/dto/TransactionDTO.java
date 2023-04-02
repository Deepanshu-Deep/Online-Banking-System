package com.masai.project.dto;

import java.time.LocalDate;

public interface TransactionDTO {

	
	public int getTransactionId();
	public void setTransactionId(int transactionId);


	public int getAccountNumber();
	public void setAccountNumber(int accountNumber);


	public LocalDate getTransactionDate();
	public void setTransactionDate(LocalDate transactionDate);
	
	public String getTransactionType();
	public void setTransactionType(String transactionType);


	public double getAmount();
	public void setAmount(double amount);
	
	
	
	
	
	
	
	
	
	
}
