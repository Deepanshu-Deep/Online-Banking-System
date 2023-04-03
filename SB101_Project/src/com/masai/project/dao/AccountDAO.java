package com.masai.project.dao;

import java.util.List;

import com.masai.project.dto.AccountDTO;
import com.masai.project.dto.CustomerDTO;
import com.masai.project.exception.InsufficientBalanceException;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public interface AccountDAO {
	
	public boolean checkIfAccountExists(int accountNumber) throws SomethingWentWrongException;
	public void openAccount(AccountDTO aDto)  throws SomethingWentWrongException, NoRecordFoundException; 
	
	public boolean checkIfAccountNumberExists(int accountNumber) throws SomethingWentWrongException;
	public double getAccountBalance(int accNumber) throws SomethingWentWrongException, NoRecordFoundException;
	public double depositMoney(int accNumber, double amount) throws SomethingWentWrongException, NoRecordFoundException; 
	public double withdrawalMoney(int accNumber, double amount) throws SomethingWentWrongException, NoRecordFoundException, InsufficientBalanceException; 

	public boolean closeAccount(int accNumber) throws SomethingWentWrongException, NoRecordFoundException;
	
	public boolean deleteAccount(int accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	
    public List<AccountDTO> getAllAccounts() throws SomethingWentWrongException, NoRecordFoundException;

    public List<AccountDTO> viewAccountDetailsByAccountNumber(int accountNumber) throws SomethingWentWrongException, NoRecordFoundException;

    
}
