package com.masai.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.masai.project.dto.AccountDTO;
import com.masai.project.dto.AccountDTOimpl;
import com.masai.project.exception.InsufficientBalanceException;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class AccountDAOimpl implements AccountDAO{

	
	//Check if the Customer has already account
	public boolean checkIfAccountExists(int customerId) throws SomethingWentWrongException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = DBUtils.getConnectionTodatabase();

	        String query = "SELECT COUNT(*) FROM account WHERE customer_id = ? AND is_deleted = 0";
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, customerId);
	        rs = ps.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to check account existence."+ex.getMessage());
	    } finally {
	        try {
	            rs.close();
	            ps.close();
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	            // Do nothing
	        }
	    }
	}

	
	
	// Open Account
	public void openAccount(AccountDTO aDto) throws SomethingWentWrongException, NoRecordFoundException{

	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        conn = DBUtils.getConnectionTodatabase();
	        
	        // Check if customer already has an account
	        String checkQuery = "SELECT COUNT(*) FROM account WHERE customer_id = ? AND is_deleted = 0";
	        ps = conn.prepareStatement(checkQuery);
	        ps.setInt(1, aDto.getCustomerId());
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println("Customer already has an account.");
	            return;
	        }

	        // Generate a random account number
	        Random rand = new Random();
	        int accountNumber = rand.nextInt(900000) + 100000;
	        
	        // Insert the new account into the database
	        String insertQuery = "INSERT INTO account (account_number, customer_id, account_type, balance, status) VALUES (?, ?, ?, ?, ?)";
	        ps = conn.prepareStatement(insertQuery);
	        ps.setInt(1, accountNumber);
	        ps.setInt(2, aDto.getCustomerId());
	        ps.setString(3, aDto.getAccountType());
	        ps.setDouble(4, aDto.getBalance());
	        ps.setString(5, aDto.getAccountStatus());
	        int result = ps.executeUpdate();
	        
	        if (result > 0) {
	            System.out.println("Account opened successfully. Account Number: " + accountNumber);
	        } else {
	            throw new SomethingWentWrongException("Unable to open account.");
	        }
	        
	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to open account."+ex.getMessage());
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {

	        }
	    }
	}

	
	
//***************************************************************************************************

	
	//Check if Account Number exists or not.
	public boolean checkIfAccountNumberExists(int accountNumber) throws SomethingWentWrongException{
		
		Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = DBUtils.getConnectionTodatabase();

	        String query = "SELECT COUNT(*) FROM account WHERE account_number = ? AND is_deleted = 0";
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, accountNumber);
	        rs = ps.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to check account existence."+ex.getMessage());
	    } finally {
	        try {
	            rs.close();
	            ps.close();
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	            
	        }
	    }
	}

	
	
	
	
	
	
	//To get updated balance 
	public double getAccountBalance(int accNumber) throws SomethingWentWrongException, NoRecordFoundException {

	    Connection conn = null;
	    double balance = 0;

	    try {
	        conn = DBUtils.getConnectionTodatabase();
	        String query = "SELECT balance FROM account WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, accNumber);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            balance = rs.getDouble("balance");
	        } else {
	            throw new NoRecordFoundException("Account not found!");
	        }

	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to get account balance");
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {

	        }
	    }

	    return balance;
	}

	

	//Deposit Money
	public double depositMoney(int accNumber, double amount) throws SomethingWentWrongException, NoRecordFoundException{
	    Connection conn = null;
	    double updatedBalance = 0;
	    try {
	        conn = DBUtils.getConnectionTodatabase();
	        // check if account number exists
	        if (!checkIfAccountNumberExists(accNumber)) {
	            throw new NoRecordFoundException("Account not found.");
	        }
	        // get account balance
	        double currentBalance = getAccountBalance(accNumber);
	        String query = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setDouble(1, amount);
	        ps.setInt(2, accNumber);
	        int rs = ps.executeUpdate();
	        if (rs > 0) {
	            updatedBalance = currentBalance + amount;
	            System.out.println("Money deposited successfully");
	        }
	    } catch(ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to deposit money");
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);                    
	        } catch(SQLException ex) {
	            // ignore
	        }
	    }
	    return updatedBalance;
	}

	
	
	//Withdrawal Money
	public double withdrawalMoney(int accNumber, double amount) throws SomethingWentWrongException, NoRecordFoundException, InsufficientBalanceException {

	    Connection conn = null;
	    double updatedBalance = 0;

	    try {

	        conn = DBUtils.getConnectionTodatabase();

	        // check if account number exists
	        if (!checkIfAccountNumberExists(accNumber)) {
	            throw new NoRecordFoundException("Account not found.");
	        }

	        // get account balance
	        double currentBalance = getAccountBalance(accNumber);

	        // check if there's sufficient balance
	        if (currentBalance < amount) {
	            throw new InsufficientBalanceException("Insufficient balance.");
	        }

	        // update balance
	        String query = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setDouble(1, amount);
	        ps.setInt(2, accNumber);
	        int rs = ps.executeUpdate();

	        if (rs > 0) {
	            System.out.println("Money withdrawn successfully");
	            updatedBalance = currentBalance - amount;
	        }

	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to withdraw money" + ex.getMessage());
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	            // ignore
	        }
	    }
	    
	    return updatedBalance;
	}

		
		



	
