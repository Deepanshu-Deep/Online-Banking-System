package com.masai.project.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.masai.project.dao.AccountDAO;
import com.masai.project.dao.AccountDAOimpl;
import com.masai.project.dao.TransactionDAO;
import com.masai.project.dao.TransactionDAOimpl;
import com.masai.project.dto.TransactionDTO;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class TransactionUI {

	//Transfer money to other account
	public static void transferMoneyUI(Scanner sc) {
		
	    System.out.println("Enter the account from which you want to transfer money:");
	    int fromAccount = sc.nextInt();

	    System.out.println("Enter the account to which you want to transfer money:");
	    int toAccount = sc.nextInt();

	    System.out.println("Enter the amount to be transferred:");
	    double amount = sc.nextDouble();

	    TransactionDAO transactionDAO = new TransactionDAOimpl();

	    try {
	        transactionDAO.transferMoney(fromAccount, toAccount, amount);
	        
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	}

	
	//get transaction details from date
//	public static void viewTransactionHistoryUI(Scanner sc, TransactionDAO transactionDAO) {
//	    System.out.println("\n******Enter the date range to view transaction history*****\n");
//	    
//	    System.out.println("Enter the start date in format (yyyy-mm-dd): ");
//	    String startDateStr = sc.nextLine();
//
//	    // Check if the input string is empty or null
//	    while (startDateStr == null || startDateStr.isEmpty()) {
//	        System.out.println("Invalid input. Please enter a non-empty date string.");
//	        System.out.println("Enter the start date in format (yyyy-mm-dd): ");
//	        startDateStr = sc.nextLine();
//	    }
//
//	    LocalDate startDate = LocalDate.parse(startDateStr);
//
//	    
//	    System.out.println("Enter the end date in format (yyyy-mm-dd): ");
//	    String endDateStr = sc.nextLine();
//
//	    // Check if the input string is empty or null
//	    while (endDateStr == null || endDateStr.isEmpty()) {
//	        System.out.println("Invalid input. Please enter a non-empty date string.");
//	        System.out.println("Enter the end date in format (yyyy-mm-dd): ");
//	        endDateStr = sc.nextLine();
//	    }
//
//	    LocalDate endDate = LocalDate.parse(endDateStr);
//	    
//	    
//	    
//	    try {
//	        List<TransactionDTO> transactions = transactionDAO.getTransactionHistory(0, startDate, endDate);
//	        System.out.println("\nTransaction history for the date range " + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " to " + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + ":");
//	        for (TransactionDTO transaction : transactions) {
//	            System.out.println("Transaction ID: " + transaction.getTransactionId());
//	            System.out.println("Account number: " + transaction.getAccountNumber());
//	            System.out.println("Transaction date: " + transaction.getTransactionDate());
//	            System.out.println("Transaction type: " + transaction.getTransactionType());
//	            System.out.println("Amount: " + transaction.getAmount());
//	            System.out.println("------------------------------");
//	        }
//	    } catch (NoRecordFoundException ex) {
//	        System.out.println(ex.getMessage());
//	    } catch (SomethingWentWrongException ex) {
//	        System.out.println(ex.getMessage());
//	    }
//	}
//
//	
//	
	
	
	
	
	
	
	
	
	
}
