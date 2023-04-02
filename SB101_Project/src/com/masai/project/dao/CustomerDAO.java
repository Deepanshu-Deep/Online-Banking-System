package com.masai.project.dao;

import java.util.List;

import com.masai.project.dto.CustomerDTO;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public interface CustomerDAO {

	public void newRegister(CustomerDTO reg) throws SomethingWentWrongException, NoRecordFoundException;
	public List<CustomerDTO> viewInformationAboutCustomer() throws SomethingWentWrongException, NoRecordFoundException;
	public List<CustomerDTO> viewCustomerDetailsById(int custId)throws SomethingWentWrongException,NoRecordFoundException;
	public int login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;
	public void UpdateCustomerDetails(CustomerDTO reg) throws SomethingWentWrongException, NoRecordFoundException;
	public void changePassword(CustomerDTO pass)throws SomethingWentWrongException, NoRecordFoundException;
	
	
	
}
