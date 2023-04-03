package com.masai.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.masai.project.dto.CustomerDTO;
import com.masai.project.dto.CustomerDTOimpl;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class CustomerDAOimpl implements CustomerDAO{

	
	//For registering new Customer
		public void newRegister(CustomerDTO reg) throws SomethingWentWrongException,NoRecordFoundException{
			
			Connection conn = null;
			
			try {
				
				conn = DBUtils.getConnectionTodatabase();
				
				String query = "INSERT INTO customer (customer_id, name, mobile, address, username, password) VALUES (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, reg.getCustomerId());
				ps.setString(2, reg.getName());
				ps.setString(3, reg.getMobileNumber());
				ps.setString(4, reg.getAddress());
				ps.setString(5, reg.getUsername());
				ps.setString(6, reg.getPassword());
				
				int rs = ps.executeUpdate();
				
				if(rs == 0) {
					throw new NoRecordFoundException("No customer found");
				}
				
			} catch(ClassNotFoundException | SQLException ex) {
				throw new SomethingWentWrongException("Unable to add customer");
			}finally {
				try {
					DBUtils.closeConnection(conn);					
				}catch(SQLException ex) {
					
				}
			}	
		}

		
//***************************************************************************************************

	//view Information About all Customer
	@Override
public List<CustomerDTO> viewInformationAboutCustomer() throws SomethingWentWrongException, NoRecordFoundException {
		
		Connection conn = null;
		List<CustomerDTO> list = new ArrayList<>();
		
		try {
			conn = DBUtils.getConnectionTodatabase();
			
			String query = "SELECT customer_id, name, mobile, address, username, password FROM customer WHERE is_deleted = 0";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				throw new NoRecordFoundException("No customer found");
			} else {
				do {
					list.add(new CustomerDTOimpl(rs.getInt("customer_id"), rs.getString("name"), rs.getString("mobile"), rs.getString("address"), rs.getString("username"), rs.getString("password")));
				} while (rs.next());
			}
			
		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to retrieve customer information");
		} finally {
			try {
				DBUtils.closeConnection(conn);					
			} catch (SQLException ex) {
				// log the exception
			}
		}
		
		return list;
	}

	

//***************************************************************************************************
	
	
	//view Customer Details By Id
	@Override
	public List<CustomerDTO> viewCustomerDetailsById(int custId) throws SomethingWentWrongException, NoRecordFoundException {
	    Connection conn = null;
	    List<CustomerDTO> list = new ArrayList<>();
	    try {
	        conn = DBUtils.getConnectionTodatabase();
	        String query = "SELECT name, mobile, address FROM customer WHERE customer_id = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, custId);
	        ResultSet rs = ps.executeQuery();
	        if (DBUtils.isResultSetEmpty(rs)) {
	            throw new NoRecordFoundException("No customer found");
	        }
	        while (rs.next()) {
	            CustomerDTO customer = new CustomerDTOimpl();
	            customer.setCustomerId(custId);
	            customer.setName(rs.getString("name"));
	            customer.setMobileNumber(rs.getString("mobile"));
	            customer.setAddress(rs.getString("address"));
	            list.add(customer);
	        }
	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to get customer by id");
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	            // handle exception
	        }
	    }
	    return list;
	}

	
//***************************************************************************************************
	
	
	// Existing User Login
	public int login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException{
		
		
		Connection conn = null;
		int user_id = -1;
		try {

			conn = DBUtils.getConnectionTodatabase();
			
			String query = "SELECT customer_id from customer WHERE username = ? AND password = ? AND is_deleted = 0";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("Invalid username or password");
			}
			
			rs.next();
			user_id = rs.getInt(1) ;
			
			
		} catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Invalid" + ex.getMessage());
		}finally {
			try {
				DBUtils.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
		return user_id;	
	}
	
	
//***************************************************************************************************

	public void UpdateCustomerDetails(CustomerDTO reg) throws SomethingWentWrongException, NoRecordFoundException{
		
		
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "UPDATE customer SET name = ?, mobile = ?, address = ? WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, reg.getName());
			ps.setString(2, reg.getMobileNumber());
			ps.setString(3, reg.getAddress());
			ps.setInt(4, reg.getCustomerId());
			
			ps.executeUpdate();
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to update the record now, try again later");
		}finally {
			try {
				DBUtils.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}	
	}

	
//***************************************************************************************************

	//change password
	public void changePassword(CustomerDTO pass)throws SomethingWentWrongException, NoRecordFoundException{
		
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "UPDATE customer SET password = ? WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pass.getPassword());
			ps.setInt(2, pass.getCustomerId());
			
			ps.executeUpdate();
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to update the password, try again later"+ex.getMessage());
		}finally {
			try {
				DBUtils.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
