package com.masai.project.ui;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.masai.project.dao.AccountDAO;
import com.masai.project.dao.AccountDAOimpl;
import com.masai.project.dao.CustomerDAO;
import com.masai.project.dao.CustomerDAOimpl;
import com.masai.project.dto.CustomerDTO;
import com.masai.project.dto.CustomerDTOimpl;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class CustomerUI {

	
	
	public static void RegisterNewAccount(Scanner sc) {
		
		 System.out.println("\nPlease enter the following details to register for a new account:");
	     
		 System.out.print("Customer Id: ");
	     int custommerId = sc.nextInt();
		 
		 System.out.print("Name: ");
		 sc.nextLine();
	     String name = sc.nextLine();
	     
	     System.out.print("Mobile Number: ");
	     String mobile = sc.next();
	     
	     System.out.print("Address: ");
	     String address = sc.next();
	     
	     System.out.print("Username: ");
	     String username = sc.next();
	     
	     System.out.print("Password: ");
	     String password = sc.next();
	     
	     CustomerDTO customerDTO = new CustomerDTOimpl(custommerId, name, address, mobile, username, password);
	     
	     CustomerDAO customerDAO = new CustomerDAOimpl();
	    		 
	     try {
			
	    	 customerDAO.newRegister(customerDTO);
	    	 
	    	 System.out.println("Customer registered successfully");
	    	 
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}
	 
		
		}
		
	
	

	
	//View Customer Details
	static void viewCustomerInformation() {
		
		CustomerDAO customerDAO = new CustomerDAOimpl();
		
		try {
			
			List<CustomerDTO> list = customerDAO.viewInformationAboutCustomer();
			
			System.out.println();
			Consumer<CustomerDTO> cun = res -> System.out.println("Customer ID : "+ res.getCustomerId() + ", Customer Name : "+ res.getName() +
					", Address : "+ res.getAddress() + ", Mobile Number : "+ res.getMobileNumber());;
			
			list.forEach(cun);
			System.out.println();
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
			
		}
	}
	
	
	
	
	//view Customer Details By Customer Id
	static void viewCustomerDetailsByCustomerId(Scanner sc) {
		
		System.out.println("Enter the Customer Id");
		int Cid = sc.nextInt();
		
		CustomerDAO customerDAO = new CustomerDAOimpl();
		
		try {
			
			List<CustomerDTO> list = customerDAO.viewCustomerDetailsById(Cid);
			System.out.println();
			Consumer<CustomerDTO> cun = res -> System.out.println(" Customer Name : "+ res.getName() +
					", Address : "+ res.getAddress() + ", Mobile Number : "+ res.getMobileNumber());;
			
			list.forEach(cun);
			
			System.out.println();
			
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}	
	}
	
	
	
	
	
	// Existing user login
	static boolean login(Scanner sc) {

	    System.out.println("Enter the Username : ");
	    String username = sc.next();

	    System.out.println("Enter the Password");
	    String password = sc.next();

	    CustomerDAO customerDAO = new CustomerDAOimpl();

	    try {
	    	// You can use the user_id to identify the customer who has logged in
	        int user_id =  customerDAO.login(username, password);

	        if (user_id == -1) {
	            System.out.println("Invalid username or password");
	            return false;
	        } else {
	            System.out.println("Login successful");
	            
	            return true;
	        }

	    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
	        System.out.println(ex.getMessage());
	        return false;
	    }

	}

	
	//
	static void updateCustomer(Scanner sc) {
		
		System.out.print("Enter customer id ");
		String cid = sc.next();
		int customerId = Integer.parseInt(cid);

		System.out.print("Enter customer name ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter mobile Number ");
		String mobile = sc.next();

		System.out.print("Enter address ");
		String address = sc.next();

		CustomerDTO customerDTO = new CustomerDTOimpl(customerId, name, address, mobile);

		CustomerDAO customerDAO = new CustomerDAOimpl();
		try {
		    customerDAO.UpdateCustomerDetails(customerDTO);
		    System.out.println("Customer updated successfully");
		} catch(SomethingWentWrongException | NoRecordFoundException ex) {
		    System.out.println(ex.getMessage());
		}
	}
	
	
	//Change password
	
	static void changePassword(Scanner sc) {
		
		System.out.print("Enter customer id ");
		String cid = sc.next();
		int customerId = Integer.parseInt(cid);

		System.out.print("Enter password ");
		String password = sc.next();

		CustomerDTO customerDTO = new CustomerDTOimpl(customerId, password);

		CustomerDAO customerDAO = new CustomerDAOimpl();
		try {
		    customerDAO.changePassword(customerDTO);
		    System.out.println("Changed password successfully");
		} catch(SomethingWentWrongException | NoRecordFoundException ex) {
		    System.out.println(ex.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
