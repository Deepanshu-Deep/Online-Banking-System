package com.masai.project.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.masai.project.dto.TransactionDTO;
import com.masai.project.exception.InsufficientBalanceException;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public interface TransactionDAO {

	public boolean checkIfAccountNumberExists(int accountNumber) throws SomethingWentWrongException;
	public double getAccountBalance(int accNumber) throws SomethingWentWrongException, NoRecordFoundException ;
	public void transferMoney(int fromAccount, int toAccount, double amount) throws SomethingWentWrongException, NoRecordFoundException, InsufficientBalanceException;
	

	
	
	
	
	
	
}
