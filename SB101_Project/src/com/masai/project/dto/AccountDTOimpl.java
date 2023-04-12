package com.masai.project.dto;

public class AccountDTOimpl implements AccountDTO{

	int accountNumber;
	String accountType;
	double balance;
	int customerId;
	String accountStatus;
	
	



	public AccountDTOimpl(String accountType, double balance, int customerId, String accountStatus) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
		this.accountStatus = accountStatus;
	}
	

	public AccountDTOimpl(int accountNumber, String accountType, double balance, int customerId, String accountStatus) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
		this.accountStatus = accountStatus;
	}

	

	
	
	// adding customer name 
	 private String customerName;
	    
	 public String getCustomerName() {
       return customerName;
     }
	    
	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }
	

	public AccountDTOimpl() {
		// TODO Auto-generated constructor stub
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
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int getCustomerId() {
		return customerId;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String getAccountStatus() {
		return accountStatus;
	}

	@Override
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	
	
	
	
	
	
	
}
