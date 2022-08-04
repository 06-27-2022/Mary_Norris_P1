package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.revature.util.ConnectionUtility;
import com.revature.ModelAssociate;
import com.revature.util.LogbackUtility;

public class AssociateRepoImplement implements AssociateRepository{
	
	//ch.qos.logback.classic.Logger childLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.revature.util.LogbackUtility");


		public List<ModelAssociate> viewAssociateList(){
			
			Connection newConn = null;
			Statement newStmt = null;
			ResultSet newSet = null;
			
			
			List<ModelAssociate> associates = new ArrayList<>();
			
			final String SQL = "select * from associates";
			
			try {
				newConn = ConnectionUtility.getNewDBConnection();
				newStmt = newConn.createStatement();
				newSet = newStmt.executeQuery(SQL);
				
				
				while(newSet.next()) {
					ModelAssociate associate = new ModelAssociate(
							newSet.getInt(1),
							newSet.getString(2),
							newSet.getString(3),
							newSet.getString(4));
					
					associates.add(associate);
					
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
			 /*
			try {
				childLogger.debug("Debug of the viewAssociatesList method:", associates);
			}catch(Exception e) {
				childLogger.debug("Exception thrown on viewAssociateListmethod:", associates, e);
			}
			*/
			
			return associates;			
		}
	
	@Override
	public void save(ModelAssociate additionalAssociate) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "insert into associates values(default, ?, ?, ?) ";
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(1, additionalAssociate.getAssociate_name());
			newStmt.setString(2, additionalAssociate.getAssociate_username());
			newStmt.setString(3, additionalAssociate.getAssociate_password());
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
	public void update(ModelAssociate associate) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "update associates set associate_username = ? where id = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(3, associate.getAssociate_username());
			newStmt.setInt(1, associate.getAssociate_id());
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
	public ModelAssociate locatebyUsername(String username) {
		
		ModelAssociate usernameArchive = null;
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		ResultSet newSet = null;
		
		final String SQL = "Select * from associates where associate_username = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			newStmt.setString(1, username);
			newSet = newStmt.executeQuery();
			
			if(newSet.next()) {
				usernameArchive = new ModelAssociate(
						newSet.getInt(1),
						newSet.getString(2),
						newSet.getString(3),
						newSet.getString(4));
				
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
		
		return usernameArchive;
	}
	
}
