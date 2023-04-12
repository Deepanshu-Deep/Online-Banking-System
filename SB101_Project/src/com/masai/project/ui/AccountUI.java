package com.masai.project.ui;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.masai.project.dao.AccountDAO;
import com.masai.project.dao.AccountDAOimpl;
import com.masai.project.dto.AccountDTO;
import com.masai.project.dto.AccountDTOimpl;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class AccountUI {

	
// Open account 	
public static void OpenAccountUI(Scanner sc){
		
		System.out.println("Enter the details below to Open Account");
		
		System.out.println("Enter the Customer Id :");
		int cid = sc.nextInt();
		
		AccountDAO accountDAO = new AccountDAOimpl();
		
		try {
			if(accountDAO.checkIfAccountExists(cid)){
				System.out.println("Customer already has an account.");
				return;
			}
			
			System.out.println("Enter the Account Type (SAVINGS or LOAN) :");
			String accType = sc.next(); 
			
			System.out.println("Enter the initial Amount :");
			double amount = sc.nextDouble();
			
			System.out.println("Enter the Account Status( ACTIVE OR INOPERATIVE ) ");
			String accStatus = sc.next(); 
			
			AccountDTO accountDTO = new AccountDTOimpl(accType, amount, cid, accStatus);
			accountDAO.openAccount(accountDTO);
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


//***************************************************************************************************


//Deposit Money
public static void depositMoneyUI(Scanner sc){
    System.out.println("\n******Enter the account details to deposit money*****\n");
    System.out.println("Enter the Account Number :");
    int accNum = sc.nextInt();
    AccountDAO accountDAO = new AccountDAOimpl();
    try {
        if(!accountDAO.checkIfAccountNumberExists(accNum)){
            System.out.println("Account not Found.");
            return;
        }
        System.out.println("\nEnter the Amount to deposit :");
        double amount = sc.nextDouble();
        double updatedBalance = accountDAO.depositMoney(accNum, amount);
        System.out.println("\nUpdated balance: " + updatedBalance);
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}


//*************************************************************************************************


// Withdraw Money
public static void withdrawalMoneyUI(Scanner sc){
	
    System.out.println("\nEnter the account to withdrawal money");
	
    System.out.println("\nEnter the Account Number :");
    int accNum = sc.nextInt();
	
    AccountDAO accountDAO = new AccountDAOimpl();
    try {
        if(!accountDAO.checkIfAccountNumberExists(accNum)){
            System.out.println("\nAccount not Found.\n");
            return;
        }
		
        System.out.println("\nEnter the Amount to withdrawal :\n");
        double amount = sc.nextDouble();
		
        double updatedBalance = accountDAO.withdrawalMoney(accNum, amount);
        System.out.println("\nUpdated balance: " + updatedBalance);
		
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
}


//*************************************************************************************************


// Delete the user account
public static void deleteAccountUI(Scanner sc) {
    System.out.println("\n******Delete an account******\n");
    
    System.out.println("Enter the account number to delete: ");
    int accNumber = sc.nextInt();
    sc.nextLine();

    AccountDAO accountDAO = new AccountDAOimpl();
    try  {
    	
        accountDAO.deleteAccount(accNumber);
        System.out.println("\nAccount deleted successfully.");
        
    } catch (NoRecordFoundException ex) {
    	
        System.out.println(ex.getMessage());
        
    } catch (SomethingWentWrongException ex) {
    	
        System.out.println(ex.getMessage());
    }
}

//***************************************************************************************************



// Get all account Details
public static void getAllAccountDetails(Scanner sc) {
    AccountDAO accountDAO = new AccountDAOimpl();
    try {
        List<AccountDTO> list = accountDAO.getAllAccounts();
        System.out.println();
        Consumer<AccountDTO> cun = res -> System.out.println("Account Number : "+ res.getAccountNumber() + ", Customer ID :"+ res.getCustomerId() + ", Account Type : "+ res.getAccountType() + ", Account Balance : "+ res.getBalance());
        list.forEach(cun);
        System.out.println();
    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
        System.out.println(ex.getMessage());
    }
}



//****************************************************************************************************

// view Account Details By Account Number
public static void viewAccountDetailsByAccountNumber(Scanner sc) {
	
    System.out.println("Enter the Account Number");
    
    int accNumber = sc.nextInt();
    
    AccountDAO accountDAO = new AccountDAOimpl();
    
    try {
    	
        List<AccountDTO> list = accountDAO.viewAccountDetailsByAccountNumber(accNumber);
       
        System.out.println();
        
        Consumer<AccountDTO> cun = res -> System.out.println(" Account Number : "+ res.getAccountNumber() +
        
        		", Balance : "+ res.getBalance() + ", Account Type : "+ res.getAccountType() + ", Customer Name : "+ res.getCustomerName());
       
        list.forEach(cun);
        
        System.out.println();
        
    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
        System.out.println(ex.getMessage());
    }
}










	
}
	
