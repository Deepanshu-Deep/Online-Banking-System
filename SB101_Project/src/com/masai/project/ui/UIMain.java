package com.masai.project.ui;

import java.util.Scanner;

public class UIMain {

	// method to display the Admin menu options
	static void displayAdminMenu() {
		
			
			System.out.println("1. View information about all Customers");
			System.out.println("2. View particular Customer details by Customer ID");
			System.out.println("3. View information about all accounts");
			System.out.println("4. View particular account details by Account Number");
			System.out.println("5. Log Out");
		
	}
	
	
	// method to handle the Admin menu options
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
					AccountUI.getAllAccountDetails(sc);
					break;
				case 4:
					AccountUI.viewAccountDetailsByAccountNumber(sc);;
					break;
				case 5:
					System.out.println("Log Out admin successfully");
					main(null);
					break;
				default:
					System.out.println("Invalid Selection please try again later");
			}
			
		}while(choice != 0);
	}
	
	
	
	
	
	static void AdminLogin(Scanner sc) {
		
		System.out.println("Enter the Admin name : ");
		String username = sc.next();
		
		System.out.println("Enter the Admin Password");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			
			System.out.println("Login Successfully");
			System.out.println();
			adminMenu(sc);
		}
		else {
			System.out.println("Login failed. Please try again.");
		}	
	}
	
	
	// method to display the User menu options
	static void displayUserMenu() {
		
		System.out.println("\nPlease choose an option:");
	
		System.out.println("1. Update account details");
        System.out.println("2. change password");
        System.out.println("3. Open account");
        System.out.println("4. Deposit money");
        System.out.println("5. withdraw money");
        System.out.println("6. Transfer Money by account number");
        System.out.println("7. Delete the account");
    	System.out.println("8. Log Out");
        
        
}
	// method to handle the user menu options
static void UserMenu(Scanner sc) {

		int choice = 0;
		do {
			
			displayUserMenu();
		
			System.out.print("\nEnter Selection ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					CustomerUI.updateCustomer(sc);
					break;
				case 2:
					CustomerUI.changePassword(sc);
					break;
				case 3:
					AccountUI.OpenAccountUI(sc);
					break;
				case 4:
					AccountUI.depositMoneyUI(sc);
					break;
				case 5:
					AccountUI.withdrawalMoneyUI(sc);
					break;
				case 6:
					TransactionUI.transferMoneyUI(sc);
					break;
				case 7:
					AccountUI.deleteAccountUI(sc);
					break;
				
				case 8:
					System.out.println("\nLog Out successfully\n");
					main(null);
					break;
				
				default:
					System.out.println("Invalid Selection. Please enter a valid option.");
			}
			
		}while(choice != 0);
	
		
	}
	

	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to the Bank");		
		
		int choice = 0;
		do {
			System.out.println("\n****** Main Menu ******");
			System.out.println();
			System.out.println("1. login Admin");
			System.out.println("2. login Customer");
			System.out.println("3. Register for new account");
			
			
			System.out.println("\nEnter your choice:");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					AdminLogin(sc);
					break;
				case 2:
					CustomerUI.login(sc);	
					break;
				case 3:
					CustomerUI.RegisterNewAccount(sc);
					break;
				default:
					
					System.out.println("Invalid choice. Please enter a valid option.");
			}
			
		}while(choice != 0);
		sc.close();
		
		
	}

}
