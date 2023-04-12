package com.masai.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.masai.project.exception.InsufficientBalanceException;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class TransactionDAOimpl implements TransactionDAO{

	
	
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

		
		
	
	//Transfer Money to other Account number
	public void transferMoney(int fromAccount, int toAccount, double amount) throws SomethingWentWrongException, NoRecordFoundException, InsufficientBalanceException {
	    Connection conn = null;

	    try {
	        conn = DBUtils.getConnectionTodatabase();

	        // check if fromAccount number exists
	        if (!checkIfAccountNumberExists(fromAccount)) {
	            throw new NoRecordFoundException("From account not found.");
	        }

	        // check if toAccount number exists
	        if (!checkIfAccountNumberExists(toAccount)) {
	            throw new NoRecordFoundException("To account not found.");
	        }

	        // get fromAccount balance
	        double currentBalance = getAccountBalance(fromAccount);

	        // check if there's sufficient balance
	        if (currentBalance < amount) {
	            throw new InsufficientBalanceException("Insufficient balance.");
	        }

	        // update balances
	        String query = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setDouble(1, amount);
	        ps.setInt(2, fromAccount);
	        int rs = ps.executeUpdate();

	        query = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
	        ps = conn.prepareStatement(query);
	        ps.setDouble(1, amount);
	        ps.setInt(2, toAccount);
	        rs = ps.executeUpdate();

	        if (rs > 0) {
	            System.out.println("\nMoney transferred successfully");
	        }

	    } catch (ClassNotFoundException | SQLException ex) {
	        throw new SomethingWentWrongException("\nUnable to transfer money" + ex.getMessage());
	    } finally {
	        try {
	            DBUtils.closeConnection(conn);
	        } catch (SQLException ex) {
	            // ignore
	        }
	    }
	}

	
	
	
	
	
}
