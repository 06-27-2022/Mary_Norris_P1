package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.ConnectionUtility;
import com.revature.ModelReimbursement;

public class ReimbursementRepoImplement implements ReimbursementRepository {
	
	public List<ModelReimbursement> viewReimbursementList(){
		
		Connection newConn = null;
		Statement newStmt = null;
		ResultSet newSet = null;
		
		List<ModelReimbursement> reimbursements = new ArrayList<>();
		
		final String SQL = "select * from reimbursements";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.createStatement();
			newSet = newStmt.executeQuery(SQL);
			
			
			while(newSet.next()) {
				ModelReimbursement reimbursement = new ModelReimbursement(
						newSet.getInt(1),
						newSet.getString(2),
						newSet.getString(3),
						newSet.getInt(4),
						newSet.getDouble(5),
						newSet.getString(6),
						newSet.getString(7));
						
				
				reimbursements.add(reimbursement);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				newConn.close();
				newStmt.close();
				newSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return reimbursements;
		
	}
	
	@Override
	public void save(ModelReimbursement additionalReimbursement) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "insert into reimbursements values(default, ?, ?, ?, ?, ?, ?) ";
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(2, additionalReimbursement.getAssociateName());
			newStmt.setString(3, additionalReimbursement.getAssociateUsername());
			newStmt.setInt(4, additionalReimbursement.getManagerId());
			newStmt.setDouble(5, additionalReimbursement.getReimbursementAmount());
			newStmt.setString(6, additionalReimbursement.getReimbursementDescription());
			newStmt.setString(7, additionalReimbursement.getApprovedORdenied());
			newStmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				newConn.close();
				newStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void update(ModelReimbursement reimbursement) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "update reimbursements set approved_or_denied = ? where associate_username = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(7, reimbursement.getApprovedORdenied());
			newStmt.setString(3, reimbursement.getAssociateUsername());
			newStmt.execute();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					newConn.close();
					newStmt.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}
	@Override
	public ModelReimbursement locateReimbursementId(int reimbursementId) {
		
		ModelReimbursement reimbursement = null;
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		ResultSet newSet = null;
		
		final String SQL = "Select * from reimbursements where reimbursement_id = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			newStmt.setInt(1, reimbursementId);
			newSet = newStmt.executeQuery();
			
			if(newSet.next()) {
				reimbursement = new ModelReimbursement(
						newSet.getInt(1),
						newSet.getString(2),
						newSet.getString(3),
						newSet.getInt(4),
						newSet.getDouble(5),
						newSet.getString(6),
						newSet.getString(7));
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				newConn.close();
				newStmt.close();
				newSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reimbursement;
	}
	


}
