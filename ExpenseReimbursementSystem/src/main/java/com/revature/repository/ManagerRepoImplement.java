package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.ConnectionUtility;
import com.revature.ModelManager;

public class ManagerRepoImplement implements ManagerRepository{
	
	public List<ModelManager> viewManagerList(){
		
		Connection newConn = null;
		Statement newStmt = null;
		ResultSet newSet = null;
		
		List<ModelManager> managers = new ArrayList<>();
		
		final String SQL = "select * from managers";
		
			try {
				newConn = ConnectionUtility.getNewDBConnection();
				newStmt = newConn.createStatement();
				newSet = newStmt.executeQuery(SQL);
			
			
				while(newSet.next()) {
					ModelManager manager = new ModelManager(
						newSet.getInt(1),
						newSet.getString(2),
						newSet.getString(3),
						newSet.getString(4));
				
				managers.add(manager);
				
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
		return managers;
		
	}
	
	@Override
	public void save(ModelManager additionalManager) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "insert into managers values(default, ?, ?, ?) ";
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(2, additionalManager.getManagerName());
			newStmt.setString(3, additionalManager.getManagerUsername());
			newStmt.setString(4, additionalManager.getManagerPassword());
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
	public void update(ModelManager manager) {
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		
		final String SQL = "update managers set manager_username = ? where manager_id = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			
			newStmt.setString(3, manager.getManagerUsername());
			newStmt.setInt(1, manager.getManagerId());
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
	public ModelManager locatebyManagerUsername(String manager_username) {
		
		ModelManager managerUsernameArchive = null;
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		ResultSet newSet = null;
		
		final String SQL = "Select * from managers where manager_username = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			newStmt.setString(1, manager_username);
			newSet = newStmt.executeQuery();
			
			if(newSet.next()) {
				managerUsernameArchive = new ModelManager(
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
		
		return managerUsernameArchive;
	}
	
	@Override
	public ModelManager locatebyManagerPassword(String manager_password) {
		
		ModelManager managerPasswordArchive = null;
		
		Connection newConn = null;
		PreparedStatement newStmt = null;
		ResultSet newSet = null;
		
		final String SQL = "Select * from managers where manager_password = ?";
		
		try {
			newConn = ConnectionUtility.getNewDBConnection();
			newStmt = newConn.prepareStatement(SQL);
			newStmt.setString(1, manager_password);
			newSet = newStmt.executeQuery();
			
			if(newSet.next()) {
				managerPasswordArchive = new ModelManager(
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
		
		return managerPasswordArchive;
	}
}
