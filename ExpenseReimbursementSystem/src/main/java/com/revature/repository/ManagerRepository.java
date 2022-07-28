package com.revature.repository;

import java.util.List;

import com.revature.ModelAssociate;
import com.revature.ModelManager;

public interface ManagerRepository {
	
	List<ModelManager> viewManagerList();
		
	void save(ModelManager additionalManager);
		
	void update(ModelManager manager);
		
	ModelManager locatebyManagerUsername(String manager_username);
	
	ModelManager locatebyManagerPassword(String manager_password);
	

}