//************************************************************************************
	
	
	
	public boolean deleteAccount(int accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
	   
		Connection conn = null;

	    try {
	        conn = DBUtils.getConnectionTodatabase();

	        // Check if account exists
	        if (!checkIfAccountNumberExists(accountNumber)) {
	            throw new NoRecordFoundException("Account not found.");
	        }

	        // Delete account
	        String query = "DELETE FROM account WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, accountNumber);

	        int rowsDeleted = ps.executeUpdate();
	        return rowsDeleted > 0;

	    } catch ( ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("Unable to delete account");
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	        }
	    }
	}

	
	
	
//****************************************************************************************
	

//		View information about all accounts
	    @Override
	    public List<AccountDTO> getAllAccounts() throws SomethingWentWrongException, NoRecordFoundException {
	        Connection conn = null;
	        List<AccountDTO> list = new ArrayList<>();

	        try {
	            conn = DBUtils.getConnectionTodatabase();
	            
	            String query = "SELECT account_number, account_type, balance, customer_id FROM account";

	            PreparedStatement ps = conn.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            if (DBUtils.isResultSetEmpty(rs)) {
	                throw new NoRecordFoundException("No accounts found");
	            }
	            
	            while (rs.next()) {
	                int accountNumber = rs.getInt("account_number");
	                String accountType = rs.getString("account_type");
	                double balance = rs.getDouble("balance");
	                int customerId = rs.getInt("customer_id");

	               AccountDTO account = new AccountDTOimpl(accountNumber, accountType, balance, customerId, accountType);

	                list.add(account);
	            }

	        } catch (ClassNotFoundException | SQLException ex) {
	            throw new SomethingWentWrongException("Unable to get accounts");
	        } finally {
	            try {
	                DBUtils.closeConnection(conn);
	            } catch (SQLException ex) {

	            }
	        }

	        return list;
	    }

	
	
//**************************************************************************************
	    
	    
	        //view Account Details By Account Number
	        @Override
	        public List<AccountDTO> viewAccountDetailsByAccountNumber(int accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
	            Connection conn = null;
	            List<AccountDTO> list = new ArrayList<>();
	            try {
	                conn = DBUtils.getConnectionTodatabase();
//	                String query = "SELECT account_number, balance, account_type FROM account WHERE account_number = ?";
	                
	                String query = "SELECT a.account_number, a.balance, a.account_type, c.name FROM account a INNER JOIN customer c ON a.customer_id = c.customer_id WHERE a.account_number = ?";

	                PreparedStatement ps = conn.prepareStatement(query);
	                
	                ps.setInt(1, accountNumber);
	                
	                ResultSet rs = ps.executeQuery();
	                
	                if (DBUtils.isResultSetEmpty(rs)) {
	                    throw new NoRecordFoundException("No account found with account number: " + accountNumber);
	                }
	                while (rs.next()) {
	                	
	                    AccountDTO account = new AccountDTOimpl();
	                    account.setAccountNumber(rs.getInt("account_number"));
	                    account.setBalance(rs.getDouble("balance"));
	                    account.setAccountType("account_type");
	                    account.setCustomerName(rs.getString("name"));
	                    
	                    list.add(account);
	                }
	            } catch (ClassNotFoundException | SQLException ex) {
	                throw new SomethingWentWrongException("Unable to get account by account number");
	            } finally {
	                try {
	                    DBUtils.closeConnection(conn);
	                } catch (SQLException ex) {
	                    // handle exception
	                }
	            }
	            return list;
	        }
	    


	     
	     
	     
	     
	     
	     
	
		
		
}

	

	
	
	
	
	

