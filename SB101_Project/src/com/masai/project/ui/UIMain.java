package com.masai.project.ui;

import java.util.Scanner;

import com.masai.project.dao.CustomerDAO;
import com.masai.project.dao.TransactionDAO;
import com.masai.project.dao.TransactionDAOimpl;
import com.masai.project.dto.CustomerDTO;

public class UIMain {

	static void displayAdminMenu() {
		
			System.out.println("0. Exit");
			System.out.println("1. Login using his/her username and password");
			System.out.println("2. View information about all Customers");
			System.out.println("3. View particular Customer details by Customer ID");
			System.out.println("4. View information about all accounts");
			System.out.println("5. View particular account details by Account Number");
			System.out.println("6. Change the status of account from active to inoperative if no transaction for last 24 months and vice versa");
			System.out.println("7. view all inoperative accounts ");
			System.out.println("8. view all closed accounts ");
			System.out.println("9. See the transaction report for a day range for all accounts. ");
			System.out.println("10. See all high magnitude transaction for a day i.e. only those transaction in which more than 49999 is transferred ");
			System.out.println("11. Logout his account ");
		
	}
	
	static void adminMenu(Scanner sc) {
		
		int choice = 0;
		do {
			
			displayAdminMenu();
		
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					CustomerUI.viewCustomerInformation();
					break;
				case 2:
					CustomerUI.viewCustomerDetailsByCustomerId(sc);
					break;
				case 3:
					CustomerUI.viewCustomerDetailsByCustomerId(sc);
					break;
				case 4:
					AccountUI.getAllAccountDetails(sc);
					break;
				case 5:
					AccountUI.viewAccountDetailsByAccountNumber(sc);;
					break;
				case 6:
					AccountUI.viewAllInoperativeAccounts();
					break;
				case 7:
					
					break;
				case 8:
					
					break;
				case 9:
				
					break;
				case 10:
				
					break;
				case 0:
					System.out.println("Log Out admin");
					break;
				default:
					System.out.println("Invalid Selection please try again later");
			}
			
		}while(choice != 0);
	}
	
	
	
	
	
	static void AdminLogin(Scanner sc) {
		
		System.out.println("Enter the Username : ");
		String username = sc.next();
		
		System.out.println("Enter the Password");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			
			System.out.println("Login Successfully");
			adminMenu(sc);
		}
		else {
			System.out.println("Login failed. Please try again.");
		}	
	}
	
	
	
	static void displayUserMenu() {
		
		System.out.println("\nPlease choose an option:");
		System.out.println("0. exit");
        System.out.println("1. Register for a new account");
        System.out.println("2. Login to an existing account");
        System.out.println("3. Update account details");
        System.out.println("4. change password");
        System.out.println("5. Open account");
        System.out.println("6. Deposit money");
        System.out.println("7. withdraw money");
        System.out.println("8. Transfer Money by account number");
        System.out.println("9. Checking the transaction history for a date range");
        System.out.println("10. close the account");
        System.out.println("11. logout");
        System.out.println("12. Delete the account");
        
}
	
static void UserMenu(Scanner sc) {
	
	TransactionDAO transactionDAO = new TransactionDAOimpl();
		int choice = 0;
		do {
			
			displayUserMenu();
		
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					CustomerUI.RegisterNewAccount(sc);
					break;
				case 2:
					CustomerUI.login(sc);
					break;
				case 3:
					CustomerUI.updateCustomer(sc);
					break;
				case 4:
					CustomerUI.changePassword(sc);
					break;
				case 5:
					AccountUI.OpenAccountUI(sc);
					break;
				case 6:
					AccountUI.depositMoneyUI(sc);
					break;
				case 7:
					AccountUI.withdrawalMoneyUI(sc);
					break;
				case 8:
					TransactionUI.transferMoneyUI(sc);
					break;
				case 9:
					
//					TransactionUI.viewTransactionHistoryUI(sc, transactionDAO);

					break;
				case 10:
					AccountUI.closeAccount(sc);
					break;
				case 11:
//					logout();
					break;
				case 12:
					AccountUI.deleteAccountUI(sc);
					break;
				case 0:
					System.out.println("Log Out admin");
					break;
				default:
					System.out.println("Invalid Selection please try again later");
			}
			
		}while(choice != 0);
	
		
	}
	
	
	





	static void UserLogin(Scanner sc) {
		 
			System.out.println("Enter the Username : ");
			String username = sc.next();
			
			System.out.println("Enter the Password");
			String password = sc.next();
			
			if(username.equalsIgnoreCase("user") && password.equalsIgnoreCase("user")) {
				
				System.out.println("Login Successfully");
				UserMenu(sc);
			}
			else {
				System.out.println("Login failed. Please try again.");
			}
	}
	
	
	
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to the Bank");		
		
		int choice = 0;
		do {
			System.out.println("\n****** Main Menu ******");
			System.out.println("1. login Admin");
			System.out.println("2. login User");
			System.out.println("3. Exit");
			
			System.out.println("\nEnter your choice:");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					AdminLogin(sc);
					break;
				case 2:
					UserLogin(sc);
					break;
				case 3:
					System.out.println("Bye Bye");
					break;
				default:
					System.out.println("Invalid choice. Please try again later");
			}
			
		}while(choice != 0);
		sc.close();
		
		
	}

}
