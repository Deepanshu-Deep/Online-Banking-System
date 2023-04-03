package com.masai.project.dto;

public class CustomerDTOimpl implements CustomerDTO{

	int customerId;
	String name;
	String address;
	String mobileNumber;
	String username;
	String password;
	
	


	public CustomerDTOimpl(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public CustomerDTOimpl(int customerId, String name, String address, String mobileNumber, String username,
			String password) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.username = username;
		this.password = password;
	}

	



	public CustomerDTOimpl(int customerId, String name, String address, String mobileNumber) {
	
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
		
	}

	public CustomerDTOimpl(int customerId2, String password2) {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	public CustomerDTOimpl(String name, String address, String mobileNumber) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}



	public CustomerDTOimpl() {
		// TODO Auto-generated constructor stub
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getMobileNumber() {
		return mobileNumber;
	}

	@Override
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	
}
