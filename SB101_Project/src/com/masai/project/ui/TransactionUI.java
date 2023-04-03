package com.masai.project.ui;

import java.util.Scanner;

import com.masai.project.dao.TransactionDAO;
import com.masai.project.dao.TransactionDAOimpl;

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


	
	
	
	
	
	
	
	
	
}
